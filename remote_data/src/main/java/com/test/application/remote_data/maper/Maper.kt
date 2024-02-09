package com.test.application.remote_data.maper

import com.test.application.domain.Company
import com.test.application.domain.CustomerMarkParameters
import com.test.application.domain.LoyaltyLevel
import com.test.application.domain.MobileAppDashboard
import com.test.application.remote_data.dto.CompanyDTO
import com.test.application.remote_data.dto.CustomerMarkParametersDTO
import com.test.application.remote_data.dto.LoyaltyLevelDTO
import com.test.application.remote_data.dto.MobileAppDashboardDTO

fun CompanyDTO.toDomain(): Company {
    return Company(
        companyId = company.companyId,
        customerMarkParameters = customerMarkParameters.toDomain(),
        mobileAppDashboard = mobileAppDashboard.toDomain()
    )
}

fun CustomerMarkParametersDTO.toDomain(): CustomerMarkParameters {
    return CustomerMarkParameters(
        loyaltyLevel = loyaltyLevel.toDomain(),
        mark = mark
    )
}

fun LoyaltyLevelDTO.toDomain(): LoyaltyLevel {
    return LoyaltyLevel(
        cashToMark = cashToMark,
        markToCash = markToCash,
        name = name,
        number = number,
        requiredSum = requiredSum
    )
}

fun MobileAppDashboardDTO.toDomain(): MobileAppDashboard {
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