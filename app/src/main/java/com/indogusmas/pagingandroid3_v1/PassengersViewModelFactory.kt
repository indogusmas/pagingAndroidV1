package com.indogusmas.pagingandroid3_v1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PassengersViewModelFactory(
    private val  api: MyApi
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PassengeresViewModel(api) as T
    }
}