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
import pgm.poolp.ugbuilder.data.Cart
import pgm.poolp.ugbuilder.data.Hero
import pgm.poolp.ugbuilder.data.UGBuilderRoomDatabase

class CartDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(CART_KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val cartType = object : TypeToken<List<Cart>>() {}.type
                        val cartList: List<Cart> = Gson().fromJson(jsonReader, cartType)

                        val database = UGBuilderRoomDatabase.getInstance(applicationContext)
                        database.cartDAO().insertAll(cartList)

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
        private const val TAG = "CartDatabaseWorker"
        const val CART_KEY_FILENAME = "CART_DATA_FILENAME"
    }
}
