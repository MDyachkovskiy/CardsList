package com.test.application.remote_data.dto

data class CustomerMarkParametersDTO(
    val loyaltyLevel: LoyaltyLevelDTO = LoyaltyLevelDTO(),
    val mark: Int = 0
)