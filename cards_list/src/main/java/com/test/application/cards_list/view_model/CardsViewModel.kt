package com.test.application.cards_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.application.domain.Company

class CardsViewModel : ViewModel() {
    private val _cards = MutableLiveData<List<Company>>()
    val cards: LiveData<List<Company>> = _cards

    init {
        loadCards()
    }

    private fun loadCards() {
        _cards.value = listOf(
            CardData("Card 1", ...),
        CardData("Card 2", ...),
        CardData("Card 3", ...)
        )
    }
}