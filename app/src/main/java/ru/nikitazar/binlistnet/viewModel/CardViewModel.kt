package ru.nikitazar.binlistnet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nikitazar.binlistnet.repository.CardRepository
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    val cardData = cardRepository.cardData.asLiveData()
    val binData = cardRepository.binData.asLiveData()

    init {
        viewModelScope.launch {
            val lastBin = binData.value?.get(0)?.bin ?: 45717360
            cardRepository.get(bin = lastBin, isInit = true)
        }
    }

    fun get(bin: Int) = viewModelScope.launch {
        cardRepository.get(bin = bin, isInit = false)
    }
}