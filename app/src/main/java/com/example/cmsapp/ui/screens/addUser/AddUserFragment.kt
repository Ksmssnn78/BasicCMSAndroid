package com.example.cmsapp.ui.screens.addUser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cmsapp.R
import com.example.cmsapp.databinding.FragmentAddUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment(R.layout.fragment_add_user) {
    lateinit var addUserBinding: FragmentAddUserBinding

    private val viewModel: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addUserBinding = FragmentAddUserBinding.bind(view)

        addUserBinding.addUserBtnAddUser.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        var name = addUserBinding.nameTxtInputAddUser.text.toString()
        var email = addUserBinding.emailTxtInputAddUser.text.toString()
        var gender = addUserBinding.genderTxtInputAddUser.text.toString()
        var status = addUserBinding.statusTxtInputAddUser.text.toString()
        viewModel.addUser(name = name, mail = email, gender = gender, status = status)
    }
}
