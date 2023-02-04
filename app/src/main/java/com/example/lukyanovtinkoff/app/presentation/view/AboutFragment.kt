package com.example.lukyanovtinkoff.app.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.app.FilmsApplication
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModel
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModelFactory
import com.example.lukyanovtinkoff.databinding.FragmentAboutBinding
import com.example.lukyanovtinkoff.databinding.FragmentPopularBinding
import javax.inject.Inject

class AboutFragment :
    BaseFragment<AboutViewModel, FragmentAboutBinding>(R.layout.fragment_about) {

    @Inject
    lateinit var viewModelFactory: AboutViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AboutViewModel::class.java]
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}