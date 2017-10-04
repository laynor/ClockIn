package me.mziulu.clockin.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.content_log.*
import me.mziulu.clockin.MainApplication
import me.mziulu.clockin.R
import me.mziulu.clockin.mvp.BaseView
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder
import javax.inject.Inject

interface ILogView : BaseView {
    fun loadData(data: List<Pair<String?, org.joda.time.Period?>>)
}

class LogFragment : Fragment(), ILogView {

    @Inject
    lateinit var presenter: ILogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainApplication.component.inject(this)

        presenter.view = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.content_log, container, false) as RecyclerView
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.initializeView()

        setupRecyclerView(recyclerview)
    }

    private fun setupRecyclerView(view: RecyclerView) {
        view.layoutManager = LinearLayoutManager(view.context)
    }

    override fun loadData(data: List<Pair<String?, org.joda.time.Period?>>) {
        recyclerview.adapter = RecyclerViewAdapter(activity, data)
    }

    override fun setError(error: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun terminate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private class RecyclerViewAdapter(context: Context, val data: List<Pair<String?, org.joda.time.Period?>>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    val background: Int
    val formatter: PeriodFormatter = PeriodFormatterBuilder().
            minimumPrintedDigits(2).printZeroAlways().
            appendHours().appendSeparator(":").
            appendMinutes().toFormatter()

    init {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.selectableItemBackground, typedValue, true)
        background = typedValue.resourceId
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dateView?.text = data[position].first
        holder.hoursView?.text = "${formatter.print(data[position].second)} hours"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val dateView: TextView? = view.findViewById(R.id.dateTxt)
        val hoursView: TextView? = view.findViewById(R.id.hoursTxt)
    }
}