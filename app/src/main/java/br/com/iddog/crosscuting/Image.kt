package br.com.iddog.crosscuting

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap

object Image {
    fun scaleDown(drawable: Drawable, context: Context): Bitmap {
        val bitmap = BitmapDrawable(context.resources, drawable.toBitmap()).bitmap
        return Bitmap.createScaledBitmap(
            bitmap, 512,
            (bitmap.height * (512.0 / bitmap.width)).toInt(),
            true
        )
    }
}