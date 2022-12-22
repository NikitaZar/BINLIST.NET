package ru.nikitazar.binlistnet.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.nikitazar.binlistnet.dto.CardBin
import ru.nikitazar.binlistnet.dto.CardData

interface CardRepository {
    val binData: Flow<List<CardBin>>
    val cardData: StateFlow<CardData>
    suspend fun get(bin: Int, isInit: Boolean)
}