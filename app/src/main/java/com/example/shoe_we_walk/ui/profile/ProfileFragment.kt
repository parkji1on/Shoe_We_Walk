package com.example.shoe_we_walk.ui.profile

import android.app.AlertDialog
import android.graphics.Color
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Auth.loginflag
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.Retrofit.getWeekWork
import com.example.shoe_we_walk.EditprofileActivity
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.Retrofit.getMonthWork
import com.example.shoe_we_walk.Retrofit.getYearWork
import com.example.shoe_we_walk.Util.CircleTransformation
import com.example.shoe_we_walk.Util.MyXAxisFormatter
import com.example.shoe_we_walk.Util.user
import com.example.shoe_we_walk.databinding.FragmentProfileBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.squareup.picasso.Picasso
import kotlinx.coroutines.selects.select
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var data: ArrayList<BarEntry>
    private val selectPeriod = arrayOf("일간", "주간", "월간")
    private var selectPeriodIdx: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titlename: TextView = binding.ProfileUserTitleText

        if (loginflag) {
            titlename.text = nickname
            val imageView: ImageView = binding.ProfileUserImage
            Picasso.get()
                .load(profileImage)
                .transform(CircleTransformation())
                .into(imageView)
        }
        val editbtn :ImageView = binding.ProfileEditImage
        editbtn.setOnClickListener {
            if(loginflag) {
                val intent = Intent(activity, EditprofileActivity::class.java)
                startActivity(intent)
            }
            else
                Toast.makeText(context, "로그인을 해주세요.", Toast.LENGTH_SHORT).show()
        }

        val username :TextView = binding.ProfileUserNameText
        val usergender :TextView = binding.ProfileUserGenderText
        val userage :TextView = binding.ProfileUserAgeText
        val userheight :TextView = binding.ProfileUserHightText
        val userweight :TextView = binding.ProfileUserWeightText

        Auth.user_name.observe(viewLifecycleOwner) { userName ->
            username.text = Auth.user_name.value
        }

        Auth.gender.observe(viewLifecycleOwner) { Gender ->
            if(Auth.gender.value == "male")
                usergender.text = "남성"
            else
                usergender.text = "여성"
        }

        Auth.age.observe(viewLifecycleOwner) { Age ->
            userage.text = Auth.age.value.toString() + "세"
        }

        Auth.height.observe(viewLifecycleOwner) { Height ->
            userheight.text = Auth.height.value.toString() + "cm"
        }

        Auth.weight.observe(viewLifecycleOwner) { Weight ->
            userweight.text = Auth.weight.value.toString() + "kg"
        }

//        날짜 설정
        getDate()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(loginflag){
            binding.chartTitleTv.visibility = View.VISIBLE
            binding.chartDateTv.visibility = View.VISIBLE
            binding.workBchart.visibility = View.VISIBLE
            binding.periodSelectBtn.visibility = View.VISIBLE
        } else{
            binding.chartTitleTv.visibility = View.GONE
            binding.chartDateTv.visibility = View.GONE
            binding.workBchart.visibility = View.GONE
            binding.periodSelectBtn.visibility = View.GONE
        }

        binding.chartTitleTv.text = "Activity Records"
//        서버와 통신 -> 현재 날짜와 user_id를 보내면 반환문으로 그에 대한 데이터가 들어온다. -> 가장 처음 들어왔을 때는 1주일치에 대한 데이터
        binding.periodSelectBtn.setOnClickListener{
//            Radio dialog로 일단위/주단위/월단위 데이터 선택하기
            var period: Int = 0
            var max: Float = 0f

            AlertDialog.Builder(requireContext(), R.style.RadioDialog)
                .setSingleChoiceItems(selectPeriod, selectPeriodIdx) { _, which ->
                    selectPeriodIdx = which
                    period = selectPeriodIdx
                }
                .setPositiveButton("확인") { dialog, which ->
                    when(period){
                        0 -> {
                            getWeekWork(Auth.user_id, completion = { weekStepNum, s ->
                                data = weekStepNum.changeData()
                                binding.chartTitleTv.text = "Daily Activity Records"
                                for(i in data){
                                    if(max < i.y){
                                        max = i.y
                                    }
                                }
                                makeChart(binding.workBchart, period, data, max)
                            })}
                        1 -> {
                            getMonthWork(Auth.user_id, completion = { monthStepNum, s ->
                                data = monthStepNum.changeData()
                                binding.chartTitleTv.text = "Weekly Activity Records"
                                for(i in data){
                                    if(max < i.y){
                                        max = i.y
                                    }
                                }
                                makeChart(binding.workBchart, period, data, max)
                            })
                        }
                        2 -> {
                            getYearWork(Auth.user_id, completion = { yearStepNum, s ->
                                data = yearStepNum.changeData()
                                binding.chartTitleTv.text = "Monthly Activity Records"
                                for(i in data){
                                    if(max < i.y){
                                        max = i.y
                                    }
                                }
                                makeChart(binding.workBchart, period, data, max)
                            })
                        }
                        else -> {
                            dialog.dismiss()
                            Toast.makeText(requireContext(), "잘못된 값을 입력하였습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                    dialog.dismiss()
                }
                .setCancelable(false)           //다른 곳은 못 누르게 설정
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    그래프 생성
    private fun makeChart(chart: BarChart, period:Int = 0, data:ArrayList<BarEntry>, max:Float){
//    데이터 갱신
        Log.d("이상", "makechart")
        val entries = data
        /* 1000보 / 5000보 / 10000보 / 15000보 / 20000보 그 이상일 경우를 다르게 생각해야 하는가?*/

//        데이터 가공
        chart.run {
            description.isEnabled = false // 차트 옆에 별도로 표기되는 description을 안보이게 설정 (false)
//            period에 따라 결정되게 변경
            setMaxVisibleValueCount(7) // 최대 보이는 그래프 개수를 7개로 지정
            setPinchZoom(false) // 핀치줌(두손가락으로 줌인 줌 아웃하는것) 설정
            setDrawBarShadow(false) //그래프의 그림자
            setDrawGridBackground(false)//격자구조 넣을건지
            axisLeft.run { //왼쪽 축. 즉 Y방향 축을 뜻한다.
//                입력되는 entries들의 값에 따라 변경
                if(max<1000){
                    axisMaximum = 1000f //1000 위치에 선을 그리기 위해 101f로 맥시멈값 설정
                    granularity = 200f // 200 단위마다 선을 그리려고 설정.
                }
                else{
                    axisMaximum = (max.toInt()/1000+1)*1000f
                    granularity = axisMaximum/5
                }
                axisMinimum = 0f // 최소값 0

                setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(false) // 축 그리기 설정

                axisLineColor = Color.rgb(246,246,246)                   // 축 색깔 설정
                gridColor = Color.rgb(246,246,246)                         // 축 아닌 격자 색깔 설정
                textColor = Color.rgb(246,246,246)                         // 라벨 텍스트 컬러 설정
                textSize = 13f //라벨 텍스트 크기
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM //X축을 아래에다가 둔다.
                granularity = 1f // 1 단위만큼 간격 두기
                setDrawAxisLine(true) // 축 그림
                setDrawGridLines(false) // 격자

                textColor = Color.rgb(246,246,246)                         //라벨 색상
                textSize = 12f // 텍스트 크기
//                period에 다라 변경되게 바꾸기
                valueFormatter = MyXAxisFormatter(period) // X축 라벨값(밑에 표시되는 글자) 바꿔주기 위해 설정
            }
            axisRight.isEnabled = false // 오른쪽 Y축을 안보이게 해줌.
            setTouchEnabled(false) // 그래프 터치해도 아무 변화없게 막음
            animateY(1000) // 밑에서부터 올라오는 애니매이션 적용
            legend.isEnabled = false //차트 범례 설정
        }

        var set = BarDataSet(entries,"DataSet") // 데이터셋 초기화
        set.color = Color.rgb(246,246,246)                                 // 바 그래프 색 설정

        val dataSet :ArrayList<IBarDataSet> = ArrayList()
        dataSet.add(set)
        val data = BarData(dataSet)
        data.barWidth = 0.3f //막대 너비 설정
        chart.run {
            this.data = data //차트의 데이터를 data로 설정해줌.
            setFitBars(true)
            invalidate()        //    생성
        }
    }

    private fun getDate(){
        val current = LocalDateTime.now()
        binding.chartDateTv.text = current.format(DateTimeFormatter.ISO_DATE)
    }
}