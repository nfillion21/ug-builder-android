package pgm.poolp.ugbuilder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class UGBuilderRoomDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        private var instance: UGBuilderRoomDatabase? = null

        fun getInstance(context: Context): UGBuilderRoomDatabase {
            return instance ?: synchronized(this)
            {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): UGBuilderRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UGBuilderRoomDatabase::class.java,
                DATABASE_NAME
            )
                // Wipes and rebuilds instead of migrating if no Migration object.
                // Migration is not part of this codelab.
                //.fallbackToDestructiveMigration()
                .addCallback(ChampionDatabaseCallback(context))
                .build()
        }

        private class ChampionDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                /*
                val workManager = WorkManager.getInstance(context)

                val requestChampions = OneTimeWorkRequestBuilder<ChampionDatabaseWorker>()
                    .setInputData(workDataOf(CHAMPION_KEY_FILENAME to CHAMPION_DATA_FILENAME))
                    .build()
                workManager.enqueue(requestChampions)

                val requestSkills = OneTimeWorkRequestBuilder<SkillDatabaseWorker>()
                    .setInputData(workDataOf(SKILL_KEY_FILENAME to SKILL_DATA_FILENAME))
                    .build()
                workManager.enqueue(requestSkills)

                val championsSkills = OneTimeWorkRequestBuilder<ChampionSkillDatabaseWorker>()
                    .setInputData(workDataOf(CHAMPION_SKILL_KEY_FILENAME to CHAMPION_SKILL_DATA_FILENAME))
                    .build()
                workManager.enqueue(championsSkills)
                */
            }
        }
    }
}