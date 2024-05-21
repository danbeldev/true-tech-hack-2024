package ru.mtc.live.ui.screens.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.TextStyle
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import ru.mtc.live.R
import ru.mtc.live.common.Utils

data class Marker(
    val longitude: Double = 0.1,
    val latitude: Double = 0.1,
    val text: String? = null,
    @DrawableRes val icon: Int? = R.drawable.location,
    val onClick: ((MapObject, Point) -> Boolean)? = null
)

@Composable
fun YandexMap(
    modifier: Modifier = Modifier,
    isNightModeEnabled: Boolean = isSystemInDarkTheme(),
    markers: List<Marker> = emptyList(),
    cameraPosition: CameraPosition? = null,
    userLocation: Boolean = true
) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    val mapKit = remember { MapKitFactory.getInstance() }
    val userLocationLayer = remember { mapKit.createUserLocationLayer(mapView.mapWindow) }

    userLocationLayer.isVisible = userLocation
    userLocationLayer.isHeadingEnabled = userLocation

    LaunchedEffect(key1 = cameraPosition) {
        if(cameraPosition != null) {
            mapView.map.move(
                cameraPosition,
                Animation(Animation.Type.LINEAR, 1.5f),
                null
            )
        }
    }

    LaunchedEffect(key1 = markers) {
        mapView.map.mapObjects.clear()
        markers.forEach { marker ->
            mapView.map.mapObjects.addMarker(marker, isNightModeEnabled, context)
        }
    }

    LaunchedEffect(key1 = isNightModeEnabled) {
        mapView.map.isNightModeEnabled = isNightModeEnabled
    }

    Box {
        AndroidView(
            modifier = modifier,
            factory = {
                mapView.apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterEnd)
        ) {
            MapIconButton(
                painter = painterResource(id = R.drawable.plus),
                onClick = {
                    mapView.updateZoom(mapView.map.cameraPosition.zoom + 1)
                }
            )

            Spacer(modifier = Modifier.height(3.dp))

            MapIconButton(
                painter = painterResource(id = R.drawable.minus),
                onClick = {
                    mapView.updateZoom(mapView.map.cameraPosition.zoom - 1)
                }
            )

            if(userLocation) {
                Spacer(modifier = Modifier.height(20.dp))

                MapIconButton(
                    painter = painterResource(id = R.drawable.compass),
                    onClick = {
                        mapView.map.move(
                            CameraPosition(Utils.getGPS(context = context), 17f, 0f,0f),
                            Animation(Animation.Type.SMOOTH, 2.0f),
                            null
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun MapIconButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isNightModeEnabled: Boolean = isSystemInDarkTheme()
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                if(isNightModeEnabled)
                    androidx.compose.ui.graphics.Color.Black.copy(0.6f)
                else
                    androidx.compose.ui.graphics.Color.White.copy(0.8f)
            )
            .clickable { onClick() }
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(20.dp),
            tint = if(isNightModeEnabled)
                androidx.compose.ui.graphics.Color.White
            else
                androidx.compose.ui.graphics.Color.Black
        )
    }
}

private fun MapView.updateZoom(zoom: Float) {
    map.move(
        CameraPosition(
            map.cameraPosition.target,
            zoom,
            map.cameraPosition.azimuth,
            map.cameraPosition.tilt
        ),
        Animation(Animation.Type.LINEAR, 0.3f),
        null
    )
}

private fun MapObjectCollection.addMarker(
    marker: Marker,
    isNightModeEnabled: Boolean,
    context: Context
) {
    val point = Point(marker.latitude, marker.longitude)
    val placemark = addPlacemark(point)

    marker.text?.let { text ->
        placemark.setText(text)
        placemark.setTextStyle(
            TextStyle().apply {
                size = 10f
                placement = TextStyle.Placement.RIGHT
                offset = 5f
                color = if(isNightModeEnabled) Color.WHITE else Color.BLACK
            }
        )
    }

    marker.icon?.let { icon ->
        val imageProvider = ImageProvider.fromResource(context, icon)
        placemark.setIcon(imageProvider)
        placemark.setIconStyle(IconStyle().apply {
            scale = 0.06f
        })
    }

    marker.onClick?.let { onClick ->
        placemark.addTapListener(onClick)
    }
}

private fun Context.createBitmapFromVector(art: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(this, art) ?: return null
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}