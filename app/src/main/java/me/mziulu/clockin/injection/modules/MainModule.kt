package me.mziulu.clockin.injection.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm
import me.mziulu.clockin.model.MainModel
import me.mziulu.clockin.ui.IMainPresenter
import me.mziulu.clockin.ui.MainPresenter
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton fun provideMainModel(realm: Realm): MainModel {
        return MainModel(realm)
    }

    @Provides
    @Singleton fun provideMainPresenter(model: MainModel): IMainPresenter {
        return MainPresenter(model)
    }
}