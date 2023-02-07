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
import com.portfolio.quizzapp.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.btnSignUp.setOnClickListener {
            launchSignUpFragment()
        }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()


        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(requireContext(), "Email/password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                launchMainFragment()
//                finish()
            } else {
                Toast.makeText(requireContext(), "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun launchMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container,MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchSignUpFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container,SignUpFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): LogInFragment {
            return LogInFragment()
        }
    }
}