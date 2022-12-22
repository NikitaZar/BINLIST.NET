package ru.nikitazar.binlistnet.hiltModules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikitazar.binlistnet.dao.BinDao
import ru.nikitazar.binlistnet.db.AppDb

@InstallIn(SingletonComponent::class)
@Module
object BinDaoModule {
    @Provides
    fun provideBinDao(db: AppDb): BinDao = db.binDao()
}