package ml.zedlabs.tvtracker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.domain.repository.AppCommonsRepository
import ml.zedlabs.domain.repository.MovieRepository
import ml.zedlabs.domain.repository.TvRepository
import ml.zedlabs.domain.usecases.*
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

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        movieRepository: MovieRepository
    ) : GetMovieDetailUseCase {
        return GetMovieDetailUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetTvDetailUseCase(
        tvRepository: TvRepository
    ) : GetTvDetailUseCase {
        return GetTvDetailUseCase(tvRepository)
    }

    @Provides
    @Singleton
    fun provideGetTvListUseCase(
        tvRepository: TvRepository
    ) : GetTvListUseCase {
        return GetTvListUseCase(tvRepository)
    }

    @Provides
    @Singleton
    fun provideGetTvSeasonDetailsUseCase(
        tvRepository: TvRepository
    ) : GetTvSeasonDetailUseCase {
        return GetTvSeasonDetailUseCase(tvRepository)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(
        appCommonsRepository: AppCommonsRepository
    ) : SearchUseCase {
        return SearchUseCase(appCommonsRepository)
    }

    @Provides
    @Singleton
    fun provideUserAddedListUseCase(
        appCommonsRepository: AppCommonsRepository
    ) : GetUserAddedListUseCase {
        return GetUserAddedListUseCase(appCommonsRepository)
    }

}