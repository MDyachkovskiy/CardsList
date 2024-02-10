package com.test.application.local_data.company_entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CompanyDao {

    @Query("DELETE FROM companies")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(companyEntity: CompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerMarkParameters(customerMarkParametersEntity: CustomerMarkParametersEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoyaltyLevel(loyaltyLevelEntity: LoyaltyLevelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMobileAppDashboard(mobileAppDashboardEntity: MobileAppDashboardEntity)

    @Query("SELECT * FROM companies ORDER BY companyId ASC LIMIT :loadSize OFFSET :offset")
    suspend fun getCompanies(offset: Int, loadSize: Int): List<CompanyEntity>

    @Query("SELECT * FROM mobileAppDashboards WHERE companyId = :companyId")
    suspend fun getMobileAppDashboard(companyId: String): MobileAppDashboardEntity

    @Query("SELECT * FROM customerMarkParameters WHERE companyId = :companyId")
    suspend fun getCustomerMarkParameters(companyId: String): CustomerMarkParametersEntity

    @Query("SELECT * FROM loyaltyLevels WHERE companyId = :companyId")
    suspend fun getLoyaltyLevelByCustomerMarkParameters(companyId: String): LoyaltyLevelEntity

}