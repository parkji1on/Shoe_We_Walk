package com.example.shoe_we_walk.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoe_we_walk.MainActivity
import com.example.shoe_we_walk.R
import com.example.shoe_we_walk.databinding.FragmentProfileBinding
import com.example.shoe_we_walk.ui.home.CircleTransformation
import com.squareup.picasso.Picasso

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

        val titlename :TextView = binding.root.findViewById(R.id.ProfileUserTitleText)
        titlename.text = MainActivity.nickname
        val imageView : ImageView = binding.root.findViewById(R.id.ProfileUserImage)
        Picasso.get()
            .load(MainActivity.profileImage)
            .transform(CircleTransformation())
            .into(imageView)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}