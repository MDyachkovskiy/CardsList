package com.test.application.local_data.company_entity

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

interface CompanyDao {

    @Transaction
    suspend fun insertCompanyWithDetails(
        companyEntity: CompanyEntity,
        customerMarkParametersEntity: CustomerMarkParametersEntity,
        loyaltyLevelEntity: LoyaltyLevelEntity,
        mobileAppDashboardEntity: MobileAppDashboardEntity
    ) {
        insertCompany(companyEntity)
        insertCustomerMarkParameters(customerMarkParametersEntity)
        insertLoyaltyLevel(loyaltyLevelEntity)
        insertMobileAppDashboard(mobileAppDashboardEntity)
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompany(companyEntity: CompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomerMarkParameters(customerMarkParametersEntity: CustomerMarkParametersEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoyaltyLevel(loyaltyLevelEntity: LoyaltyLevelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMobileAppDashboard(mobileAppDashboardEntity: MobileAppDashboardEntity)

    @Transaction
    @Query("SELECT * FROM companies WHERE companyId = :companyId")
    suspend fun getCompanyWithDetails(companyId: String): CompanyWithDetails
}