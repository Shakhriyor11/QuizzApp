package com.portfolio.quizzapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.portfolio.quizzapp.R
import com.portfolio.quizzapp.databinding.FragmentPrifileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentPrifileBinding? = null
    private val binding: FragmentPrifileBinding
    get() = _binding ?: throw  RuntimeException("FragmentPrifileBinding == null")

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPrifileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.txtEmail.text = firebaseAuth.currentUser?.email

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            launchLoginFragment()
        }
    }

    private fun launchLoginFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, LogInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }
}