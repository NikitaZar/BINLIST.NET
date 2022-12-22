package ru.nikitazar.binlistnet.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.binlistnet.R
import ru.nikitazar.binlistnet.databinding.FragmentDetailsBinding
import ru.nikitazar.binlistnet.viewModel.CardViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel.cardData.observe(viewLifecycleOwner) { cardData ->
            Log.i("Details", cardData.toString())
        }

        return binding.root
    }
}