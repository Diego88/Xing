package com.hiberus.mobile.android.xing.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.mobile.android.domain.repository.repositories.GetRepositoriesUseCase
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.xing.common.model.ResourceState
import com.hiberus.mobile.android.xing.common.model.ResourceState.Companion.toResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RepositoriesListViewModel(
    private val getRepositoryUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private var _repositories = MutableLiveData<ResourceState<List<Repository>>>()
    val repositories: LiveData<ResourceState<List<Repository>>>
        get() = _repositories

    fun fetchRepositories(page: Int = 1) {
        _repositories.value = ResourceState.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            getRepositoryUseCase(page).collect { result ->
                _repositories.value = result.toResourceState()
            }
        }
    }
}