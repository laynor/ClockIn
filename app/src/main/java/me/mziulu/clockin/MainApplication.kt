package me.mziulu.clockin

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import me.mziulu.clockin.injection.components.DaggerMainComponent
import me.mziulu.clockin.injection.components.MainComponent
import me.mziulu.clockin.injection.modules.AndroidModule
import me.mziulu.clockin.injection.modules.MainModule
import net.danlew.android.joda.JodaTimeAndroid
import timber.log.Timber

class MainApplication : Application() {

    companion object {
        @JvmStatic
        lateinit var component: MainComponent
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("testdb.realm")
                .initialData(RealmInitialData())
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        JodaTimeAndroid.init(this)

        component = DaggerMainComponent
                .builder()
                .androidModule(AndroidModule(this))
                .mainModule(MainModule())
                .build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}