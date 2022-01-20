package pgm.poolp.ugbuilder.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideChampionDao(appDatabase: UGBuilderRoomDatabase): HeroDao {
        return appDatabase.heroDao()
    }
}
