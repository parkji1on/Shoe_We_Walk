package com.example.shoe_we_walk.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoe_we_walk.Data.Auth
import com.example.shoe_we_walk.Data.Auth.nickname
import com.example.shoe_we_walk.Data.Auth.profileImage
import com.example.shoe_we_walk.EditprofileActivity
import com.example.shoe_we_walk.Util.CircleTransformation
import com.example.shoe_we_walk.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val titlename :TextView = binding.ProfileUserTitleText
        titlename.text = nickname
        val imageView : ImageView = binding.ProfileUserImage
        Picasso.get()
            .load(profileImage)
            .transform(CircleTransformation())
            .into(imageView)

        val editbtn :ImageView = binding.ProfileEditImage
        editbtn.setOnClickListener {
            val intent = Intent(activity, EditprofileActivity::class.java)
            startActivity(intent)
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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}