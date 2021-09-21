package com.hiberus.mobile.android.xing.common

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.hiberus.mobile.android.xing.R
import com.hiberus.mobile.android.xing.util.UrlUtils

class OpenGitHubDialogFragment : DialogFragment() {

    companion object {
        val TAG: String = OpenGitHubDialogFragment::class.java.simpleName
        private const val PARAMS_KEY_TITLE = "params_key_title"
        private const val PARAMS_KEY_REPOSITORY_URL = "params_key_repository_url"
        private const val PARAMS_KEY_OWNER_URL = "params_key_owner_url"

        fun newInstance(
            title: String,
            repositoryUrl: String,
            ownerUrl: String
        ): OpenGitHubDialogFragment {
            val args = Bundle()
            args.putString(PARAMS_KEY_TITLE, title)
            args.putString(PARAMS_KEY_REPOSITORY_URL, repositoryUrl)
            args.putString(PARAMS_KEY_OWNER_URL, ownerUrl)
            
            val fragment = OpenGitHubDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(arguments?.getString(PARAMS_KEY_TITLE))
            .setMessage(resources.getString(R.string.open_github_dialog_title))
            .setNegativeButton(
                resources.getString(R.string.open_github_dialog_repository_option)) { dialog, _ ->
                UrlUtils.openUrl(context, arguments?.getString(PARAMS_KEY_REPOSITORY_URL))
                dialog.dismiss()
            }
            .setPositiveButton(
                resources.getString(R.string.open_github_dialog_owner_option)) { dialog, _ ->
                UrlUtils.openUrl(context, arguments?.getString(PARAMS_KEY_OWNER_URL))
                dialog.dismiss()
            }
            .create()
    }
}