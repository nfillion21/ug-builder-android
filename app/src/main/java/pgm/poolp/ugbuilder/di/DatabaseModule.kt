package pgm.poolp.ugbuilder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pgm.poolp.ugbuilder.data.CartDAO
import pgm.poolp.ugbuilder.data.HeroDao
import pgm.poolp.ugbuilder.data.UGBuilderRoomDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): UGBuilderRoomDatabase {
        return UGBuilderRoomDatabase.getInstance(context)
    }

    @Provides
    fun provideHeroDao(appDatabase: UGBuilderRoomDatabase): HeroDao {
        return appDatabase.heroDao()
    }

    @Provides
    fun provideCartDao(appDatabase: UGBuilderRoomDatabase): CartDAO {
        return appDatabase.cartDAO()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile("preferences")
            }
        )
}
