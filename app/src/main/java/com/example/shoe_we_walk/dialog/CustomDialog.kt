package com.example.shoe_we_walk.Util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.databinding.DialogWorkfinishBinding

class FinishWorkDialog(data:Work) : DialogFragment(){

    private lateinit var binding: DialogWorkfinishBinding
    private val data = data
//    lateinit var listener: DialogOKClickedListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogWorkfinishBinding.inflate(inflater, container, false)
        val (hour, min, sec) = getTime(data.work_time)
        val distance = data.work_dist
        val calorie = data.calories
        val coin = getCoin(data.step_num)

        binding.timeTv.text = String.format("%02d:%02d:%02d", hour, min, sec)
        if (distance > 1000){
            binding.distanceTv.text = String.format("%.2f km", distance/1000)
        } else{
            binding.distanceTv.text = String.format("%.2f m", distance)
        }
        binding.calorieTv.text = "$calorie kcal"
        binding.coinTv.text = "$coin coins"

        binding.updateBtn.setOnClickListener {
//            운동정보 등록
            if(Auth.loginflag){
//                registWork(WorkRegisterRequest(Auth.user_id, data.work_date, data.step_num, distance, calorie))
                Toast.makeText(requireContext(), "${data.step_num}을 걸어서 ${coin}을 얻었습니다!", Toast.LENGTH_SHORT).show()
                Auth.setCoin((Auth.coin.value!! +coin))
            } else {
                Toast.makeText(requireContext(), "로그인을 해주시기 바랍니다!", Toast.LENGTH_SHORT).show()
            }
//            listener.onOKClicked()
            dismiss()
        }

        binding.deleteBtn.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    /* 외부에서 updateBtn을 클릭했을 때 사용할 수 있게 만들어 놓은 listener
    fun setOnOKClickedListener(listener: () -> Unit){
        this.listener = object: DialogOKClickedListener{
            override fun onOKClicked() {
            }
        }
    }

    interface DialogOKClickedListener {
        fun onOKClicked()
    }*/
}

/*
class FinishWorkDialog(private val context: AppCompatActivity){
    private lateinit var binding : DialogWorkfinishBinding
    private val dlg = Dialog(context)   //부모 액티비티의 context 가 들어감

    fun show(current:String, time : String, distance: Double, step: Int, calroie: Int) {
        binding = DialogWorkfinishBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(binding.root)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함

        binding.timeTv.text = time
        if (distance > 1000){
            binding.distanceTv.text = String.format("%.2f km", distance)
        } else{
            binding.distanceTv.text = String.format("%.2f m", distance)
        }
        binding.calorieTv.text = calroie.toString() + " kcal"
        binding.coinTv.text = 0.toString() + " coins"
        //ok 버튼 동작
        binding.updateBtn.setOnClickListener {
            insertWork(user.user_id, WorkRegisterRequest(current, step, distance.toFloat(), calroie))
            dlg.dismiss()
        }
        //cancel 버튼 동작
        binding.deleteBtn.setOnClickListener {
            dlg.dismiss()
        }
        dlg.show()
    }
}*/
