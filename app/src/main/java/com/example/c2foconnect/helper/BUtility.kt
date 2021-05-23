package com.example.c2foconnect.helper

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.net.URLEncoder
import java.util.regex.Pattern
import kotlin.math.roundToInt


class BUtility {

    companion object {

        fun isArrayEmpty(list: List<*>?): Boolean {
            return !(list != null && list.size > 0)
        }

        fun isStringEmpty(str: String?): Boolean {
            return str == null || str.trim { it <= ' ' } == "" || str == "null"
        }

        fun isActivityRunning(activity: Activity?): Boolean {
            return !(activity == null || activity.isFinishing || activity.isDestroyed)
        }

        fun isEditTextEmpty(editText: EditText?): Boolean {
            if (editText != null && editText.text != null) {
                val text = editText.text.toString().trim { it <= ' ' }
                return isStringEmpty(text)
            }
            return true
        }

        fun getIndexOfSubString(text: String, s: String): Int {
            val word = Pattern.compile(s)
            val match = word.matcher(text)

            return if (match != null && match.find()) {
                match.start()
            } else -1
        }

        fun showSnackbar(view: View, message: String, duration: Int) {
            Snackbar.make(view, message, duration).show()
        }

        fun showSnackbarWithColor(view: View, message: String, duration: Int, color: Int) {
            val snackbarView = Snackbar.make(view, message, duration)
            snackbarView.setBackgroundTint(color)
            snackbarView.show()
        }

        fun dpToPx(context: Context, dp: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }

        fun setTextIfStringNotEmpty(textView: TextView, text: String): Boolean {
            if (isStringEmpty(text)) {
                textView.visibility = View.GONE
                return false
            } else {
                textView.text = text
                textView.visibility = View.VISIBLE
                return true
            }

        }

        fun isInteger(s: String): Boolean {
            try {
                Integer.parseInt(s)
            } catch (e: NumberFormatException) {
                return false
            } catch (e: NullPointerException) {
                return false
            }

            return true
        }

    }
}
