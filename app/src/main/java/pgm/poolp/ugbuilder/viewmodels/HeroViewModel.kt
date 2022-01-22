package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import pgm.poolp.ugbuilder.database.Hero
import pgm.poolp.ugbuilder.database.HeroRepository
import pgm.poolp.ugbuilder.preferences.SortOrder
import pgm.poolp.ugbuilder.preferences.UserPreferences
import pgm.poolp.ugbuilder.preferences.UserPreferencesRepository
import javax.inject.Inject

data class PlayersUiModel(
    val players: LiveData<List<Hero>>,
    val showCompleted: Boolean,
    val sortOrder: SortOrder
)

@HiltViewModel
class HeroViewModel @Inject internal constructor(
    heroRepository: HeroRepository,
    userPreferencesRepository: UserPreferencesRepository) : ViewModel()
{
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

    // Every time the sort order, the show completed filter or the list of tasks emit,
    // we should recreate the list of tasks
    private val playersUiModelFlow = combine(
        heroRepository.allPlayers,
        userPreferencesFlow
    ) { _: List<Hero>, userPreferences: UserPreferences ->
        return@combine PlayersUiModel(
            players = filteredPlayers(
                userPreferences.showVillains,
                userPreferences.sortOrder
            ),
            showCompleted = userPreferences.showVillains,
            sortOrder = userPreferences.sortOrder
        )
    }
    val playersUiModel = playersUiModelFlow.asLiveData()

    val allHeroes: LiveData<List<Hero>> = heroRepository.allHeroes.asLiveData()
    val allAllies: LiveData<List<Hero>> = heroRepository.allAllies.asLiveData()
    val allVillains: LiveData<List<Hero>> = heroRepository.allVillains.asLiveData()

    private val allPlayers: LiveData<List<Hero>> = heroRepository.allPlayers.asLiveData()
    private val allPlayersOrderBySide: LiveData<List<Hero>> =
        heroRepository.allPlayersOrderBySide.asLiveData()
    private val allPlayersOrderByName: LiveData<List<Hero>> =
        heroRepository.allPlayersOrderByName.asLiveData()

    private val allExceptVillains: LiveData<List<Hero>> =
        heroRepository.allPlayersExceptVillains.asLiveData()
    private val allExceptVillainsOrderBySide: LiveData<List<Hero>> =
        heroRepository.allPlayersExceptVillainsOrderBySide.asLiveData()
    private val allExceptVillainsOrderByName: LiveData<List<Hero>> =
        heroRepository.allPlayersExceptVillainsOrderByName.asLiveData()

    fun filteredPlayers(showVillains: Boolean, sortOrder: SortOrder): LiveData<List<Hero>> {
        return if (showVillains) {
            when (sortOrder) {
                SortOrder.NONE -> allPlayers
                SortOrder.BY_NAME -> allPlayersOrderByName
                SortOrder.BY_SIDE -> allPlayersOrderBySide
                SortOrder.BY_NAME_AND_SIDE -> allPlayersOrderBySide
            }
        } else {
            when (sortOrder) {
                SortOrder.NONE -> allExceptVillains
                SortOrder.BY_NAME -> allExceptVillainsOrderByName
                SortOrder.BY_SIDE -> allExceptVillainsOrderBySide
                SortOrder.BY_NAME_AND_SIDE -> allExceptVillainsOrderBySide
            }
        }
    }
}