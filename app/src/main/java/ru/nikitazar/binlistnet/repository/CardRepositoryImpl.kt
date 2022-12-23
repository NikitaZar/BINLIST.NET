package ru.nikitazar.binlistnet.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import ru.nikitazar.binlistnet.api.ApiService
import ru.nikitazar.binlistnet.dao.BinDao
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.*
import ru.nikitazar.binlistnet.dto.CardData
import ru.nikitazar.binlistnet.entity.BinEntity
import ru.nikitazar.binlistnet.entity.toDto
import ru.nikitazar.binlistnet.errors.*
import java.io.IOException
import java.sql.SQLException


@Singleton
class CardRepositoryImpl @Inject constructor(
    private val dao: BinDao,
    private val apiService: ApiService,
) : CardRepository {

    override val binData = dao.getAll()
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override val cardData: StateFlow<CardData>
        get() = _cardData

    private val _cardData = MutableStateFlow(CardData())

    override suspend fun get(bin: Int, isInit: Boolean) {
        try {
            val response = apiService.get(bin)
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            _cardData.emit(body)

            if (!isInit) {
                dao.insert(BinEntity(0, bin))
            }
        } catch (e: ApiError) {
            throw e
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: SQLException) {
            throw DbError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}