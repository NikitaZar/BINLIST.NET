package ru.nikitazar.binlistnet.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.nikitazar.binlistnet.R
import ru.nikitazar.binlistnet.databinding.FragmentDetailsBinding
import ru.nikitazar.binlistnet.dto.CardData
import ru.nikitazar.binlistnet.viewModel.CardViewModel

const val DEFAULT_BIN = 45717360

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: CardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        init()

        binding.btFind.setOnClickListener {
            getData(binding.editBin.text.toString()) {
                binding.editBin.setText("")
            }
        }

        viewModel.cardData.observe(viewLifecycleOwner) { cardData ->
            bind(cardData)
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun bind(cardData: CardData) {
        val blackColorId = requireContext().getColor(R.color.black)
        val defaultColorId = binding.schemeNetworkTitle.currentTextColor
        val emptyText = requireContext().getString(R.string.empty_text)

        with(binding) {

            scheme.text = emptyText
            cardData.scheme?.let {
                scheme.text = "${cardData.scheme.substring(0, 1).uppercase()}${cardData.scheme.substring(1).lowercase()}"
            }

            typeDebit.setTextColor(
                if (cardData.type == requireContext().getString(R.string.debit_text).lowercase())
                    blackColorId
                else defaultColorId
            )

            typeCredit.setTextColor(
                if (cardData.type == requireContext().getString(R.string.credit_text).lowercase())
                    blackColorId
                else defaultColorId
            )

            bankName.text = emptyText
            cardData.bank?.name?.let { name ->
                cardData.bank.city?.let { city ->
                    bankName.text = "$name, $city"
                }
            }
            bankUrl.text = cardData.bank?.url ?: emptyText
            bankUrl.isVisible = cardData.bank?.url != null
            bankPhone.text = cardData.bank?.phone ?: emptyText
            bankPhone.isVisible = cardData.bank?.phone != null

            brand.text = cardData.brand ?: emptyText

            when (cardData.prepaid) {
                true -> prepaidYes.setTextColor(blackColorId)
                false -> prepaidNo.setTextColor(blackColorId)
                else -> {
                    prepaidYes.setTextColor(defaultColorId)
                    prepaidNo.setTextColor(defaultColorId)
                }
            }

            cardNumberLength.text = emptyText
            cardData.number?.length?.let {
                cardNumberLength.text = it.toString()
            }
            when (cardData.number?.luhn) {
                true -> cardNumberLuhnYes.setTextColor(blackColorId)
                false -> cardNumberLuhnNo.setTextColor(blackColorId)
                else -> {
                    cardNumberLuhnYes.setTextColor(defaultColorId)
                    cardNumberLuhnYes.setTextColor(defaultColorId)
                }
            }

            countryName.text = emptyText
            cardData.country?.let {
                it.emoji?.let { emoji ->
                    it.name?.let { name ->
                        countryName.text = "$emoji $name"
                    }
                }
            }

            countryLatitude.text = emptyText
            cardData.country?.latitude.let {
                countryLatitude.text = it.toString()
            }

            countryLongitude.text = emptyText
            cardData.country?.longitude?.let {
                countryLongitude.text = it.toString()
            }
        }
    }

    private fun init() {
        binding.editBin.setText(DEFAULT_BIN.toString())
        viewModel.get(DEFAULT_BIN, true)
    }

    private fun getData(bin: String, act: () -> Unit) {
        try {
            val binInt = Integer.parseInt(bin)
            viewModel.get(binInt, false)
        } catch (e: java.lang.NumberFormatException) {
            act()
        }
    }
}