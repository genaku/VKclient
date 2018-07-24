package com.genaku.local

import android.content.Context
import java.util.*
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by gena.kuchergin on 25.09.2017
 */
object DelegatesExt {
    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}

class Preference<T>(val context: Context, val name: String, private val default: T) : ReadWriteProperty<Any?, T> {

    private val prefs by lazy { context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            is Date -> Date(getLong(name, default.time))
            else -> throw IllegalArgumentException("This type can't be load from Preferences")
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            is Date -> putLong(name, value.time)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }
}