package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pgm.poolp.ugbuilder.database.Hero
import pgm.poolp.ugbuilder.database.HeroRepository
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject internal constructor(
    heroRepository: HeroRepository) : ViewModel() {
    val allHeroes: LiveData<List<Hero>> = heroRepository.allHeroes.asLiveData()
}