package pgm.poolp.ugbuilder.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pgm.poolp.ugbuilder.data.Hero
import pgm.poolp.ugbuilder.data.UGBuilderRoomDatabase

class HeroDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(HERO_KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val championType = object : TypeToken<List<Hero>>() {}.type
                        val championList: List<Hero> = Gson().fromJson(jsonReader, championType)

                        val database = UGBuilderRoomDatabase.getInstance(applicationContext)
                        database.heroDao().insertAll(championList)

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "HeroDatabaseWorker"
        const val HERO_KEY_FILENAME = "HERO_DATA_FILENAME"
    }
}
