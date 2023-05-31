package com.example.shoe_we_walk

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.shoe_we_walk.Util.setStatusBarTransparent
import com.example.shoe_we_walk.databinding.ActivityWalkBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PolylineOverlay
import com.naver.maps.map.util.FusedLocationSource

class WalkActivity : AppCompatActivity(), OnMapReadyCallback, SensorEventListener{

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private const val ACTIVITY_RECOGNITION_REQUEST_CODE = 100
    }

    private lateinit var binding: ActivityWalkBinding

    private lateinit var nMap: NaverMap
    private lateinit var locationSource: FusedLocationSource    //사용자의 위치를 받기 위한 객체

    var sensorManager: SensorManager? = null
    lateinit var stepCountSensor: Sensor
    var currentStep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setStatusBarTransparent()  //statusBar transparent

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)    //onRequestPermissionsResult 호출하게 됨 permission의 결과에 따라 달라짐

        val permission: Array<String> = arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION)

//        permission check
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACTIVITY_RECOGNITION)
            == PackageManager.PERMISSION_DENIED){ //권한이 없는 경우
//            requestPermissions(permission, 0)
            ActivityCompat.requestPermissions(this, permission, ACTIVITY_RECOGNITION_REQUEST_CODE)
        }

//        센서 세팅
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCountSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        /*Sensor.TYPE_STEP_DETECTOR : 리턴 값이 무조건 1, 앱이 종료되면 다시 0부터 시작?
        Sensor.TYPE_STEP_COUNTER : 앱 종료와 관계없이 계속 기존의 값을 가지고 있다가 1씩 증가한 값을 리턴*/

//        디바이스에 걸음 센서의 존재 여부 체크
        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Sensor", Toast.LENGTH_SHORT).show()
        }
        binding.stepCountTv.text = "0"

        binding.mapMv.onCreate(savedInstanceState)

        binding.mapMv.getMapAsync(this)

        binding.exerciseFinishBtn.setOnClickListener {//운동종료
//            운동정보를 가지고 종료해야 함! -> 시간, 거리, 걸음수를 이전 화면으로 보내줘야 함 -> 화면 이동 후 칼로리 계산
            finish()
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        nMap = naverMap
        naverMap.locationSource = locationSource

        naverMap.mapType = NaverMap.MapType.Basic
        val marker = Marker()                           //naverMap marker
        val polyline = PolylineOverlay()                //naverMap shape->PolylineOverlay
        val coords = mutableListOf<LatLng>()            //PolylineOverlay의 coords속성

//        naverMap SDK에 미리 만들어져 있는 위치 추적 기능을 켰다.
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

//        Map의 위치가 변경한 경우
        naverMap.addOnLocationChangeListener { location ->

//            사용자의 가장 마지막 위치 표시 (Marker)
            marker.position = LatLng(location.latitude, location.longitude)
            if(marker.map == null){     //marker를 맵에 표시
                marker.map = naverMap
            }

//            사용자의 이동 경로 표시 (PolylineOverlay)
            coords.add(LatLng(location.latitude, location.longitude))
            if(coords.size > 1){        //PolylineOverlay의 coords의 갯수가 2개 이상이 되지 않으면 오류가 발생한다.
                polyline.coords = coords
                if(polyline.map == null){       //PolylineOverlay를 맵에 표시
                    polyline.map = naverMap
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR){
            if(event.values[0] == 1.0f){
                currentStep++
                binding.stepCountTv.text = currentStep.toString()
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
        //        센서 동작 딜레이 설정
        if(stepCountSensor != null){
            sensorManager?.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        binding.mapMv.onResume()
        super.onResume()
    }

    override fun onPause() {
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

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}