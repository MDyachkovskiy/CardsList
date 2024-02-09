package com.test.application.local_data.maper

import com.test.application.domain.Company
import com.test.application.domain.CustomerMarkParameters
import com.test.application.domain.LoyaltyLevel
import com.test.application.domain.MobileAppDashboard
import com.test.application.local_data.company_entity.CompanyEntity
import com.test.application.local_data.company_entity.CompanyWithDetails
import com.test.application.local_data.company_entity.CustomerMarkParametersEntity
import com.test.application.local_data.company_entity.CustomerMarkParametersWithLoyaltyLevel
import com.test.application.local_data.company_entity.LoyaltyLevelEntity
import com.test.application.local_data.company_entity.MobileAppDashboardEntity

fun CompanyWithDetails.toDomain(): Company {
    return Company(
        companyId = company.companyId,
        customerMarkParameters = customerMarkParameters.toDomain(),
        mobileAppDashboard = mobileAppDashboard.toDomain()
    )
}

fun CustomerMarkParametersWithLoyaltyLevel.toDomain(): CustomerMarkParameters {
    return CustomerMarkParameters(
        loyaltyLevel = loyaltyLevel.toDomain(),
        mark = customerMarkParameters.mark
    )
}

fun LoyaltyLevelEntity.toDomain(): LoyaltyLevel {
    return LoyaltyLevel(
        cashToMark = cashToMark,
        markToCash = markToCash,
        name = name,
        number = number,
        requiredSum = requiredSum
    )
}

fun MobileAppDashboardEntity.toDomain(): MobileAppDashboard {
    return MobileAppDashboard(
        accentColor = accentColor,
        backgroundColor = backgroundColor,
        cardBackgroundColor = cardBackgroundColor,
        companyName = companyName,
        highlightTextColor = highlightTextColor,
        logo = logo,
        mainColor = mainColor,
        textColor = textColor
    )
}

fun Company.toEntity(): CompanyEntity{
    return CompanyEntity(
        companyId = this.companyId
    )
}

fun CustomerMarkParameters.toEntity(companyId: String): CustomerMarkParametersEntity {
    return CustomerMarkParametersEntity(
        companyId = companyId,
        mark = this.mark
    )
}

fun LoyaltyLevel.toEntity(companyId: Int): LoyaltyLevelEntity {
    return LoyaltyLevelEntity(
        customerMarkParametersId = companyId,
        cashToMark = this.cashToMark,
        markToCash = this.markToCash,
        name = this.name,
        number = this.number,
        requiredSum = this.requiredSum
    )
}

fun MobileAppDashboard.toEntity(companyId: String): MobileAppDashboardEntity {
    return MobileAppDashboardEntity(
        companyId = companyId,
        accentColor = this.accentColor,
        backgroundColor = this.backgroundColor,
        cardBackgroundColor = this.cardBackgroundColor,
        companyName = this.companyName,
        highlightTextColor = this.highlightTextColor,
        logo = this.logo,
        mainColor = this.mainColor,
        textColor = this.textColor
    )
}