package com.wiserock.heruri.utils

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar

class Dialog(private val context: Context) {
    private val progressBarId: Int get() = ProgressBar.generateViewId()

    private val view: View? =
        LayoutInflater.from(context)
            .inflate(progressBarId, null)
    private val builder = AlertDialog.Builder(context)
    private val dialog: AlertDialog = builder
        .setCancelable(false)
        .setView(view)
        .create()

    fun showDialog(second: Long) {
        //로딩
        dialog.show()
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
        }, second)
    }

    fun dismissDialog() {
        dialog.dismiss()
    }
}