package me.mziulu.clockin.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.content_main.*
import me.mziulu.clockin.MainApplication
import me.mziulu.clockin.R
import me.mziulu.clockin.model.MainModel
import me.mziulu.clockin.mvp.BaseView
import javax.inject.Inject

interface IMainView : BaseView {
    fun setDay(day: String)
    fun setTime(time: String)
    fun setFabStatus(state: MainModel.STATES)
}

class MainFragment : Fragment(), IMainView {

    @Inject
    lateinit var presenter: IMainPresenter

    private lateinit var fabClicksSubscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainApplication.component.inject(this)

        presenter.view = this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.initializeView()

        fabClicksSubscription = RxView.clicks(fab).subscribe { presenter.fabPressed() }
    }

    override fun onDestroy() {
        super.onDestroy()

        fabClicksSubscription.dispose()

        presenter.terminate()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.content_main, container, false)
    }

    override fun setError(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun terminate() {
    }

    override fun setDay(day: String) {
        dateTxt.text = day
    }

    override fun setTime(time: String) {
        timeTxt.text = time
    }

    override fun setFabStatus(state: MainModel.STATES) {
        when (state) {
            MainModel.STATES.START -> fab.setImageResource(R.drawable.start)
            MainModel.STATES.STOP -> fab.setImageResource(R.drawable.stop)
        }
    }
}