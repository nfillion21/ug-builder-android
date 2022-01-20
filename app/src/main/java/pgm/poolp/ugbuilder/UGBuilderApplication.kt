package pgm.poolp.ugbuilder
import android.app.Application
import androidx.work.Configuration
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.rememberImagePainter
import dagger.hilt.android.HiltAndroidApp
import pgm.poolp.ugbuilder.ui.utils.UnsplashSizingInterceptor

@HiltAndroidApp
//@Suppress("unused")
class UGBuilderApplication : Application(), ImageLoaderFactory, Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            //.setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
            .build()
    /**
     * Create the singleton [ImageLoader].
     * This is used by [rememberImagePainter] to load images in the app.
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .componentRegistry {
                add(UnsplashSizingInterceptor)
            }
            .build()
    }
}
