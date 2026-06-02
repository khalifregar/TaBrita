package com.tabrita.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tabrita.domain.model.Article
import com.tabrita.domain.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ArticleDetailUiState(
    val article: Article? = null,
    val relatedArticles: List<Article> = emptyList(),
    val isBookmarked: Boolean = false,
    val isLoading: Boolean = true
)

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleDetailUiState())
    val uiState: StateFlow<ArticleDetailUiState> = _uiState.asStateFlow()

    fun loadArticle(articleId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val article = repository.getArticleById(articleId)
            val isBookmarked = repository.isBookmarked(articleId)

            val related = if (article != null) {
                repository.getArticlesByCategory(article.category)
                    .filter { it.id != articleId }
                    .take(4)
            } else emptyList()

            _uiState.update {
                it.copy(
                    article = article,
                    relatedArticles = related,
                    isBookmarked = isBookmarked,
                    isLoading = false
                )
            }
        }
    }

    fun toggleBookmark() {
        val current = _uiState.value.article?.id ?: return
        viewModelScope.launch {
            val newState = repository.toggleBookmark(current)
            _uiState.update { it.copy(isBookmarked = newState) }
        }
    }
}
