package com.sudansh.savic

import android.content.Context
import android.content.SharedPreferences

class Prefs(private val context: Context) {

    fun clearValues() {
        maxPatient = 0
        currentPatient = 0
        name = ""
    }

    private val FILENAME = "${BuildConfig.APPLICATION_ID}.prefs"
    private val MAX = "MAX"
    private val CURRENT = "CURRENT"
    private val NAME = "NAME"

    private val prefs: SharedPreferences =
        context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

    var maxPatient: Int?
        get() = prefs.getInt(MAX, 0)
        set(value) = prefs.edit().putInt(MAX, value ?: 0).apply()
    var currentPatient: Int
        get() = prefs.getInt(CURRENT, 0)
        set(value) = prefs.edit().putInt(CURRENT, value ?: 0).apply()
    var name: String
        get() = prefs.getString(NAME, "").orEmpty()
        set(value) = prefs.edit().putString(NAME, value).apply()
}