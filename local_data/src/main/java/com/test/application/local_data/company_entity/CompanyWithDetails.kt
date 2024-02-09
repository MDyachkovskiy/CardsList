package com.test.application.local_data.company_entity

import androidx.room.Embedded
import androidx.room.Relation

data class CompanyWithDetails(
    @Embedded val company: CompanyEntity,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val mobileAppDashboard: List<MobileAppDashboardEntity>,
    @Relation(
        entity = CustomerMarkParametersEntity::class,
        parentColumn = "companyId",
        entityColumn = "companyId"
    )
    val customerMarkParameters: List<CustomerMarkParametersWithLoyaltyLevel>
)

data class CustomerMarkParametersWithLoyaltyLevel(
    @Embedded val customerMarkParameters: CustomerMarkParametersEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "customerMarkParametersId"
    )
    val loyaltyLevel: LoyaltyLevelEntity
)
