package ml.zedlabs.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.data.local_db.AddedListDao
import ml.zedlabs.data.local_db.AddedListDatabase
import ml.zedlabs.data.network.JsonApi
import ml.zedlabs.data.network.MovieApi
import ml.zedlabs.data.network.TvApi
import ml.zedlabs.data.repository.AppCommonsRepositoryImpl
import ml.zedlabs.data.repository.MovieRepositoryImpl
import ml.zedlabs.data.repository.TvRepositoryImpl
import ml.zedlabs.domain.repository.AppCommonsRepository
import ml.zedlabs.domain.repository.MovieRepository
import ml.zedlabs.domain.repository.TvRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApi
    ): MovieRepository {
        return MovieRepositoryImpl(movieApiService)
    }

    @Provides
    @Singleton
    fun provideTvRepository(
        tvApiService: TvApi
    ): TvRepository {
        return TvRepositoryImpl(tvApiService)
    }

    @Provides
    @Singleton
    fun provideAppCommonsRepository(
        jsonApi: JsonApi
    ): AppCommonsRepository {
        return AppCommonsRepositoryImpl(jsonApi)
    }

    @Provides
    @Singleton
    fun provideAddedListDatabase(
        @ApplicationContext applicationContext: Context
    ): AddedListDatabase {
        return Room
            .databaseBuilder(
                applicationContext,
                AddedListDatabase::class.java, "added-list-db"
            ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAddedListDao(addedListDatabase: AddedListDatabase): AddedListDao {
        return addedListDatabase.addedListDao()
    }

}