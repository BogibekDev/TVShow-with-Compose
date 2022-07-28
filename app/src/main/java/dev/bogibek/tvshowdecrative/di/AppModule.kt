package dev.bogibek.tvshowdecrative.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.bogibek.tvshowdecrative.data.db.AppDatabase
import dev.bogibek.tvshowdecrative.data.db.TVShowDao
import dev.bogibek.tvshowdecrative.data.network.Server.IS_TESTER
import dev.bogibek.tvshowdecrative.data.network.Server.SERVER_DEPLOYMENT
import dev.bogibek.tvshowdecrative.data.network.Server.SERVER_DEVELOPMENT
import dev.bogibek.tvshowdecrative.data.network.TVSHowService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun server(): String {
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_DEPLOYMENT
    }

    @Provides
    @Singleton
    fun retrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(server())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun tvShowService(): TVSHowService {
        return retrofitClient().create(TVSHowService::class.java)
    }

    @Provides
    @Singleton
    fun appDatabase(context: Application): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun tvShowDao(appDatabase: AppDatabase): TVShowDao {
        return appDatabase.getTVShowDao()
    }
}