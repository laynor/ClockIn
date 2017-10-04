package me.mziulu.clockin.model

import io.realm.Realm
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class MainModel @Inject constructor(val realm: Realm) {

    enum class STATES {
        START, STOP
    }

    private val dateFormatter = DateTimeFormat.forPattern("MMM d, YYYY")
    private val timeFormatter = DateTimeFormat.forPattern("hh:mm a")

    fun getDayString(): String = dateFormatter.print(DateTime())
    fun getTimeString(): String = timeFormatter.print(DateTime())

    fun getCurrentState(): STATES {
        val res = realm.where(Period::class.java).equalTo("active", true).findAll()
        return when (res.size) {
            0 -> STATES.START
            1 -> STATES.STOP
            else -> TODO()
        }
    }

    fun updateState() {
        when (getCurrentState()) {
            MainModel.STATES.START -> {
                realm.executeTransaction { realm ->
                    val dt = DateTime()
                    var p: Period? = realm.where(Period::class.java)
                            .equalTo("active", true)
                            .findFirst()
                    if (p == null) {
                        val maxId = realm.where(Period::class.java).max("id")?.toLong()
                        p = realm.createObject(
                                Period::class.java,
                                if(maxId != null) maxId + 1 else 0
                        )
                        p.start = dt.toDate()
                        p.active = true
                    }
                }
            }
            MainModel.STATES.STOP -> {
                realm.executeTransaction { realm ->
                    val dt = DateTime()
                    val p: Period = realm.where(Period::class.java)
                            .equalTo("active", true)
                            .findFirst() ?: // TODO raise error
                            TODO()
                    p.end = dt.toDate()
                    p.active = false
                }
            }
        }
    }
}