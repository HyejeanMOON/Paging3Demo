package com.example.paging3demo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class PersonDataSource(private val repository: PersonRepository) : PagingSource<Int, Person>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        val pos = params.key ?: START_INDEX
        val startIndex = pos * params.loadSize + 1
        val endIndex = (pos + 1) * params.loadSize
        return try {
            val personList = repository.getPersonList(startIndex, endIndex)
            Log.d("AAAA", personList.size.toString())
            Log.d("AAAA",personList[0].name)
            LoadResult.Page(
                personList,
                if (pos <= START_INDEX) null else pos - 1,
                if (personList.isEmpty()) null else pos + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        return 0
    }

    companion object {
        private const val START_INDEX = 0
    }
}