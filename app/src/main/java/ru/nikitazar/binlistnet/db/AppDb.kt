package ru.nikitazar.binlistnet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nikitazar.binlistnet.dao.BinDao
import ru.nikitazar.binlistnet.entity.BinEntity

@Database(entities = [BinEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun binDao(): BinDao
}