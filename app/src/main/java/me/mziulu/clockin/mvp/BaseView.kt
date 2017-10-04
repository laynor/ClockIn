package me.mziulu.clockin.mvp

interface BaseView {

    fun setError(error: String?)

    fun terminate()
}