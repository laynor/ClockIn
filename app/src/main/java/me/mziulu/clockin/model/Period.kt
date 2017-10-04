package me.mziulu.clockin.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Period(@PrimaryKey var id: Long = 0,
                  var start: Date? = null,
                  var end: Date? = null,
                  var active: Boolean = false ) : RealmObject()