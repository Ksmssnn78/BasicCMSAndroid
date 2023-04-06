package com.example.cmsapp.ui.screens.profiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cmsapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var profileBinding: FragmentProfileBinding

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDatas()
    }

    private fun showDatas() {
        val status = arguments?.getString("status")
        val gender = arguments?.getString("gender")
        profileBinding.idTxtProfile.setText(arguments?.getInt("userId").toString())
        profileBinding.nameTxtProfile.setText(arguments?.getString("userName"))
        profileBinding.statusTxtProfile.setText(status)
        profileBinding.genderTxtProfile.setText(gender)
    }
}
