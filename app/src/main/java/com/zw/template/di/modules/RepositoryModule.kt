package com.zw.template.di.modules

import com.zw.template.api.RetrofitService
import com.zw.template.repositories.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideLoginRepository(retrofitService: RetrofitService): LoginRepository {
        return LoginRepository(retrofitService)
    }
}