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
import tech.tabrita.com.ui.theme.TaBritaColors
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.res.stringResource
import tech.tabrita.com.ui.theme.TaBritaDimens
import tech.tabrita.com.R
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import coil3.compose.AsyncImage
import tech.tabrita.com.data.auth.AppUser
import tech.tabrita.com.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadNewsScreen(
    currentUser: AppUser,
    onUploadSuccess: () -> Unit = {},
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass? = null,
    viewModel: UploadViewModel = hiltViewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val category by viewModel.category.collectAsState()
    val thumbnailUri by viewModel.thumbnailUri.collectAsState()
    val slices by viewModel.slices.collectAsState()
    val isUploading by viewModel.isUploading.collectAsState()
    val error by viewModel.error.collectAsState()

    val widthSizeClass = windowSizeClass?.widthSizeClass ?: WindowWidthSizeClass.Compact
    val isLarge = widthSizeClass == WindowWidthSizeClass.Expanded || widthSizeClass == WindowWidthSizeClass.Medium
    val useTwoColumnForm = isLarge

    val categories = Category.entries.filter { it != Category.ALL }.map { it.displayName }

    val thumbnailPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.setThumbnail(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.upload_title), fontWeight = FontWeight.SemiBold) },
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
                .wrapContentWidth(androidx.compose.ui.Alignment.CenterHorizontally)
                .widthIn(max = TaBritaDimens.maxContentWidth)
                .padding(horizontal = TaBritaDimens.paddingLarge),
            verticalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingMedium)
        ) {
            item {
                Spacer(Modifier.height(TaBritaDimens.paddingXSmall))
                if (useTwoColumnForm) {
                    Row(horizontalArrangement = Arrangement.spacedBy(TaBritaDimens.paddingMedium)) {
                        Column(Modifier.weight(1f)) {
                            Text(stringResource(R.string.upload_news_title), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                            OutlinedTextField(
                                value = title,
                                onValueChange = viewModel::updateTitle,
                                placeholder = { Text(stringResource(R.string.upload_news_title_placeholder)) },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
                            )
                        }
                        Column(Modifier.weight(1f)) {
                            Text(stringResource(R.string.upload_category), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
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
                                    shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
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
                    }
                } else {
                    Text(stringResource(R.string.upload_news_title), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                    OutlinedTextField(
                        value = title,
                        onValueChange = viewModel::updateTitle,
                        placeholder = { Text(stringResource(R.string.upload_news_title_placeholder)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
                    )
                    Spacer(Modifier.height(TaBritaDimens.paddingSmall))
                    Text(stringResource(R.string.upload_category), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
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
                            shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
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
            }

            item {
                Text(stringResource(R.string.upload_description), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = description,
                    onValueChange = viewModel::updateDescription,
                    placeholder = { Text(stringResource(R.string.upload_description_placeholder)) },
                    modifier = Modifier.fillMaxWidth().heightIn(min = TaBritaDimens.formFieldHeight),
                    shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
                )
            }

            item {
                Text(stringResource(R.string.upload_thumbnail), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TaBritaDimens.cardImageHeightLarge)
                        .clip(RoundedCornerShape(TaBritaDimens.cornerLarge))
                        .border(TaBritaDimens.borderWidth, MaterialTheme.colorScheme.outline, RoundedCornerShape(TaBritaDimens.cornerLarge))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { thumbnailPicker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (thumbnailUri != null) {
                        AsyncImage(
                            model = thumbnailUri,
                            contentDescription = stringResource(R.string.upload_thumbnail),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        IconButton(
                            onClick = { viewModel.setThumbnail(Uri.EMPTY) },
                            modifier = Modifier.align(Alignment.TopEnd).padding(TaBritaDimens.paddingXSmall)
                                .background(TaBritaColors.OverlayDark, RoundedCornerShape(TaBritaDimens.cornerPill))
                        ) {
                            Icon(Icons.Default.Close, contentDescription = stringResource(R.string.upload_remove_slice), tint = TaBritaColors.TextOnDarkPrimary)
                        }
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Image, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(TaBritaDimens.iconSizeLarge))
                            Spacer(Modifier.height(TaBritaDimens.paddingXSmall))
                            Text(stringResource(R.string.upload_choose_thumbnail), color = MaterialTheme.colorScheme.onSurfaceVariant)
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
                    Text(stringResource(R.string.upload_content_slices), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
                    FilledTonalButton(onClick = viewModel::addSlice, shape = RoundedCornerShape(TaBritaDimens.cornerPill)) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(TaBritaDimens.iconSizeSmall))
                        Spacer(Modifier.width(TaBritaDimens.paddingXSmall))
                        Text(stringResource(R.string.upload_add_slice))
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
                    Text(error!!, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(vertical = TaBritaDimens.paddingXSmall))
                }

                Button(
                    onClick = { viewModel.uploadNews(currentUser, onUploadSuccess) },
                    enabled = !isUploading && title.isNotBlank() && description.isNotBlank(),
                    modifier = Modifier.fillMaxWidth().height(TaBritaDimens.buttonHeight),
                    shape = RoundedCornerShape(TaBritaDimens.cornerLarge)
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(modifier = Modifier.size(TaBritaDimens.iconSizeSmall), strokeWidth = TaBritaDimens.strokeWidth, color = Color.White)
                        Spacer(Modifier.width(TaBritaDimens.paddingSmall))
                        Text(stringResource(R.string.upload_uploading))
                    } else {
                        Text(stringResource(R.string.upload_publish), fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(Modifier.height(TaBritaDimens.paddingXXLarge))
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
        shape = RoundedCornerShape(TaBritaDimens.cornerLarge),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(TaBritaDimens.paddingMedium)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.upload_slice_label), fontWeight = FontWeight.Medium)
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Close, contentDescription = stringResource(R.string.upload_remove_slice), tint = MaterialTheme.colorScheme.error)
                }
            }

            OutlinedTextField(
                value = slice.text,
                onValueChange = onTextChange,
                placeholder = { Text(stringResource(R.string.upload_slice_text_placeholder)) },
                modifier = Modifier.fillMaxWidth().heightIn(min = TaBritaDimens.paddingXXXLarge * 2),
                shape = RoundedCornerShape(TaBritaDimens.cornerMedium)
            )

            Spacer(Modifier.height(TaBritaDimens.paddingSmall))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TaBritaDimens.cardImageHeightMedium)
                    .clip(RoundedCornerShape(TaBritaDimens.cornerMedium))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .border(TaBritaDimens.borderWidth, MaterialTheme.colorScheme.outline, RoundedCornerShape(TaBritaDimens.cornerMedium))
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
                        Text(stringResource(R.string.upload_choose_image_optional), style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

