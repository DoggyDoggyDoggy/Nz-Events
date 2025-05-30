package denys.diomaxius.nzevents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.nzevents.data.network.EventFindApi
import denys.diomaxius.nzevents.data.network.RetrofitInstance
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEventFindApi(): EventFindApi = RetrofitInstance.api
}