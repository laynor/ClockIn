package me.mziulu.clockin.ui

import me.mziulu.clockin.model.LogModel
import me.mziulu.clockin.mvp.BasePresenter
import javax.inject.Inject

interface ILogPresenter : BasePresenter {
    var view: ILogView
}

class LogPresenter @Inject constructor(val model: LogModel) : ILogPresenter {

    override lateinit var view: ILogView

    override fun initializeView() {
        val data = model.getData()
        val intervals = data.values.map { model.calculateIntervals(it) }
        view.loadData(data.keys.zip(intervals))
    }

    override fun terminate() {
    }
}