package pgm.poolp.ugbuilder.preferences

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

enum class SortOrder {
    NONE,
    BY_NAME,
    BY_SIDE,
    BY_NAME_AND_SIDE
}

data class UserPreferences(
    val showVillains: Boolean,
    val sortOrder: SortOrder
)

/**
 * Class that handles saving and retrieving user preferences
 */
class UserPreferencesRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "UserPreferencesRepo"

    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val SHOW_VILLAINS = booleanPreferencesKey("show_villains")
    }

    /**
     * Get the user preferences flow.
     */
    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    /**
     * Enable / disable sort by deadline.
     */
    suspend fun enableSortByName(enable: Boolean) {
        // updateData handles data transactionally, ensuring that if the sort is updated at the same
        // time from another thread, we won't have conflicts
        dataStore.edit { preferences ->
            val currentOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
            )

            val newSortOrder =
                if (enable) {
                    if (currentOrder == SortOrder.BY_SIDE) {
                        SortOrder.BY_NAME_AND_SIDE
                    } else {
                        SortOrder.BY_NAME
                    }
                } else {
                    if (currentOrder == SortOrder.BY_NAME_AND_SIDE) {
                        SortOrder.BY_SIDE
                    } else {
                        SortOrder.NONE
                    }
                }

            preferences[PreferencesKeys.SORT_ORDER] = newSortOrder.name
        }
    }

    /**
     * Enable / disable sort by priority.
     */
    suspend fun enableSortBySide(enable: Boolean) {
        // updateData handles data transactionally, ensuring that if the sort is updated at the same
        // time from another thread, we won't have conflicts
        dataStore.edit { preferences ->
            val currentOrder = SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
            )

            val newSortOrder =
                if (enable) {
                    if (currentOrder == SortOrder.BY_NAME) {
                        SortOrder.BY_NAME_AND_SIDE
                    } else {
                        SortOrder.BY_SIDE
                    }
                } else {
                    if (currentOrder == SortOrder.BY_NAME_AND_SIDE) {
                        SortOrder.BY_NAME
                    } else {
                        SortOrder.NONE
                    }
                }

            preferences[PreferencesKeys.SORT_ORDER] = newSortOrder.name
        }
    }

    suspend fun updateShowVillains(showVillains: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOW_VILLAINS] = showVillains
        }
    }

    suspend fun fetchInitialPreferences() =
        mapUserPreferences(dataStore.data.first().toPreferences())

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        // Get the sort order from preferences and convert it to a [SortOrder] object
        val sortOrder =
            SortOrder.valueOf(
                preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name
            )

        // Get our show completed value, defaulting to false if not set:
        val showCompleted = preferences[PreferencesKeys.SHOW_VILLAINS] ?: false
        return UserPreferences(showCompleted, sortOrder)
    }
}
