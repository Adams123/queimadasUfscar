package com.ufscar.queimadas_front.di

import android.content.Context
import com.ufscar.queimadas_front.data.UserPreferences
import com.ufscar.queimadas_front.data.network.AuthAPI
import com.ufscar.queimadas_front.data.network.MapsApi
import com.ufscar.queimadas_front.data.network.RemoteDataSource
import com.ufscar.queimadas_front.data.network.UserApi
import com.ufscar.queimadas_front.data.repository.AuthRepository
import com.ufscar.queimadas_front.data.repository.MapsRepository
import com.ufscar.queimadas_front.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        userPreferences: UserPreferences
    ): RemoteDataSource {
        return RemoteDataSource(userPreferences)
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): AuthAPI {
        return remoteDataSource.buildApi(AuthAPI::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideMapsApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): MapsApi {
        return remoteDataSource.buildApi(MapsApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    fun provideAuthRepository(
        authApi: AuthAPI,
        userPreferences: UserPreferences
    ): AuthRepository {
        return AuthRepository(authApi, userPreferences)
    }

    @Provides
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository(userApi)
    }

    @Provides
    fun provideMapsRepository(mapsApi: MapsApi,
    userPreferences: UserPreferences): MapsRepository {
        return MapsRepository(mapsApi, userPreferences)
    }
}