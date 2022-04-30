package ml.zedlabs.tvtracker.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import ml.zedlabs.tvtracker.R

open class BaseAndroidFragment: Fragment() {

    private var sharedPref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = context?.getSharedPreferences(getString(R.string.pref_file_name), Context.MODE_PRIVATE)
    }

    open fun writeLongToSharedPreference(key: String, value: Long) {
        sharedPref?.edit()?.let {
            it.putLong(key, value)
            it.apply()
        }
    }

    open fun writeStringToSharedPreference(key: String, value: String) {
        sharedPref?.edit()?.let {
            it.putString(key, value)
            it.apply()
        }
    }

    open fun writeBooleanToSharedPreference(key: String, value: Boolean) {
        sharedPref?.edit()?.let {
            it.putBoolean(key, value)
            it.apply()
        }
    }

    open fun readLongFromSharedPreference(key: String, defaultValue: Long = 0) : Long {
        return sharedPref?.getLong(key, defaultValue) ?: defaultValue
    }

    open fun readStringFromSharedPreference(key: String, defaultValue: String = "") : String {
        return sharedPref?.getString(key, defaultValue) ?: defaultValue
    }


    open fun readBooleanFromSharedPreference(key: String, defaultValue: Boolean = false) : Boolean {
        return sharedPref?.getBoolean(key, defaultValue) ?: defaultValue
    }
}