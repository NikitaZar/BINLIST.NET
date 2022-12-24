package ru.nikitazar.binlistnet.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nikitazar.binlistnet.errors.ApiError
import ru.nikitazar.binlistnet.errors.NetworkError
import ru.nikitazar.binlistnet.repository.CardRepository
import javax.inject.Inject

const val REQ_ERR = 400
const val NW_ERR = 1
const val NO_ERR = 0

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    val cardData = cardRepository.cardData.asLiveData()
    val binData = cardRepository.binData.asLiveData()

    val errState: LiveData<Int>
        get() = _errState
    private val _errState = MutableLiveData(0)

    fun get(bin: Int, isInit: Boolean) = viewModelScope.launch {
        try {
            cardRepository.get(bin = bin, isInit = isInit)
            _errState.value = NO_ERR
        } catch (e: ApiError) {
            if (e.code == REQ_ERR) {
                _errState.value = REQ_ERR
            }
        } catch (e: NetworkError) {
            _errState.value = NW_ERR
        }
    }

    fun removeById(id: Long) = viewModelScope.launch {
        cardRepository.removeById(id)
    }

    fun removeAll() = viewModelScope.launch {
        cardRepository.removeAll()
    }
}