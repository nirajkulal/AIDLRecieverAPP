package com.raj.reciever.di

import com.raj.reciever.data.respository.RepositoryImp
import com.raj.reciever.data.service.ServiceManger
import com.raj.reciever.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(): Repository {
        return RepositoryImp(ServiceManger())
    }
}