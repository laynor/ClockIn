package me.mziulu.clockin.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton fun provideContext(): Context {
        return application.baseContext
    }

    @Provides
    @Singleton fun provideDefaultRealm() : Realm {
        return Realm.getDefaultInstance()
    }
}