package com.takusemba.jethub.ui.item

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.takusemba.jethub.model.Repository
import com.takusemba.jethub.viewmodel.SearchReposViewModel
import com.takusemba.jethub.viewmodel.UserViewModel
import com.xwray.groupie.Item
import com.xwray.groupie.Section

class SearchReposSection(
  lifecycleOwner: LifecycleOwner,
  searchReposViewModel: SearchReposViewModel,
  private val userViewModel: UserViewModel
) : Section() {

  init {
    searchReposViewModel.searchedRepos.observe(lifecycleOwner, Observer { repositories ->
      updateResult(repositories)
    })
  }

  private fun updateResult(repositories: List<Repository>) {
    val items = mutableListOf<Item<*>>()
    (repositories).mapTo(items) { repository -> RepositoryItem(repository, userViewModel) }
    update(items)
  }
}