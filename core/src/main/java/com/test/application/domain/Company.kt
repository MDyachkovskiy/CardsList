package com.test.application.domain

data class Company(
    val companyId: String = "",
    val customerMarkParameters: CustomerMarkParameters = CustomerMarkParameters(),
    val mobileAppDashboard: MobileAppDashboard = MobileAppDashboard()
)
