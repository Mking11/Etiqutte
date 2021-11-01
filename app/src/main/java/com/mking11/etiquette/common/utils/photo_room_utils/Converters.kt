package com.mking11.etiquette.common.utils.photo_room_utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {


    @TypeConverter
    fun fromBitMap(bitmap: Bitmap?): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return if (bitmap != null) outputStream.toByteArray() else null
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        return if (byteArray != null) BitmapFactory.decodeByteArray(
            byteArray,
            0,
            byteArray.size
        ) else null
    }
}