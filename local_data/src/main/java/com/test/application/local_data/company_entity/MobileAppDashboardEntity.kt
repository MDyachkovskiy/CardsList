package com.test.application.local_data.company_entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "mobileAppDashboards",
    foreignKeys = [
        ForeignKey(
            entity = CompanyEntity::class,
            parentColumns = ["companyId"],
            childColumns = ["companyId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MobileAppDashboardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val companyId: String,
    val accentColor: String = "",
    val backgroundColor: String = "",
    val cardBackgroundColor: String = "",
    val companyName: String = "",
    val highlightTextColor: String = "",
    val logo: String = "",
    val mainColor: String = "",
    val textColor: String = ""
)
