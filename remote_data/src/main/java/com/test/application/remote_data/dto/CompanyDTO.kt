package com.test.application.remote_data.dto

data class CompanyDTO(
    val company: CompanyXDTO = CompanyXDTO(),
    val customerMarkParameters: CustomerMarkParametersDTO = CustomerMarkParametersDTO(),
    val mobileAppDashboard: MobileAppDashboardDTO = MobileAppDashboardDTO()
)