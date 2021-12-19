package com.indogusmas.pagingandroid3_v1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PassengeresViewModel(
    private val  api: MyApi
): ViewModel() {
    val passenger =
        Pager(config = PagingConfig(
            pageSize = 10
        ),pagingSourceFactory = {
            PassengersDataSource(api)
        }).flow.cachedIn(viewModelScope)
}