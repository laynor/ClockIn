package me.mziulu.clockin

import io.realm.Realm
import me.mziulu.clockin.model.Period
import org.joda.time.format.DateTimeFormat

class RealmInitialData : Realm.Transaction {

    override fun execute(realm: Realm) {
        val formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm")
        val period = Period()

        period.id = 1
        period.start = formatter.parseDateTime("05/01/2017 09:00").toDate()
        period.end = formatter.parseDateTime("05/01/2017 13:00").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 2
        period.start = formatter.parseDateTime("05/01/2017 15:00").toDate()
        period.end = formatter.parseDateTime("05/01/2017 19:00").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 3
        period.start = formatter.parseDateTime("06/01/2017 09:00").toDate()
        period.end = formatter.parseDateTime("06/01/2017 13:00").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 4
        period.start = formatter.parseDateTime("06/01/2017 15:00").toDate()
        period.end = formatter.parseDateTime("06/01/2017 19:00").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 5
        period.start = formatter.parseDateTime("07/01/2017 09:00").toDate()
        period.end = formatter.parseDateTime("07/01/2017 13:00").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 6
        period.start = formatter.parseDateTime("07/01/2017 14:00").toDate()
        period.end = formatter.parseDateTime("07/01/2017 17:30").toDate()
        period.active = false
        realm.insertOrUpdate(period)

        period.id = 7
        period.start = formatter.parseDateTime("08/01/2017 08:30").toDate()
        period.end = null
        period.active = true
        realm.insertOrUpdate(period)
    }

    override fun hashCode(): Int {
        return RealmInitialData::class.java.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is RealmInitialData
    }
}
