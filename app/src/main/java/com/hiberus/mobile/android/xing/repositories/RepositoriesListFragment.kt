package com.hiberus.mobile.android.xing.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.xing.R
import com.hiberus.mobile.android.xing.common.BaseFragment
import com.hiberus.mobile.android.xing.common.OpenGitHubDialogFragment
import com.hiberus.mobile.android.xing.common.model.ResourceState
import com.hiberus.mobile.android.xing.databinding.FragmentRepositoriesBinding
import com.hiberus.mobile.android.xing.util.OnRepositoryLongClickListener
import com.hiberus.mobile.android.xing.util.PaginationScrollListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesListFragment : BaseFragment() {

    private lateinit var binding: FragmentRepositoriesBinding
    private val viewModel: RepositoriesListViewModel by viewModel()
    private val repositoriesListAdapter: RepositoriesListAdapter by inject()
    private lateinit var layoutManager: LinearLayoutManager
    private var page = 1

    private val repositoriesAdapterListener = object : OnRepositoryLongClickListener {
        override fun onLongClicked(repository: Repository) {
            showOpenGitHubDialog(repository)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setupRepositoriesRecycler()
        getRepositoriesList()
    }

    @Suppress("UNCHECKED_CAST")
    override fun showResult(result: Any?) {
        (result as? List<Repository>)?.apply {
            val positionStart = repositoriesListAdapter.repositories.size
            val itemCount = this.size
            repositoriesListAdapter.repositories.addAll(this)
            binding.rvRepositoriesList.post {
                repositoriesListAdapter.notifyItemRangeChanged(positionStart, itemCount)
            }
        }
    }

    private fun initObservers() {
        viewModel.repositories.observe(viewLifecycleOwner, { result ->
            if (result != null) {
                handleDataState(
                    resourceState = result,
                    loadingView = binding.clProgress.root,
                    errorView = binding.clError.root,
                    errorMessageView = binding.clError.tvError,
                    errorButtonRetryView = binding.clError.btnRetry,
                    retryFunction = { viewModel.fetchRepositories(this.page) }
                )

                if (result is ResourceState.Success && result.data?.isNotEmpty() == true) {
                    this.page++
                }
            }
        })
    }

    private fun getRepositoriesList() {
        this.page = 1
        viewModel.fetchRepositories(this.page)
    }

    private fun setupRepositoriesRecycler() {
        layoutManager = LinearLayoutManager(context)
        binding.rvRepositoriesList.layoutManager = layoutManager
        repositoriesListAdapter.setRepositoryAdapterListener(repositoriesAdapterListener)
        binding.rvRepositoriesList.adapter = repositoriesListAdapter

        binding.rvRepositoriesList.addOnScrollListener(
            PaginationScrollListener(layoutManager) { viewModel.fetchRepositories(this.page) }
        )

        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_item_decoration, null)?.let {
            itemDecoration.setDrawable(it)
        }
        binding.rvRepositoriesList.addItemDecoration(itemDecoration)
    }

    private fun showOpenGitHubDialog(repository: Repository) {
        val title = "${repository.owner.login} / ${repository.name}"
        val openGitHubDialogFragment = OpenGitHubDialogFragment.newInstance(
            title,
            repository.htmlUrl,
            repository.owner.htmlUrl
        )

        activity?.supportFragmentManager?.let {
            openGitHubDialogFragment.show(it, OpenGitHubDialogFragment.TAG)
        }
    }
}