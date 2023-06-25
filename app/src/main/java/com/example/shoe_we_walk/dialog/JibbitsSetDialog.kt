package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.adapter.MyAbleJibbitsData

class JibbitsSetDialog(context : Context, val item : MyAbleJibbitsData) {
    private val dialog = Dialog(context, R.style.CustomDialog)
    private var context :Context = context
    private lateinit var onClickListener: JibbitsBuyDialog.OnDialogClickListener

    fun setOnClickListener(listener: JibbitsBuyDialog.OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {

    }

    interface OnDialogClickListener
    {
        fun onClicked(name :String)
    }
}