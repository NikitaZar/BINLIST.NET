package ru.nikitazar.binlistnet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nikitazar.binlistnet.repository.CardRepository
import javax.inject.Inject

const val DEFAULT_BIN = 45717360

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    val cardData = cardRepository.cardData.asLiveData()
    val binData = cardRepository.binData.asLiveData()

    fun get(bin: Int, isInit: Boolean) = viewModelScope.launch {
        cardRepository.get(bin = bin, isInit = isInit)
    }

    fun removeById(id: Long) = viewModelScope.launch {
        cardRepository.removeById(id)
    }

    fun removeAll() = viewModelScope.launch {
        cardRepository.removeAll()
    }
}