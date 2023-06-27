package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.ItemTable
import com.example.shoe_we_walk.Data.MessageResponse
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Retrofit.errormessage
import com.example.shoe_we_walk.Retrofit.failuremessage
import com.example.shoe_we_walk.adapter.JibbitsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        ok.setOnClickListener {
            if(Auth.coin.value!! < item.price)
                Toast.makeText(context, "코인이 부족합니다.", Toast.LENGTH_SHORT).show()
            else
            {
                updateItem(Auth.user_id, item.code, 1)
            }
        }


    }

    interface OnDialogClickListener
    {
        fun onClicked(name :String)
    }

    //아이템 구매했을 때 업데이트 요청
    private fun updateItem(user_id : Long, item_id : Int, add : Int){
        RetrofitClient.getRetrofitService.updateItem(user_id, item_id, add).enqueue(object : Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>,response: Response<MessageResponse>) {
                if(response.isSuccessful()){
                    if(response.code() == 200) {
                        Toast.makeText(context, "구매완료", Toast.LENGTH_SHORT).show()
                        Auth.setCoin(Auth.coin.value!! - item.price)
                        Auth.setTotalItemCnt(Auth.total_item_cnt.value!! + 1)

                        val searchitem = Auth.itemList.find{it.user_id == Auth.user_id && it.item_id == item.code}
                        if(searchitem != null)
                            searchitem.item_cnt++

                        dialog.cancel()
                    }
                    else
                        Toast.makeText(context, errormessage, Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(context, errormessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Toast.makeText(context, failuremessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}