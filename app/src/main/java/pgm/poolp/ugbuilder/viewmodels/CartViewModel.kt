package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pgm.poolp.ugbuilder.data.CartRepository
import pgm.poolp.ugbuilder.data.PlayerCartCrossRef
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject internal constructor(
    private val cartRepository: CartRepository
) : ViewModel()
{
    fun insert(playerCartCrossRef: PlayerCartCrossRef) {
        viewModelScope.launch {
            cartRepository.insertPlayerCart(playerCartCrossRef)
        }
    }
}