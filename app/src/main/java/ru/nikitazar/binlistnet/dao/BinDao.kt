package ru.nikitazar.binlistnet.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.nikitazar.binlistnet.entity.BinEntity

@Dao
interface BinDao {

    @Query("SELECT * FROM BinEntity ORDER BY id DESC")
    fun getAll(): Flow<List<BinEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bin: BinEntity): Long
}