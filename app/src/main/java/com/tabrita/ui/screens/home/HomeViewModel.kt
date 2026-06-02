package com.tabrita.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabrita.domain.model.Article
import com.tabrita.domain.model.Category
import com.tabrita.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val featuredArticles: List<Article> = emptyList(),
    val forYouArticles: List<Article> = emptyList(),
    val trendingArticles: List<Article> = emptyList(),
    val selectedCategory: Category = Category.ALL,
    val isLoading: Boolean = true,
    val bookmarkedIds: Set<String> = emptySet()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
        observeBookmarks()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val featured = repository.getFeaturedArticles()
            val all = repository.getAllArticles()
            val trending = repository.getTrendingArticles(6)

            _uiState.update {
                it.copy(
                    featuredArticles = featured,
                    forYouArticles = all,
                    trendingArticles = trending,
                    isLoading = false
                )
            }
        }
    }

    fun selectCategory(category: Category) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedCategory = category) }

            val filtered = if (category == Category.ALL) {
                repository.getAllArticles()
            } else {
                repository.getArticlesByCategory(category)
            }

            _uiState.update { it.copy(forYouArticles = filtered) }
        }
    }

    fun toggleBookmark(articleId: String) {
        viewModelScope.launch {
            val newBookmarked = repository.toggleBookmark(articleId)
            // Refresh will come from flow
        }
    }

    private fun observeBookmarks() {
        viewModelScope.launch {
            repository.getBookmarkedArticles().collect { bookmarked ->
                _uiState.update { state ->
                    state.copy(bookmarkedIds = bookmarked.map { it.id }.toSet())
                }
            }
        }
    }

    fun refresh() {
        loadData()
    }
}
