package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.adapter.MyUsedJibbitsData

class JibbitsUnsetDialog (context : Context, val item : MyUsedJibbitsData){
    private val dialog = Dialog(context, R.style.CustomDialog)
    private var context :Context = context

    interface MyUnsetDialogListener {
        fun onDialogOkClicked()
    }

    private var dialogListener :MyUnsetDialogListener?= null

    fun setDialogListener(listener :MyUnsetDialogListener){
        dialogListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.unsetjibbits_layout)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()

        val cancelbtn : ImageView = dialog.findViewById(R.id.Dialog_CancelButton)
        val unsetbtn : ImageView = dialog.findViewById(R.id.Dialog_UnSetButton)

        cancelbtn.setOnClickListener {
            dialog.cancel()
        }
        unsetbtn.setOnClickListener {
            when(item.position) {
                1 -> Auth.locationdata.value?.location_1 = 0
                2 -> Auth.locationdata.value?.location_2 = 0
                3 -> Auth.locationdata.value?.location_3 = 0
                4 -> Auth.locationdata.value?.location_4 = 0
                5 -> Auth.locationdata.value?.location_5 = 0
                6 -> Auth.locationdata.value?.location_6 = 0
                7 -> Auth.locationdata.value?.location_7 = 0
                8 -> Auth.locationdata.value?.location_8 = 0
                9 -> Auth.locationdata.value?.location_9 = 0
                10 -> Auth.locationdata.value?.location_10 = 0
            }

            Toast.makeText(context, "지비츠 장식을 해제하였습니다.", Toast.LENGTH_SHORT).show()
            dialogListener?.onDialogOkClicked()
            dialog.cancel()
        }

        val helperImage : ImageView = dialog.findViewById(R.id.Dialog_MainHelperImage)

        when(item.position){
            1 -> helperImage.setImageResource(R.drawable.jibbits_guide_1)
            2 -> helperImage.setImageResource(R.drawable.jibbits_guide_2)
            3 -> helperImage.setImageResource(R.drawable.jibbits_guide_3)
            4 -> helperImage.setImageResource(R.drawable.jibbits_guide_4)
            5 -> helperImage.setImageResource(R.drawable.jibbits_guide_5)
            6 -> helperImage.setImageResource(R.drawable.jibbits_guide_6)
            7 -> helperImage.setImageResource(R.drawable.jibbits_guide_7)
            8 -> helperImage.setImageResource(R.drawable.jibbits_guide_8)
            9 -> helperImage.setImageResource(R.drawable.jibbits_guide_9)
            10 -> helperImage.setImageResource(R.drawable.jibbits_guide_10)
        }
    }
}