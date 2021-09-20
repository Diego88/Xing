package com.hiberus.mobile.android.xing.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCase
import com.hiberus.mobile.android.model.AsyncResult
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.xing.common.model.ResourceState
import com.hiberus.mobile.android.xing.common.model.ResourceState.Companion.toResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RepositoriesListViewModel(
    private val getRepositoryUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private var page = 1

    private var _repositories = MutableLiveData<ResourceState<List<Repository>>>()
    val repositories: LiveData<ResourceState<List<Repository>>>
        get() = _repositories

    fun fetchRepositories() {
        _repositories.value = ResourceState.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            getRepositoryUseCase(page).collect { result ->
                if (result is AsyncResult.Success && result.data?.isNotEmpty() == true) {
                    page++
                }
                _repositories.value = result.toResourceState()
            }
        }
    }
}