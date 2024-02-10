package com.test.application.local_data.repository

import androidx.paging.PagingSource
import com.test.application.domain.Company
import com.test.application.local_data.company_entity.CompanyDao
import com.test.application.local_data.maper.toEntity
import com.test.application.repository.LocalDataRepository

class LocalDataRepositoryImpl(
    private val companyDao: CompanyDao
): LocalDataRepository {
    override suspend fun clearAll() {
        companyDao.clearAll()
    }

    override suspend fun insertAll(companies: List<Company>) {
        companies.forEach { company ->

            val companyEntity = company.toEntity()
            companyDao.insertCompany(companyEntity)

            val customerMarkParametersEntity = company.customerMarkParameters
                .toEntity(companyId = company.companyId)
            companyDao.insertCustomerMarkParameters(customerMarkParametersEntity)


            val loyaltyLevelEntity = company.customerMarkParameters.loyaltyLevel
                .toEntity(companyId = company.companyId)
            companyDao.insertLoyaltyLevel(loyaltyLevelEntity)

            val mobileAppDashboardEntity = company.mobileAppDashboard
                .toEntity(companyId = company.companyId)
            companyDao.insertMobileAppDashboard(mobileAppDashboardEntity)
        }
    }

    override fun pagingSourceForCompanies(): PagingSource<Int, Company> {
        return CompanyPagingSource(companyDao)
    }
}