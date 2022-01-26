package pgm.poolp.ugbuilder.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pgm.poolp.ugbuilder.data.CartRepository
import pgm.poolp.ugbuilder.data.CartWithPlayers
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

    fun delete(playerCartCrossRef: PlayerCartCrossRef) {
        viewModelScope.launch {
            cartRepository.deletePlayerCart(playerCartCrossRef)
        }
    }

    fun getPlayerCart(cardId:String): StateFlow<CartWithPlayers?> {
        return cartRepository.getCartWithPlayers(cardId).stateIn(viewModelScope, SharingStarted.Eagerly, null)
    }
/*
    fun getPlayerCart(cardId:String): LiveData<CartWithPlayers?> {
        return cartRepository.getCartWithPlayers(cardId).asLiveData()
    }
 */
}