package com.example.shoe_we_walk.dialog

import android.app.Dialog
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.Util.getCoin
import com.example.shoe_we_walk.Util.getTime2

class WalkFinishDialog(context : Context, data: Work) {
    private val dialog = Dialog(context, R.style.CustomDialog)
    private var context :Context = context
    private val data = data

    val timeResult = getTime2(data.work_time)
    val hour = timeResult.first
    val min = timeResult.second
    val sec = timeResult.third

    val distance = data.work_dist
    val calorie = data.calories
    val coin = getCoin(data.step_num)

    fun showDialog()
    {
        dialog.setContentView(R.layout.dialog_workfinish)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(true)
        dialog.show()

        val timeTv :TextView = dialog.findViewById(R.id.time_tv)
        val distanceTv :TextView = dialog.findViewById(R.id.distance_tv)
        val calorieTv :TextView = dialog.findViewById(R.id.calorie_tv)
        val coinTv :TextView = dialog.findViewById(R.id.coin_tv)

        val updateBtn :ImageView = dialog.findViewById(R.id.update_btn)
        val cancelBtn :ImageView = dialog.findViewById(R.id.delete_btn)

        timeTv.text = String.format("%02d:%02d:%02d", hour, min, sec)
        if (distance > 1000){
            distanceTv.text = String.format("%.2f km", distance/1000)
        } else{
            distanceTv.text = String.format("%.2f m", distance)
        }
        calorieTv.text = "$calorie kcal"
        coinTv.text = "$coin coins"

        updateBtn.setOnClickListener {
            if(Auth.loginflag){
                Toast.makeText(context, "${data.step_num}걸음을 걸어서 ${coin}개의 코인을 얻었습니다!", Toast.LENGTH_SHORT).show()
                Auth.setCoin((Auth.coin.value!! +coin))
            } else {
                Toast.makeText(context, "로그인을 하여 코인을 획득하세요!", Toast.LENGTH_SHORT).show()
            }
            dialog.cancel()
        }

        cancelBtn.setOnClickListener {
            dialog.cancel()
        }

    }

}