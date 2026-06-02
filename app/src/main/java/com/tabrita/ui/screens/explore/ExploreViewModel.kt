package com.tabrita.ui.screens.explore

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

data class ExploreUiState(
    val allArticles: List<Article> = emptyList(),
    val filteredArticles: List<Article> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: Category = Category.ALL,
    val isLoading: Boolean = false,
    val bookmarkedIds: Set<String> = emptySet()
)

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        loadArticles()
        observeBookmarks()
    }

    private fun loadArticles() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val articles = repository.getAllArticles()
            _uiState.update {
                it.copy(
                    allArticles = articles,
                    filteredArticles = articles,
                    isLoading = false
                )
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applyFilters()
    }

    fun selectCategory(category: Category) {
        _uiState.update { it.copy(selectedCategory = category) }
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        viewModelScope.launch {
            val base = if (state.searchQuery.isBlank()) {
                repository.getAllArticles()
            } else {
                repository.searchArticles(state.searchQuery)
            }

            val filtered = if (state.selectedCategory == Category.ALL) {
                base
            } else {
                base.filter { it.category == state.selectedCategory }
            }

            _uiState.update { it.copy(filteredArticles = filtered) }
        }
    }

    fun toggleBookmark(articleId: String) {
        viewModelScope.launch {
            repository.toggleBookmark(articleId)
        }
    }

    private fun observeBookmarks() {
        viewModelScope.launch {
            repository.getBookmarkedArticles().collect { bookmarked ->
                _uiState.update { it.copy(bookmarkedIds = bookmarked.map { a -> a.id }.toSet()) }
            }
        }
    }
}
