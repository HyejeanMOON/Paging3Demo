package com.example.paging3demo

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

class PersonViewModel : ViewModel() {

    private val repository = PersonRepository()

    var personList =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                PersonDataSource(repository)
            }
        ).flow

}