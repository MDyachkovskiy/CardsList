package com.test.application.cards_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.application.repository.CardsListRepository

class CardsViewModel(
    cardsListRepository: CardsListRepository
) : ViewModel() {

    val cardsFlow = cardsListRepository.getPagingFlow().cachedIn(viewModelScope)

}