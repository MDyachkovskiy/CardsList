package com.test.application.local_data.company_entity

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithDetails(
    @Embedded val company: CompanyEntity,

    @Relation(
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val mobileAppDashboard: MobileAppDashboardEntity,

    @Relation(
        entity = CustomerMarkParametersEntity::class,
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val customerMarkParameters: CustomerMarkParametersEntity,

    @Relation(
        entity = LoyaltyLevelEntity::class,
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val loyaltyLevels: LoyaltyLevelEntity
)


