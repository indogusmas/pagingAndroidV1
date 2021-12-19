package com.indogusmas.pagingandroid3_v1

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class PassengersDataSource(private val api: MyApi): PagingSource<Int, Passenger>() {
    override fun getRefreshKey(state: PagingState<Int, Passenger>): Int? {
        return  state.anchorPosition?.let {
                anchorPosition -> state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Passenger> {
        return  try {
            val nextPageNumber = params.key ?: 1
            val response = api.getPassengersData(nextPageNumber)
            LoadResult.Page(
                data =  response.data,
                prevKey = if (nextPageNumber == nextPageNumber) null else nextPageNumber - 1,
                nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}