package com.test.application.local_data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.application.domain.Company
import com.test.application.local_data.company_entity.CompanyDao
import com.test.application.local_data.company_entity.CompanyWithDetails
import com.test.application.local_data.company_entity.CustomerMarkParametersWithLoyaltyLevel
import com.test.application.local_data.maper.toDomain
import com.test.application.utils.INITIAL_OFFSET

class CompanyPagingSource(
    private val companyDao: CompanyDao
) : PagingSource<Int, Company>() {
    override fun getRefreshKey(state: PagingState<Int, Company>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Company> {
        val position = params.key ?: INITIAL_OFFSET

        return try {
            val companies = companyDao.getCompanies(position, params.loadSize)
            val nextKey = if (companies.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }

            val companiesWithDetails = companies.map {companyEntity ->
                val dashboard = companyDao.getMobileAppDashboard(companyEntity.companyId)

                val customerMarkParameters = companyDao
                    .getCustomerMarkParameters(companyEntity.companyId)

                val loyaltyLevel = companyDao
                    .getLoyaltyLevelByCustomerMarkParameters(customerMarkParameters.id)

                CompanyWithDetails(
                    company = companyEntity,
                    mobileAppDashboard = dashboard,
                    customerMarkParameters = CustomerMarkParametersWithLoyaltyLevel(
                        customerMarkParameters = customerMarkParameters,
                        loyaltyLevel = loyaltyLevel
                    )
                )
            }

            val domainCompanies = companiesWithDetails.map { it.toDomain() }

            LoadResult.Page(
                data = domainCompanies,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}