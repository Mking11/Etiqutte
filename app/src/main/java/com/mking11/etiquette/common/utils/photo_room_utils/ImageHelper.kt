package com.mking11.etiquette.common.utils.photo_room_utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.request.SuccessResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


suspend fun invoke(context: Context, imageLoader: ImageLoader, photoURl: String): Flow<Bitmap?> =
    flow {
        val request = ImageRequest.Builder(context).data(photoURl).build()

        val result = (imageLoader.execute(request) as SuccessResult).drawable
        emit((result as BitmapDrawable).bitmap)
    }

suspend fun convertToBitmap(context: Context, imageLoader: ImageLoader, photoURl: String): Bitmap? {


    return try {
        val request = ImageRequest.Builder(context).data(photoURl).build()
        val result: ImageResult = imageLoader.execute(request)

        if (result.drawable == null) {
            null
        } else {
            ((result.drawable as BitmapDrawable).bitmap)
        }

    } catch (e: NullPointerException) {
        null
    }


}
