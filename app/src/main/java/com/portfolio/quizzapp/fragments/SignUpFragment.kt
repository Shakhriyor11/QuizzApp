package com.portfolio.quizzapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.portfolio.quizzapp.R
import com.portfolio.quizzapp.activities.MainActivity
import com.portfolio.quizzapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
    get() = _binding ?: throw RuntimeException("FragmentSignUpBinding == null")

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {
            signUpUser()
        }
        binding.btnLogin.setOnClickListener {
            launchLogInFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun signUpUser(){
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Email and Password can't be blank",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(
                requireContext(),
                "Password and Confirm Password do not match",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) {
                if(it.isSuccessful){
                    Toast.makeText(
                        requireContext(),
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    launchMainFragment()
//                    finish()
                }
                else{
                    Toast.makeText(requireContext(), "Error creating user.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun launchLogInFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, LogInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }
}