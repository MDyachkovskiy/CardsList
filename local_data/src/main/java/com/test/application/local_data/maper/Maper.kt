package com.test.application.local_data.maper

import com.test.application.domain.Company
import com.test.application.domain.CustomerMarkParameters
import com.test.application.domain.LoyaltyLevel
import com.test.application.domain.MobileAppDashboard
import com.test.application.local_data.company_entity.CompanyWithDetails
import com.test.application.local_data.company_entity.CustomerMarkParametersWithLoyaltyLevel
import com.test.application.local_data.company_entity.LoyaltyLevelEntity
import com.test.application.local_data.company_entity.MobileAppDashboardEntity

fun CompanyWithDetails.toDomain(): Company {
    return Company(
        companyId = company.companyId,
        customerMarkParameters = customerMarkParameters.first().toDomain(),
        mobileAppDashboard = mobileAppDashboard.first().toDomain()
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