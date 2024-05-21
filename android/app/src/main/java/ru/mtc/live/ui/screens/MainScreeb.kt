@file:OptIn(ExperimentalMaterial3Api::class)

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.yandex.mapkit.map.CameraPosition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mtc.live.common.Utils.getGPS
import ru.mtc.live.data.network.MainNetwork
import ru.mtc.live.data.network.model.Venue
import ru.mtc.live.data.network.model.VenueFeature
import ru.mtc.live.ui.screens.VenueDetails
import ru.mtc.live.ui.screens.views.MapIconButton
import ru.mtc.live.ui.screens.views.Marker
import ru.mtc.live.ui.screens.views.YandexMap

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(network: MainNetwork) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = true)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    var cameraPosition by remember { mutableStateOf<CameraPosition?>(null) }
    val locationPermission = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    var venues by remember { mutableStateOf(emptyList<Venue>()) }
    var venue by remember { mutableStateOf<Venue?>(null) }
    val venueFeaturesFilter = remember { mutableStateListOf<VenueFeature>() }

    LaunchedEffect(key1 = Unit) {
        locationPermission.launchPermissionRequest()
    }

    LaunchedEffect(key1 = venueFeaturesFilter.size) {
        venues = network.getAllVenues(venueFeaturesFilter.map { it.name })
    }

    LaunchedEffect(key1 = locationPermission.hasPermission) {
        if(locationPermission.hasPermission) {
            cameraPosition = CameraPosition(getGPS(context = context), 11f, 0f,0f)
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if(venue == null) {
                VenuesList(
                    venues = venues,
                    onClick = { item ->
                        scope.launch {
                            bottomSheetState.show()
                            cameraPosition = CameraPosition(item.getLatLng(), 17f, 0f,0f)
                            venue = item
                            delay(300L)
                            bottomSheetState.expand()
                        }
                    }
                )
            }else {
                VenueDetails(
                    venue = venue!!,
                    network = network,
                    onBack = { venue = null }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            YandexMap(
                modifier = Modifier.fillMaxSize(),
                cameraPosition = cameraPosition,
                userLocation = locationPermission.hasPermission,
                markers = venues.map { item ->
                    Marker(
                        latitude = item.latitude,
                        longitude = item.longitude,
                        onClick = { _, _ ->
                            scope.launch {
                                bottomSheetState.show()
                                cameraPosition = CameraPosition(item.getLatLng(), 17f, 0f,0f)
                                venue = item
                                delay(100L)
                                bottomSheetState.expand()
                            }
                            true
                        }
                    )
                }
            )

            Row {
                VenueFeature.entries.forEach { feature ->
                    MapIconButton(
                        painter = painterResource(id = feature.icon),
                        modifier = Modifier.padding(5.dp)
                            .border(
                                border = BorderStroke(
                                    width = 2.dp,
                                    color = if(feature in venueFeaturesFilter) Color.Red else Color.Transparent
                                ),
                                shape = CircleShape
                            ),
                        onClick = {
                            if(feature in venueFeaturesFilter) {
                                venueFeaturesFilter.remove(feature)
                            }else {
                                venueFeaturesFilter.add(feature)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun VenuesList(
    venues: List<Venue>,
    onClick: (Venue) -> Unit = {}
) {
    LazyColumn {
        items(venues) { venue ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                onClick = { onClick(venue) }
            ) {
                Text(
                    text = venue.name,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    fontSize = 22.sp
                )
            }
        }
    }
}