package com.test.application.remote_data.dto

data class LoyaltyLevelDTO(
    val cashToMark: Int = 0,
    val markToCash: Int = 0,
    val name: String = "",
    val number: Int = 0,
    val requiredSum: Int = 0
)