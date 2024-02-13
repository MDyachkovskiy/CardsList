package com.test.application.local_data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.InvalidationTracker
import com.test.application.domain.Company
import com.test.application.local_data.company_entity.CompanyDao
import com.test.application.local_data.company_entity.CompanyWithDetails
import com.test.application.local_data.database.LocalDatabase
import com.test.application.local_data.maper.toDomain
import com.test.application.utils.INITIAL_OFFSET

class CompanyPagingSource(
    private val companyDao: CompanyDao,
    private val database: LocalDatabase
) : PagingSource<Int, Company>() {
    init {
        database.invalidationTracker.addObserver(object : InvalidationTracker.Observer("companies"){
            override fun onInvalidated(tables: Set<String>) {
                this@CompanyPagingSource.invalidate()
                database.invalidationTracker.removeObserver(this)
            }
        })
    }
    override fun getRefreshKey(state: PagingState<Int, Company>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Company> {
        val position = params.key ?: INITIAL_OFFSET
        Log.d("@@@", "PagingSource load: position=$position, loadSize=${params.loadSize}")

        return try {
            Log.d("@@@", "Attempting to load companies from DB")
            val companies = companyDao.getCompanies(position, params.loadSize)
            Log.d("@@@", "Successfully loaded ${companies.size} companies from the database.")
            val nextKey = if (companies.isEmpty()) {
                null
            } else {
                position + params.loadSize
            }

            val companiesWithDetails = companies.map {companyEntity ->
                Log.d("@@@", "Processing companyEntity: ${companyEntity.companyId}")
                val dashboard = companyDao
                    .getMobileAppDashboard(companyEntity.companyId)
                Log.d("@@@", "Dashboard for companyId ${companyEntity.companyId}: $dashboard")

                val customerMarkParameters = companyDao
                    .getCustomerMarkParameters(companyEntity.companyId)
                Log.d("@@@", "CustomerMarkParameters for companyId ${companyEntity.companyId}: $customerMarkParameters")

                val loyaltyLevel = companyDao
                    .getLoyaltyLevelByCustomerMarkParameters(companyEntity.companyId)
                Log.d("@@@", "LoyaltyLevel for companyId ${companyEntity.companyId}: $loyaltyLevel")


                Log.d("@@@", "Creating CompanyWithDetails for companyId: ${companyEntity.companyId}")
                CompanyWithDetails(
                    company = companyEntity,
                    mobileAppDashboard = dashboard,
                    customerMarkParameters = customerMarkParameters,
                    loyaltyLevels = loyaltyLevel
                ).also {
                    Log.d("@@@", "Created CompanyWithDetails for companyId=${companyEntity.companyId}")
                }
            }

            Log.d("@@@", "Mapping companies to domain models")
            val domainCompanies = companiesWithDetails.map { it.toDomain() }
            Log.d("@@@", "Returning page with ${domainCompanies.size} companies")

            LoadResult.Page(
                data = domainCompanies,
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            Log.e("@@@", "PagingSource Error loading data", exception)
            LoadResult.Error(exception)
        }
    }
}