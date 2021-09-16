package com.hiberus.mobile.android.xing.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hiberus.mobile.android.model.repositories.Repository
import com.hiberus.mobile.android.xing.R
import com.hiberus.mobile.android.xing.databinding.RowRepositoryBinding
import com.hiberus.mobile.android.xing.util.OnRepositoryLongClickListener
import com.hiberus.mobile.android.xing.util.loadImage
import java.lang.ref.WeakReference

class RepositoriesListAdapter : RecyclerView.Adapter<RepositoriesListAdapter.ViewHolder>() {

    var repositories: MutableList<Repository> = mutableListOf()
    private var repositoryLongClickListener: WeakReference<OnRepositoryLongClickListener>? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = RowRepositoryBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    fun setRepositoryAdapterListener(@NonNull onRepositoryClickListener: OnRepositoryLongClickListener) {
        this.repositoryLongClickListener = WeakReference(onRepositoryClickListener)
    }

    private fun getPrivateImageResource(private: Boolean): Int =
        if (private) {
            R.drawable.ic_lock
        } else {
            R.drawable.ic_unlock
        }

    private fun getForkedColorResource(fork: Boolean) =
        if (fork) {
            R.color.light_green
        } else {
            R.color.white
        }

    inner class ViewHolder(private val itemBinding: RowRepositoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(position: Int) {
            val repository = repositories[position]

            itemBinding.ivRowOwnerAvatar.loadImage(
                itemBinding.root.context,
                repository.owner.avatarUrl,
                RequestOptions
                    .circleCropTransform()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                // Caches only the final image, after reducing the resolution and transformations
            )

            itemBinding.clRowRepositoryContainer.setBackgroundResource(
                getForkedColorResource(repository.fork)
            )
            itemBinding.tvRowRepositoryName.text = repository.name
            itemBinding.tvRowRepositoryDescription.text = repository.description
            itemBinding.tvRowOwnerLogin.text = repository.owner.login
            itemBinding.ivRowRepositoryPrivate.setImageResource(
                getPrivateImageResource(repository.private)
            )

            itemBinding.root.setOnLongClickListener {
                this@RepositoriesListAdapter.repositoryLongClickListener?.get()
                    ?.onLongClicked(repository)
                return@setOnLongClickListener true
            }
        }
    }
}