package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        heroRepository.allHeroes,
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

    private val allPlayers: Flow<List<Hero>> = heroRepository.allPlayers
    private val allPlayersOrderBySide: Flow<List<Hero>> = heroRepository.allPlayersOrderBySide
    private val allPlayersOrderByName: Flow<List<Hero>> = heroRepository.allPlayersOrderByName

    private val allExceptVillains: Flow<List<Hero>> = heroRepository.allPlayersExceptVillains
    private val allExceptVillainsOrderBySide: Flow<List<Hero>> = heroRepository.allPlayersExceptVillainsOrderBySide
    private val allExceptVillainsOrderByName: Flow<List<Hero>> = heroRepository.allPlayersExceptVillainsOrderByName

    private fun filteredPlayers(players:List<Hero>, showVillains: Boolean, sortOrder: SortOrder): List<Hero> {
        return players
    }
}