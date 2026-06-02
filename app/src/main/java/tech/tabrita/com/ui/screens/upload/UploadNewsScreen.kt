package tech.tabrita.com.ui.screens.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import tech.tabrita.com.data.auth.AppUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadNewsScreen(
    currentUser: AppUser,
    onUploadSuccess: () -> Unit = {},
    viewModel: UploadViewModel = hiltViewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val category by viewModel.category.collectAsState()
    val thumbnailUri by viewModel.thumbnailUri.collectAsState()
    val slices by viewModel.slices.collectAsState()
    val isUploading by viewModel.isUploading.collectAsState()
    val error by viewModel.error.collectAsState()

    val categories = listOf("Politik", "Teknologi", "Bisnis", "Olahraga", "Hiburan", "Kesehatan", "Sains")

    val thumbnailPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.setThumbnail(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Berita Baru", fontWeight = FontWeight.SemiBold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(Modifier.height(8.dp))
                Text("Judul Berita", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = title,
                    onValueChange = viewModel::updateTitle,
                    placeholder = { Text("Masukkan judul yang menarik...") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                Text("Deskripsi Singkat", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = description,
                    onValueChange = viewModel::updateDescription,
                    placeholder = { Text("Ringkasan berita dalam 1-2 kalimat...") },
                    modifier = Modifier.fillMaxWidth().height(90.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {
                Text("Kategori", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = category,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        shape = RoundedCornerShape(12.dp)
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat) },
                                onClick = {
                                    viewModel.updateCategory(cat)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            item {
                Text("Thumbnail Utama", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { thumbnailPicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (thumbnailUri != null) {
                        AsyncImage(
                            model = thumbnailUri,
                            contentDescription = "Thumbnail",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        IconButton(
                            onClick = { viewModel.setThumbnail(Uri.EMPTY) },
                            modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                                .background(Color.Black.copy(0.5f), RoundedCornerShape(50))
                        ) {
                            Icon(Icons.Default.Close, contentDescription = "Hapus", tint = Color.White)
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Image, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(42.dp))
                            Spacer(Modifier.height(8.dp))
                            Text("Pilih Thumbnail", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Isi Berita (Slice)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                    FilledTonalButton(onClick = viewModel::addSlice, shape = RoundedCornerShape(50)) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Tambah Slice")
                    }
                }
            }

            items(slices, key = { it.id }) { slice ->
                UploadSliceCard(
                    slice = slice,
                    onTextChange = { viewModel.updateSliceText(slice.id, it) },
                    onPickImage = { uri -> viewModel.setSliceImage(slice.id, uri) },
                    onRemove = { viewModel.removeSlice(slice.id) }
                )
            }

            item {
                if (error != null) {
                    Text(error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(vertical = 8.dp))
                }

                Button(
                    onClick = { viewModel.uploadNews(currentUser, onUploadSuccess) },
                    enabled = !isUploading && title.isNotBlank() && description.isNotBlank(),
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = Color.White)
                        Spacer(Modifier.width(12.dp))
                        Text("Mengupload...")
                    } else {
                        Text("Publikasikan Berita", fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun UploadSliceCard(
    slice: UploadSlice,
    onTextChange: (String) -> Unit,
    onPickImage: (Uri) -> Unit,
    onRemove: () -> Unit
) {
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let(onPickImage)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Slice", fontWeight = FontWeight.Medium)
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Close, contentDescription = "Hapus slice", tint = MaterialTheme.colorScheme.error)
                }
            }

            OutlinedTextField(
                value = slice.text,
                onValueChange = onTextChange,
                placeholder = { Text("Tulis paragraf di sini...") },
                modifier = Modifier.fillMaxWidth().height(110.dp),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                    .clickable { imagePicker.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (slice.localImageUri != null) {
                    AsyncImage(
                        model = slice.localImageUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Image, null, tint = MaterialTheme.colorScheme.primary)
                        Text("Pilih Gambar (opsional)", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

