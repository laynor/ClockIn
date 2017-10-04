package me.mziulu.clockin.injection.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm
import me.mziulu.clockin.model.LogModel
import me.mziulu.clockin.ui.ILogPresenter
import me.mziulu.clockin.ui.LogPresenter
import javax.inject.Singleton

@Module
class LogModule {

    @Provides
    @Singleton fun provideLogPresenter(model: LogModel): ILogPresenter {
        return LogPresenter(model)
    }

    @Provides
    @Singleton fun provideLogModel(realm: Realm): LogModel {
        return LogModel(realm)
    }
}