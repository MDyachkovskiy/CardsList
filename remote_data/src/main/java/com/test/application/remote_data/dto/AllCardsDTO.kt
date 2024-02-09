package com.test.application.remote_data.dto

data class AllCardsDTO(
    val companies: List<CompanyDTO> = listOf(),
    val limit: Int = 0,
    val offset: Int = 0
)