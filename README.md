# Heroes
<https://play.google.com/store/apps/details?id=pgm.poolp.ugbuilder>

A Jetpack compose app that features some Blood Bowl characters.

Libraries and components used
--------------
* [Coroutine Worker][0] - Used to load JSON file and fill database asynchronously.
* [Room][1] - Access the app's SQLite database with in-app objects and compile-time checks.
* [Preferences DataStore][2] - Used to save preferences asynchronously thanks to Kotlin flows.
* [Hilt][3] for [dependency injection][4]
* [Coil][5] - Used to load images from network.

[0]: https://developer.android.com/topic/libraries/architecture/workmanager/advanced/coroutineworker
[1]: https://developer.android.com/topic/libraries/architecture/room
[2]: https://developer.android.com/topic/libraries/architecture/datastore
[3]: https://developer.android.com/training/dependency-injection/hilt-android
[4]: https://developer.android.com/training/dependency-injection
[5]: https://coil-kt.github.io/coil/compose/


