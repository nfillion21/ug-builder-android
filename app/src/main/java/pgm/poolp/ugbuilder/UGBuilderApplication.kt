
import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.rememberImagePainter
import pgm.poolp.ugbuilder.ui.utils.UnsplashSizingInterceptor

@Suppress("unused")
class UGBuilderApplication : Application(), ImageLoaderFactory {

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
