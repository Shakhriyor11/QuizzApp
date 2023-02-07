package com.portfolio.quizzapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.portfolio.quizzapp.R
import com.portfolio.quizzapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
    get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            redirect("MAIN")
        }
        binding.btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }
    }

    private fun redirect(name: String) {
        when(name) {
            "LOGIN" -> launchLogInFragment()
            "MAIN" -> launchMainFragment()
            else -> throw RuntimeException("No path exists")
        }
    }

    private fun launchMainFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchLogInFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, LogInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}