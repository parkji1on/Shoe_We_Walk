package com.example.shoe_we_walk

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.MessageResponse
import com.example.shoe_we_walk.Data.Work
import com.example.shoe_we_walk.Data.WorkRegisterRequest
import com.example.shoe_we_walk.Retrofit.RetrofitClient
import com.example.shoe_we_walk.Util.getCalorie
import com.example.shoe_we_walk.Util.getDistance
import com.example.shoe_we_walk.Util.setStatusBarTransparent
import com.example.shoe_we_walk.databinding.ActivityWalkBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.PolylineOverlay
import com.naver.maps.map.util.FusedLocationSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class WalkActivity : AppCompatActivity(), OnMapReadyCallback, SensorEventListener{
    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        const val ACTIVITY_RECOGNITION_REQUEST_CODE = 100
    }

    private lateinit var binding: ActivityWalkBinding
    private lateinit var nMap: NaverMap
    private lateinit var locationSource: FusedLocationSource    //사용자의 위치를 받기 위한 객체
    private var sensorManager: SensorManager? = null
    private lateinit var stepCountSensor: Sensor

    private var currentStep = 0                                         //총 걸음수
    private val startTime = SystemClock.elapsedRealtime()       //걸린 총 시간 (다른 방법 : private val startTime = System.currentTimeMillis())
    private var totalTime: Long = 0
    private var totalDistance:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setStatusBarTransparent()          //statusBar transparent

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)    //onRequestPermissionsResult 호출하게 됨 permission의 결과에 따라 달라짐

//        permission check
        val permission: Array<String> = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACTIVITY_RECOGNITION)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_DENIED
            ||ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_DENIED
            ||ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACTIVITY_RECOGNITION)
            == PackageManager.PERMISSION_DENIED){ // 셋 중 하나라도 권한이 없는 경우
//            requestPermissions(permission, 0)
            ActivityCompat.requestPermissions(this, permission, ACTIVITY_RECOGNITION_REQUEST_CODE)          // permission request
        }

//        센서 세팅
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)!!
        /*Sensor.TYPE_STEP_DETECTOR : 리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작?
        Sensor.TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴*/

//        디바이스에 걸음 센서의 존재 여부 체크
        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show()
        }
        binding.stepCountTv.text = "0걸음"
        binding.stepCountTv.bringToFront()

        binding.mapMv.onCreate(savedInstanceState)

        binding.mapMv.getMapAsync(this)

        binding.exerciseFinishBtn.setOnClickListener {//운동종료
//            운동정보를 가지고 종료해야 함! -> 시간, 거리, 걸음수를 이전 화면으로 보내줘야 함 -> 화면 이동 후 칼로리 계산
            totalTime = SystemClock.elapsedRealtime() - startTime           //ms단위로 나온다.

            registWork(WorkRegisterRequest(Auth.user_id, getCurrentDateTime(), currentStep, totalDistance.toFloat(),getCalorie(Auth.weight.value!!, (totalTime/60000).toInt())))

            val resultIntent = Intent()
            resultIntent.putExtra("총 시간", totalTime.toInt())
            resultIntent.putExtra("총 거리", totalDistance)
            resultIntent.putExtra("총 걸음수", currentStep)

            setResult(Activity.RESULT_OK, resultIntent)

//            Timer().schedule(1000){}        //1초 뒤에 {}안의 내용 실행
            finish()

        }


    }

    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDate = Date(System.currentTimeMillis())
        return dateFormat.format(currentDate)
    }

    override fun onMapReady(naverMap: NaverMap) {
        nMap = naverMap
        naverMap.locationSource = locationSource

        naverMap.mapType = NaverMap.MapType.Basic
        naverMap.moveCamera(CameraUpdate.zoomTo(18.0))


//        val marker = Marker()                           //naverMap marker
        val polyline = PolylineOverlay()                //naverMap shape->PolylineOverlay
        val coords = mutableListOf<LatLng>()            //PolylineOverlay의 coords속성

//        naverMap SDK에 미리 만들어져 있는 위치 추적 기능을 켰다.
        naverMap.locationTrackingMode = LocationTrackingMode.Follow


//        Map의 위치가 변경한 경우
        naverMap.addOnLocationChangeListener { location ->
            naverMap.moveCamera(CameraUpdate.scrollTo(LatLng(location.latitude, location.longitude)).animate(CameraAnimation.Easing))

//
            val colorHex = "#EC008C"
            val color = Color.parseColor(colorHex)
//            사용자의 이동 경로 표시 (PolylineOverlay)
            coords.add(LatLng(location.latitude, location.longitude))
            if(coords.size > 1){        //PolylineOverlay의 coords의 갯수가 2개 이상이 되지 않으면 오류가 발생한다.
                polyline.coords = coords
                if(polyline.map == null){       //PolylineOverlay를 맵에 표시
                    polyline.width = 30
                    polyline.capType = PolylineOverlay.LineCap.Round
                    polyline.joinType = PolylineOverlay.LineJoin.Round
                    polyline.color = color
                    polyline.map = naverMap
                }

//                총 거리 계산
                val index = coords.size-1
                totalDistance += getDistance(coords[index-1].latitude,coords[index-1].longitude,coords[index].latitude,coords[index].longitude)
            }
        }


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR){           //걸음수가 측정될 때 마다 currentStep을 증가시킴
            if(event.values[0] == 1.0f){
                currentStep++
                binding.stepCountTv.text = currentStep.toString() + "걸음"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onStart() {
        super.onStart()
        binding.mapMv.onStart()
    }

    override fun onResume() {
//         센서 동작 딜레이 설정 -> 반드시 설정해줘야한다 안하면 제대로 동작 X
        if(stepCountSensor != null){
            sensorManager?.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        binding.mapMv.onResume()
        super.onResume()
    }

    override fun onPause() {
//        step sensor 해제
        sensorManager?.unregisterListener(this)
        binding.mapMv.onPause()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapMv.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapMv.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapMv.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapMv.onLowMemory()
    }

    //permission check
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        위치에 관한 permission
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            if(!locationSource.isActivated){    //위치추적 거절됨
                nMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }

        if (requestCode == ACTIVITY_RECOGNITION_REQUEST_CODE) {
            if (grantResults.isNotEmpty()) {
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) Toast.makeText(this, "권한 허용이 필요한 작업입니다 - 작동 불가!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    //work 정보 등록하기
    fun registWork(workRegisterRequest : WorkRegisterRequest){
        RetrofitClient.getRetrofitService.registWork(workRegisterRequest).enqueue(object :
            Callback<MessageResponse> {
            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                if(response.isSuccessful()){
                    Log.d("Response:", response.body().toString())
                }
                else
                {
                    Log.d("Response FAILURE", response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Log.d("CONNECTION FAILURE :", t.localizedMessage?:"Null")
            }
        })
    }
}