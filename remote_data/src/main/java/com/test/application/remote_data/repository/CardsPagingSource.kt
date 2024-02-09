package com.test.application.remote_data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.application.remote_data.api.CardsApi
import com.test.application.remote_data.dto.CompanyDTO

class CardsPagingSource(
    private val apiService: CardsApi
) : PagingSource<Int, CompanyDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CompanyDTO> {
        val position = params.key ?: INITIAL_OFFSET

        return try {
            val response = apiService.getAllCards(mapOf("offset" to position, "limit" to PAGE_SIZE)).await()
            val companies = response.companies

            LoadResult.Page(
                data = companies,
                prevKey = if (position == INITIAL_OFFSET) null else position - PAGE_SIZE,
                nextKey = if (companies.isEmpty()) null else position + PAGE_SIZE
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CompanyDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }
    }

    companion object {
        const val PAGE_SIZE = 10
        const val INITIAL_OFFSET = 0
    }
}