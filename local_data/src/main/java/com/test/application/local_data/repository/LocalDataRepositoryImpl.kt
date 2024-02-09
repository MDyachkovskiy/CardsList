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
            val customerMarkParametersEntity = company.customerMarkParameters
                .toEntity(companyId = company.companyId)
            val loyaltyLevelEntity = company.customerMarkParameters.loyaltyLevel
                .toEntity(companyId = customerMarkParametersEntity.id)
            val mobileAppDashboardEntity = company.mobileAppDashboard
                .toEntity(companyId = company.companyId)

            companyDao.insertCompanyWithDetails(
                companyEntity = companyEntity,
                customerMarkParametersEntity = customerMarkParametersEntity,
                loyaltyLevelEntity = loyaltyLevelEntity,
                mobileAppDashboardEntity = mobileAppDashboardEntity
            )
        }
    }

    override fun pagingSourceForCompanies(): PagingSource<Int, Company> {
        return CompanyPagingSource(companyDao)
    }
}