package com.test.application.cards_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.test.application.repository.CardsRepository

class CardsViewModel(
    cardsRepository: CardsRepository
) : ViewModel() {

    val cardsFlow = cardsRepository.getAllCards()
        .cachedIn(viewModelScope)
}