package com.hiberus.mobile.android.xing.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaginationScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val fetchData: () -> Unit
) : RecyclerView.OnScrollListener() {

    companion object {
        private const val ENDLESS_OFFSET = 5
    }

    private var isLoadingMore = true
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (isLoadingMore) {
                if (totalItemCount > previousTotal) {
                    isLoadingMore = false
                    previousTotal = totalItemCount
                }
            }
            if (!isLoadingMore && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + ENDLESS_OFFSET)) {
                fetchData()
                isLoadingMore = true
            }
        }
    }
}