package com.test.application.domain

data class LoyaltyLevel(
    val cashToMark: Int = 0,
    val markToCash: Int = 0,
    val name: String = "",
    val number: Int = 0,
    val requiredSum: Int = 0
)
