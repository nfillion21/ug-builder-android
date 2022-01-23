package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import pgm.poolp.ugbuilder.database.Hero
import pgm.poolp.ugbuilder.database.HeroRepository
import pgm.poolp.ugbuilder.preferences.SortOrder
import pgm.poolp.ugbuilder.preferences.UserPreferences
import pgm.poolp.ugbuilder.preferences.UserPreferencesRepositoryImpl
import javax.inject.Inject

data class PlayersUiModel(
    val players: List<Hero>,
    val showVillains: Boolean,
    val sortOrder: SortOrder
)

@HiltViewModel
class HeroViewModel @Inject internal constructor(
    heroRepository: HeroRepository,
    private val userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
) : ViewModel()
{
    val allHeroes: LiveData<List<Hero>> = heroRepository.allHeroes.asLiveData()
    val allAllies: LiveData<List<Hero>> = heroRepository.allAllies.asLiveData()
    val allVillains: LiveData<List<Hero>> = heroRepository.allVillains.asLiveData()

    // Every time the sort order, the show completed filter or the list of tasks emit,
    // we should recreate the list of tasks
    private val playersUiModelFlow = combine(
        heroRepository.allPlayers,
        userPreferencesRepositoryImpl.userPreferencesFlow
    ) { players: List<Hero>, userPreferences: UserPreferences ->
        return@combine PlayersUiModel(
            players = filteredPlayers(
                players = players,
                showVillains = userPreferences.showVillains,
                sortOrder = userPreferences.sortOrder
            ),
            showVillains = userPreferences.showVillains,
            sortOrder = userPreferences.sortOrder
        )
    }

    val playersUiModel:StateFlow<PlayersUiModel?> = playersUiModelFlow.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        null)

    private fun filteredPlayers(players:List<Hero>, showVillains: Boolean, sortOrder: SortOrder): List<Hero> {
        val filtered = if (showVillains) players.filter { it.side == "Villain" } else players
        return filtered.sortedWith(sortOrder.comparator)
    }

    fun showCompletedTasks(show: Boolean) {
        viewModelScope.launch {
            userPreferencesRepositoryImpl.updateShowVillains(show)
        }
    }

    fun enableSortByName(enable: Boolean) {
        viewModelScope.launch {
            userPreferencesRepositoryImpl.enableSortByName(enable)
        }
    }

    fun enableSortByPrice(enable: Boolean) {
        viewModelScope.launch {
            userPreferencesRepositoryImpl.enableSortByPrice(enable)
        }
    }
}