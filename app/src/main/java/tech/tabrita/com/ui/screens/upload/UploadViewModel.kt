package tech.tabrita.com.ui.screens.upload

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import tech.tabrita.com.data.auth.AppUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

data class UploadSlice(
    val id: String = UUID.randomUUID().toString(),
    val text: String = "",
    val localImageUri: Uri? = null
)

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : ViewModel() {

    private val _slices = MutableStateFlow(listOf(UploadSlice()))
    val slices: StateFlow<List<UploadSlice>> = _slices.asStateFlow()

    private val _thumbnailUri = MutableStateFlow<Uri?>(null)
    val thumbnailUri: StateFlow<Uri?> = _thumbnailUri.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description: StateFlow<String> = _description.asStateFlow()

    private val _category = MutableStateFlow("Politik")
    val category: StateFlow<String> = _category.asStateFlow()

    private val _isUploading = MutableStateFlow(false)
    val isUploading: StateFlow<Boolean> = _isUploading.asStateFlow()

    private val _uploadSuccess = MutableStateFlow(false)
    val uploadSuccess: StateFlow<Boolean> = _uploadSuccess.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun updateTitle(value: String) { _title.value = value }
    fun updateDescription(value: String) { _description.value = value }
    fun updateCategory(value: String) { _category.value = value }

    fun setThumbnail(uri: Uri) { _thumbnailUri.value = uri }

    fun updateSliceText(sliceId: String, text: String) {
        _slices.value = _slices.value.map {
            if (it.id == sliceId) it.copy(text = text) else it
        }
    }

    fun setSliceImage(sliceId: String, uri: Uri) {
        _slices.value = _slices.value.map {
            if (it.id == sliceId) it.copy(localImageUri = uri) else it
        }
    }

    fun addSlice() {
        _slices.value = _slices.value + UploadSlice()
    }

    fun removeSlice(sliceId: String) {
        if (_slices.value.size > 1) {
            _slices.value = _slices.value.filter { it.id != sliceId }
        }
    }

    fun uploadNews(currentUser: AppUser, onComplete: () -> Unit) {
        if (_title.value.isBlank() || _description.value.isBlank()) {
            _error.value = "Judul dan deskripsi wajib diisi"
            return
        }

        viewModelScope.launch {
            _isUploading.value = true
            _error.value = null
            try {
                // Upload thumbnail
                val thumbUrl = _thumbnailUri.value?.let { uploadImage(it, "thumbnails") } ?: ""

                // Upload slice images and build blocks
                val blocks = _slices.value.map { slice ->
                    val imgUrl = slice.localImageUri?.let { uploadImage(it, "slices") }
                    tech.tabrita.com.domain.model.ContentBlock(
                        text = slice.text,
                        imageUrl = imgUrl
                    )
                }.filter { it.text.isNotBlank() || it.imageUrl != null }

                if (blocks.isEmpty()) {
                    _error.value = "Isi berita minimal 1 slice"
                    _isUploading.value = false
                    return@launch
                }

                val articleData = hashMapOf(
                    "title" to _title.value,
                    "description" to _description.value,
                    "thumbnailUrl" to thumbUrl,
                    "source" to "TaBrita Admin",
                    "author" to (currentUser.displayName ?: "Admin"),
                    "publishedAt" to System.currentTimeMillis(),
                    "category" to _category.value,
                    "readTimeMinutes" to ((blocks.sumOf { it.text.length } / 200) + 2).coerceAtLeast(1),
                    "contentBlocks" to blocks.map { mapOf("text" to it.text, "imageUrl" to it.imageUrl) }
                )

                firestore.collection("articles").add(articleData).await()

                _uploadSuccess.value = true
                resetForm()
                onComplete()
            } catch (e: Exception) {
                _error.value = e.message ?: "Gagal upload berita"
            } finally {
                _isUploading.value = false
            }
        }
    }

    private suspend fun uploadImage(uri: Uri, folder: String): String {
        val ref = storage.reference.child("$folder/${UUID.randomUUID()}.jpg")
        ref.putFile(uri).await()
        return ref.downloadUrl.await().toString()
    }

    private fun resetForm() {
        _title.value = ""
        _description.value = ""
        _thumbnailUri.value = null
        _slices.value = listOf(UploadSlice())
        _category.value = "Politik"
        _uploadSuccess.value = false
    }

    fun clearError() { _error.value = null }
}

