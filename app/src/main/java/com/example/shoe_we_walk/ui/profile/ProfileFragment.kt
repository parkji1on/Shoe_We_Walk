package com.example.shoe_we_walk.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.Retrofit.getWeekWork
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var current: LocalDateTime

    private lateinit var data: ArrayList<BarEntry>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titlename :TextView = binding.root.findViewById(R.id.ProfileUserTitleText)
        titlename.text = nickname
        val imageView : ImageView = binding.root.findViewById(R.id.ProfileUserImage)
        Picasso.get()
            .load(profileImage)
            .transform(CircleTransformation())
            .into(imageView)

//        날짜 설정
        getDate()



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        서버와 통신 -> 현재 날짜와 user_id를 보내면 반환문으로 그에 대한 데이터가 들어온다. -> 가장 처음 들어왔을 때는 1주일치에 대한 데이터
        binding.periodSelectBtn.setOnClickListener{
            getWeekWork(user.user_id, completion = { weekStepNum, s ->
                data = weekStepNum.changeData()
                makeChart(binding.workBchart, 0, data)
            })
        }
//        그래프 생성 -> 0(일), 1(주), 2(월) 별로 그래프를 생성함
//        makeChart(binding.workBchart, 0, arrayListOf(BarEntry(0f, 0f)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    그래프 생성
    fun makeChart(chart: BarChart, period:Int = 0, data:ArrayList<BarEntry>){
//    데이터 갱신
        val entries = data
        /*val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f,20.0f))
        entries.add(BarEntry(2f,70.0f))
        entries.add(BarEntry(3f,30.0f))
        entries.add(BarEntry(4f,90.0f))
        entries.add(BarEntry(5f,70.0f))
        entries.add(BarEntry(6f,30.0f))
        entries.add(BarEntry(7f,90.0f))*/
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
                axisMaximum = 101f //100 위치에 선을 그리기 위해 101f로 맥시멈값 설정
                axisMinimum = 0f // 최소값 0
                granularity = 50f // 50 단위마다 선을 그리려고 설정.

                setDrawLabels(true) // 값 적는거 허용 (0, 50, 100)
                setDrawGridLines(true) //격자 라인 활용
                setDrawAxisLine(false) // 축 그리기 설정
//                색 재지정 바람 -> res/color에 지정해 놓고 나중에 가져와 쓰기
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

    fun getDate(){
        current = LocalDateTime.now()
        binding.chartDateTv.text = current.format(DateTimeFormatter.ISO_DATE)
    }
}