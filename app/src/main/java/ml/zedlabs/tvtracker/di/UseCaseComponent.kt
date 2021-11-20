package ml.zedlabs.tvtracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.data.network.MovieApi
import ml.zedlabs.data.repository.MovieRepositoryImpl
import ml.zedlabs.domain.repository.MovieRepository
import ml.zedlabs.domain.usecases.GetMovieListUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseComponent {

    @Provides
    @Singleton
    fun provideGetMovieListUseCase(
        movieRepository: MovieRepository
    ) : GetMovieListUseCase {
        return GetMovieListUseCase(movieRepository)
    }

}