package com.test.application.domain

data class CustomerMarkParameters(
    val loyaltyLevel: LoyaltyLevel = LoyaltyLevel(),
    val mark: Int = 0
)
