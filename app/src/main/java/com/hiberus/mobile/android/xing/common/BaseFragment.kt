package com.hiberus.mobile.android.xing.common

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hiberus.mobile.android.model.error.AsyncError
import com.hiberus.mobile.android.xing.R
import com.hiberus.mobile.android.xing.common.model.ResourceState
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    internal fun <T> handleDataState(
        resourceState: ResourceState<Any?>,
        loadingView: View?,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        when (resourceState) {
            is ResourceState.Loading -> showLoading(loadingView, true)
            is ResourceState.Success -> {
                loadingView?.visibility = View.GONE
                errorView?.visibility = View.GONE
                showResult(resourceState.data)
            }
            is ResourceState.Error -> {
                Timber.e(resourceState.error.debugMessage)
                showLoading(loadingView, false)
                showError(
                    resourceState.error,
                    errorView,
                    errorMessageView,
                    errorButtonRetryView,
                    retryFunction
                )
            }
        }
    }

    private fun showLoading(loadingView: View?, showLoading: Boolean) {
        if (showLoading) {
            loadingView?.visibility = View.VISIBLE
        } else {
            loadingView?.visibility = View.GONE
        }
    }

    private fun <T> showError(
        asyncError: AsyncError,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        errorView?.visibility = View.VISIBLE
        errorButtonRetryView?.setOnClickListener { retryFunction() }
        (errorMessageView as? TextView)?.text = when (asyncError) {
            is AsyncError.ConnectionError -> resources.getString(R.string.connection_error)
            is AsyncError.ServerError -> resources.getString(R.string.server_error)
            else -> resources.getString(R.string.unknown_error)
        }
    }

    protected abstract fun showResult(result: Any?)
}