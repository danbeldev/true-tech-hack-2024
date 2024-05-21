package ru.mtc.live

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey("cb1daec0-fb7e-48dc-817e-0b77deecceed")
        MapKitFactory.initialize(this)
        MapKitFactory.getInstance().onStart()
    }
}