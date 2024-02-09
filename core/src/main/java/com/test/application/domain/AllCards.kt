package com.test.application.domain

data class AllCards(
    val companies: List<Company>,
    val limit: Int,
    val offset: Int
)
