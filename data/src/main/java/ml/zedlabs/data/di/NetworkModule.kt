package ml.zedlabs.data.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ml.zedlabs.data.network.JsonApi
import ml.zedlabs.data.network.MovieApi
import ml.zedlabs.data.network.TvApi
import ml.zedlabs.data.util.Constants.base_url
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient{
        return OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesJsonApi(retrofit: Retrofit): JsonApi {
        return retrofit.create(JsonApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTvApi(retrofit: Retrofit): TvApi {
        return retrofit.create(TvApi::class.java)
    }
}