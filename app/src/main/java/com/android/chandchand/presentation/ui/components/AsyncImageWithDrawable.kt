package com.android.chandchand.presentation.ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch

@Composable
fun AsyncImageWithDrawable(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholderResId: Int? = null,
    errorResId: Int? = null,
    fallbackResId: Int? = errorResId,
    contentScale: ContentScale,
    onDrawableLoad: (Drawable?) -> Unit
) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = model)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholderResId?.let { placeholder(it) }
                errorResId?.let { error(it) }
                fallbackResId?.let { fallback(it) }
                allowHardware(false)
            }).build()
    )

    val state = painter.state

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )

    when (state) {
        is AsyncImagePainter.State.Success -> {
            LaunchedEffect(key1 = painter) {
                launch {
                    val drawable: Drawable? = painter.imageLoader.execute(painter.request).drawable
                    onDrawableLoad(drawable)
                }
            }
        }
        else -> {}
    }
}
