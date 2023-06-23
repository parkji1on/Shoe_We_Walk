package com.example.shoe_we_walk

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoe_we_walk.Data.UserRegisterRequest
import com.example.shoe_we_walk.Data.WorkRegisterRequest
import com.example.shoe_we_walk.Retrofit.insertUser
import com.example.shoe_we_walk.Retrofit.insertWork
import com.example.shoe_we_walk.Retrofit.selectUser
import com.example.shoe_we_walk.Retrofit.selectWork
import com.example.shoe_we_walk.Util.user
import com.example.shoe_we_walk.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.workUpdate.setOnClickListener {
            insertWork(user.user_id, WorkRegisterRequest(
                "2023-03-12 11:11:11",
                100,
                100.0f,
            13
            ))
        }

        binding.workGet.setOnClickListener {
            selectWork(user.user_id, "2023-03-11 11:11:11", "2023-03-14 11:11:11")
        }

        binding.idGet.setOnClickListener {
            selectUser(user.user_id)
        }
    }
}