package ml.zedlabs.tvtracker.util

import android.content.Context
import android.widget.Toast
import ml.zedlabs.tvtracker.util.Constants.IMAGE_W500_BASE

fun String.appendAsImageUrl(): String = "$IMAGE_W500_BASE$this"

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}