package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import pgm.poolp.ugbuilder.database.Hero
import pgm.poolp.ugbuilder.database.HeroRepository
import pgm.poolp.ugbuilder.preferences.SortOrder
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject internal constructor(
    heroRepository: HeroRepository) : ViewModel() {
    val allHeroes: LiveData<List<Hero>> = heroRepository.allHeroes.asLiveData()
    val allAllies: LiveData<List<Hero>> = heroRepository.allAllies.asLiveData()
    val allVillains: LiveData<List<Hero>> = heroRepository.allVillains.asLiveData()

    val allPlayers: LiveData<List<Hero>> = heroRepository.allPlayers.asLiveData()
    private val allPlayersOrderBySide: LiveData<List<Hero>> = heroRepository.allPlayersOrderBySide.asLiveData()
    private val allPlayersOrderByName: LiveData<List<Hero>> = heroRepository.allPlayersOrderByName.asLiveData()

    private val allExceptVillains: LiveData<List<Hero>> = heroRepository.allPlayersExceptVillains.asLiveData()
    private val allExceptVillainsOrderBySide: LiveData<List<Hero>> = heroRepository.allPlayersExceptVillainsOrderBySide.asLiveData()
    private val allExceptVillainsOrderByName: LiveData<List<Hero>> = heroRepository.allPlayersExceptVillainsOrderByName.asLiveData()

    fun filteredPlayers(showVillains:Boolean, sortOrder: SortOrder): LiveData<List<Hero>> {
        if (showVillains)
        {
            return when (sortOrder) {
                SortOrder.NONE -> allExceptVillains
                SortOrder.BY_NAME -> allExceptVillainsOrderByName
                SortOrder.BY_SIDE -> allExceptVillainsOrderBySide
                SortOrder.BY_NAME_AND_SIDE -> allExceptVillainsOrderBySide
            }
        }
        else
        {
            return when (sortOrder) {
                SortOrder.NONE -> allPlayers
                SortOrder.BY_NAME -> allPlayersOrderByName
                SortOrder.BY_SIDE -> allPlayersOrderBySide
                SortOrder.BY_NAME_AND_SIDE -> allPlayersOrderBySide
            }
        }
        // sort the tasks
        /*
        return when (sortOrder) {
            SortOrder.NONE -> filteredTasks
            SortOrder.BY_DEADLINE -> filteredTasks.sortedByDescending { it.deadline }
            SortOrder.BY_PRIORITY -> filteredTasks.sortedBy { it.priority }
            SortOrder.BY_DEADLINE_AND_PRIORITY -> filteredTasks.sortedWith(
                compareByDescending<Task> { it.deadline }.thenBy { it.priority }
            )
        }
        */
    }

    /*
private fun filterHeroes(
    tasks: List<Task>,
    showCompleted: Boolean,
    sortOrder: SortOrder
): List<Task> {
    // filter the tasks
    val filteredTasks = if (showCompleted) {
        tasks
    } else {
        tasks.filter { !it.completed }
    }
    // sort the tasks
    return when (sortOrder) {
        SortOrder.NONE -> filteredTasks
        SortOrder.BY_DEADLINE -> filteredTasks.sortedByDescending { it.deadline }
        SortOrder.BY_PRIORITY -> filteredTasks.sortedBy { it.priority }
        SortOrder.BY_DEADLINE_AND_PRIORITY -> filteredTasks.sortedWith(
            compareByDescending<Task> { it.deadline }.thenBy { it.priority }
        )
    }
}
     */
}