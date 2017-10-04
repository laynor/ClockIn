package me.mziulu.clockin.model

import io.realm.Realm
import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class LogModel @Inject constructor(val realm: Realm) {

    fun getData(): Map<String?, List<Period?>> {
        val fmt = DateTimeFormat.forPattern("dd-MM-yyyy")

        return realm.where(Period::class.java)
                .findAll()
                .groupBy<Period?, String?> { fmt.print(DateTime(it?.start)) }
    }

    fun calculateIntervals(values: List<Period?>): org.joda.time.Period? = values.fold(org.joda.time.Period.ZERO,
            { total, next ->
                val start = DateTime(next?.start)
                val end = DateTime(next?.end)
                total.plus(Interval(start, end).toPeriod())
            })
}
