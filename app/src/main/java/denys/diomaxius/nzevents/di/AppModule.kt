package denys.diomaxius.nzevents.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import denys.diomaxius.nzevents.data.remote.api.EventsFindApi
import denys.diomaxius.nzevents.data.remote.network.RetrofitInstance
import denys.diomaxius.nzevents.data.repository.EventsRepositoryImpl
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideEventFindApi(): EventsFindApi = RetrofitInstance.api

    @Provides
    @Singleton
    fun provideEventsRepository(api: EventsFindApi): EventsRepository = EventsRepositoryImpl(api)
}