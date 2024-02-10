package com.test.application.local_data.company_entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CompanyDao {

    @Query("DELETE FROM companies")
    suspend fun clearAll()

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

    @Query("SELECT * FROM companies ORDER BY companyId ASC LIMIT :loadSize OFFSET :offset")
    suspend fun getCompanies(offset: Int, loadSize: Int): List<CompanyEntity>

    @Query("SELECT * FROM mobileAppDashboards WHERE companyId = :companyId")
    suspend fun getMobileAppDashboard(companyId: String): MobileAppDashboardEntity

    @Query("SELECT * FROM customerMarkParameters WHERE companyId = :companyId")
    suspend fun getCustomerMarkParameters(companyId: String): CustomerMarkParametersEntity

    @Query("SELECT * FROM loyaltyLevels WHERE customerMarkParametersId = :customerMarkParametersId")
    suspend fun getLoyaltyLevelByCustomerMarkParameters(customerMarkParametersId: Int): LoyaltyLevelEntity

}