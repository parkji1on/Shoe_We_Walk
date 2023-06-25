package com.example.shoe_we_walk.ui.home

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.shoe_we_walk.EditshoesActivity
import com.example.shoe_we_walk.LoginActivity
import com.example.shoe_we_walk.MainActivity
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import java.util.Collections.min
import kotlin.math.min

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val loginText : TextView = binding.root.findViewById(R.id.HomeUserTitleText)
        val EditShoesText :TextView = binding.root.findViewById(R.id.HomeEditShoesText)
        val ShoesImage :ImageView = binding.root.findViewById(R.id.HomeShoesImage)

        EditShoesText.setOnClickListener{
            val intent = Intent(activity, EditshoesActivity::class.java)
            startActivity(intent)//신발 수정으로 이동
        }
        ShoesImage.setOnClickListener {
            val intent = Intent(activity, EditshoesActivity::class.java)
            startActivity(intent)//신발 수정으로 이동
        }

        loginText.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)//로그인 시작
        }

        if(MainActivity.loginflag) {
            binding.root.findViewById<TextView>(R.id.HomeUserTitleText).text = MainActivity.nickname

            val imageView :ImageView = binding.root.findViewById(R.id.HomeProfileImage)
            Picasso.get()
                .load(MainActivity.profileImage)
                .transform(CircleTransformation())
                .into(imageView)
        }

        return binding.root
    }

}

class CircleTransformation : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap !== source) {
            source.recycle()
        }

        val bitmap = Bitmap.createBitmap(size, size, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.isAntiAlias = true

        val radius = size / 2f
        canvas.drawCircle(radius, radius, radius, paint)

        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}