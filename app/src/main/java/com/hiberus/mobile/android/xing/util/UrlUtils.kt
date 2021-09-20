package com.hiberus.mobile.android.xing.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hiberus.mobile.android.xing.R

object UrlUtils {

    fun openUrl(context: Context?, url: String?) {
        context?.let {
            if (URLUtil.isValidUrl(url)) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(it, intent, null)
            } else {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.open_url_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
