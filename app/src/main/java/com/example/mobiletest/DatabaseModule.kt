package com.example.mobiletest

import android.content.Context
import androidx.room.Room
import com.example.mobiletest.database.AppDatabase
import com.example.mobiletest.database.dao.TreeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tree_db"
        ).build()

    @Provides
    fun provideTreeDao(db: AppDatabase): TreeDao =
        db.treeDao()
}
