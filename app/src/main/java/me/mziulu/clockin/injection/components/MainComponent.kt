package me.mziulu.clockin.injection.components


import android.app.Application
import dagger.Component
import me.mziulu.clockin.injection.modules.AndroidModule
import me.mziulu.clockin.injection.modules.LogModule
import me.mziulu.clockin.injection.modules.MainModule
import me.mziulu.clockin.model.MainModel
import me.mziulu.clockin.ui.ILogPresenter
import me.mziulu.clockin.ui.IMainPresenter
import me.mziulu.clockin.ui.LogFragment
import me.mziulu.clockin.ui.MainFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, MainModule::class, LogModule::class))
interface MainComponent {

    fun inject(app: Application)

    fun inject(view: MainFragment)

    fun inject(view: LogFragment)

    fun inject(presenter: IMainPresenter)

    fun inject(presenter: ILogPresenter)

    fun inject(model: MainModel)
}
