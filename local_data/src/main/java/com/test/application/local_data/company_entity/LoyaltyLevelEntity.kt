package com.test.application.local_data.company_entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "loyaltyLevels",
    foreignKeys = [
        ForeignKey(
            entity = CustomerMarkParametersEntity::class,
            parentColumns = ["id"],
            childColumns = ["customerMarkParametersId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LoyaltyLevelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerMarkParametersId: Int,
    val cashToMark: Int = 0,
    val markToCash: Int = 0,
    val name: String = "",
    val number: Int = 0,
    val requiredSum: Int = 0
)
