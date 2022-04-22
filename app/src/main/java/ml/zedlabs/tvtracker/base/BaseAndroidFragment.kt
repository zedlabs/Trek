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

    open fun writeLongToSharedPreferenceFile(key: String, value: Long) {
        sharedPref?.edit()?.let {
            it.putLong(key, value)
            it.apply()
        }
    }

    open fun writeStringToSharedPreferenceFile(key: String, value: String) {
        sharedPref?.edit()?.let {
            it.putString(key, value)
            it.apply()
        }
    }

    open fun writeBooleanToSharedPreferenceFile(key: String, value: Boolean) {
        sharedPref?.edit()?.let {
            it.putBoolean(key, value)
            it.apply()
        }
    }

    open fun readLongFromSharedPreferenceFile(key: String, defaultValue: Long = 0) : Long {
        return sharedPref?.getLong(key, defaultValue) ?: defaultValue
    }

    open fun readStringFromSharedPreferenceFile(key: String, defaultValue: String = "") : String {
        return sharedPref?.getString(key, defaultValue) ?: defaultValue
    }


    open fun readBooleanFromSharedPreferenceFile(key: String, defaultValue: Boolean = false) : Boolean {
        return sharedPref?.getBoolean(key, defaultValue) ?: defaultValue
    }
}