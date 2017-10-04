package me.mziulu.clockin.ui

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import me.mziulu.clockin.MainApplication
import me.mziulu.clockin.model.MainModel
import me.mziulu.clockin.mvp.BasePresenter
import java.util.concurrent.TimeUnit

interface IMainPresenter : BasePresenter {
    var view: IMainView

    fun fabPressed()
}

class MainPresenter constructor(private val model: MainModel) : IMainPresenter {

    override lateinit var view: IMainView

    private var dtObservable: Disposable? = null

    init {
        MainApplication.component.inject(this)
    }

    override fun initializeView() {
        dtObservable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    view.setDay(model.getDayString())
                    view.setTime(model.getTimeString())
                }

        view.setFabStatus(model.getCurrentState())
    }

    override fun terminate() {
        dtObservable?.dispose()
    }

    override fun fabPressed() {
        model.updateState()
        view.setFabStatus(model.getCurrentState())
    }
}