package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.adapter.MyAbleJibbitsData

class JibbitsSetDialog(context : Context, val item : MyAbleJibbitsData) {
    private val dialog = Dialog(context, R.style.CustomDialog)
    private var context :Context = context

    interface MySetDialogListener {
        fun onDialogOkClicked()
    }

    private var dialogListener: MySetDialogListener? = null
    fun setDialogListener(listener: MySetDialogListener) {
        dialogListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.setjibbits_layout)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()

        val helperImage :ImageView = dialog.findViewById(R.id.Dialog_MainHelperImage)
        var cnt = 1

        val cancelbtn : ImageView = dialog.findViewById(R.id.Dialog_CancelButton)
        val setbtn : ImageView = dialog.findViewById(R.id.Dialog_SetButton)
        val leftbtn : ImageView = dialog.findViewById(R.id.Dialog_LeftImage)
        val rightbtn : ImageView = dialog.findViewById(R.id.Dialog_RightImage)
        val num :TextView = dialog.findViewById(R.id.Dialog_HelperIDXText)

        val imageResourceMap = mapOf(
            1 to R.drawable.jibbits_guide_1,
            2 to R.drawable.jibbits_guide_2,
            3 to R.drawable.jibbits_guide_3,
            4 to R.drawable.jibbits_guide_4,
            5 to R.drawable.jibbits_guide_5,
            6 to R.drawable.jibbits_guide_6,
            7 to R.drawable.jibbits_guide_7,
            8 to R.drawable.jibbits_guide_8,
            9 to R.drawable.jibbits_guide_9,
            10 to R.drawable.jibbits_guide_10
        )

        leftbtn.setOnClickListener {
            if(cnt == 1)
                cnt = 10
            else
                cnt--

            helperImage.setImageResource(imageResourceMap[cnt]!!)
            num.text = cnt.toString()

        }
        rightbtn.setOnClickListener {
            if(cnt == 10)
                cnt = 1
            else
                cnt++

            helperImage.setImageResource(imageResourceMap[cnt]!!)
            num.text = cnt.toString()
        }



        cancelbtn.setOnClickListener {
            dialog.cancel()
        }
        setbtn.setOnClickListener {
            if(cnt == 1)
            {
                if(Auth.locationdata.value?.location_1 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_1 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 2)
            {
                if(Auth.locationdata.value?.location_2 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_2 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 3)
            {
                if(Auth.locationdata.value?.location_3 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_3 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 4)
            {
                if(Auth.locationdata.value?.location_4 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_4 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 5)
            {
                if(Auth.locationdata.value?.location_5 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_5 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 6)
            {
                if(Auth.locationdata.value?.location_6 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_6 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 7)
            {
                if(Auth.locationdata.value?.location_7 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_7 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 8)
            {
                if(Auth.locationdata.value?.location_8 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_8 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 9)
            {
                if(Auth.locationdata.value?.location_9 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_9 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
            else if(cnt == 10)
            {
                if(Auth.locationdata.value?.location_10 != 0) //이미 다른 지비츠가 있을 경우
                {
                    Toast.makeText(context, "다른 지비츠가 장식중입니다. 해제후 장착해주세요.", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Auth.locationdata.value?.location_10 = item.code
                    Toast.makeText(context, "해당 위치에 장착하였습니다.", Toast.LENGTH_SHORT).show()
                    dialogListener?.onDialogOkClicked()
                    Auth.setchangeFlag()
                    dialog.cancel()
                }
            }
        }
    }
}