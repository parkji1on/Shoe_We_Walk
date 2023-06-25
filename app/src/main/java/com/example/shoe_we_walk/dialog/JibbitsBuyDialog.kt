package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.adapter.JibbitsData

class JibbitsBuyDialog(context : Context, val item :JibbitsData) {
    private val dialog = Dialog(context, R.style.CustomDialog)
    private var context :Context = context
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.jibbitsbuy_layout)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()

        val name :TextView = dialog.findViewById(R.id.Dialog_ItemNameText)
        val price :TextView = dialog.findViewById(R.id.Dialog_ItemPriceText)
        val jibbitsImage :ImageView = dialog.findViewById(R.id.Dialog_ItemImage)

        val cancel :ImageView = dialog.findViewById(R.id.Dialog_CancelButton)
        val ok :ImageView = dialog.findViewById(R.id.Dialog_BuyButton)

        name.text = item.name
        price.text = item.price.toString()

        if(item.code == 1)
            jibbitsImage.setImageResource(R.drawable.jibbits_bee_front)
        else if(item.code == 2)
            jibbitsImage.setImageResource(R.drawable.jibbits_duck_front)
        else if(item.code == 3)
            jibbitsImage.setImageResource(R.drawable.jibbits_cat_front)
        else if(item.code == 4)
            jibbitsImage.setImageResource(R.drawable.jibbits_clover_front)
        else if(item.code == 5)
            jibbitsImage.setImageResource(R.drawable.jibbits_flower_front)
        else if(item.code == 6)
            jibbitsImage.setImageResource(R.drawable.jibbits_orange_front)
        else if(item.code == 7)
            jibbitsImage.setImageResource(R.drawable.jibbits_paeng_front)
        else if(item.code == 8)
            jibbitsImage.setImageResource(R.drawable.jibbits_bear_front)
        else if(item.code == 9)
            jibbitsImage.setImageResource(R.drawable.jibbits_octopus_front)
        else if(item.code == 10)
            jibbitsImage.setImageResource(R.drawable.jibbits_strawberry_front)

        cancel.setOnClickListener {
            dialog.cancel()
        }
    }

    interface OnDialogClickListener
    {
        fun onClicked(name :String)
    }
}