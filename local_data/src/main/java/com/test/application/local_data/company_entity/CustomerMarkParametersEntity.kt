package com.test.application.local_data.company_entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "customerMarkParameters",
    foreignKeys = [
        ForeignKey(
            entity = CompanyEntity::class,
            parentColumns = ["companyId"],
            childColumns = ["companyId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CustomerMarkParametersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val companyId: String,
    val mark: Int = 0
)
