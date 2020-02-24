package com.example.instabus.model

import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream


class Memory {
    var title: String
        private set
    var imageAsString: String
        private set


    constructor(cursor: Cursor) {
        title = cursor.getString(COL_TITLE)
        imageAsString = cursor.getString(COL_IMAGE)
    }

    constructor(title: String, image: Bitmap) {
        this.title = title
        imageAsString = bitmapToString(resizeBitmap(image))
    }

    fun getImage(): Bitmap? {
        return stringToBitmap(imageAsString)
    }

    companion object {
        private const val PREFERRED_WIDTH = 250f
        private const val PREFERRED_HEIGHT = 250f
        const val COL_ID = 0
        const val COL_TITLE = 1
        const val COL_IMAGE = 2
        private fun bitmapToString(bitmap: Bitmap): String {
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b = baos.toByteArray()
            return Base64.encodeToString(b, Base64.DEFAULT)
        }

        private fun stringToBitmap(encodedString: String): Bitmap? {
            return try {
                val encodeByte =
                    Base64.decode(encodedString, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
            } catch (e: Exception) {
                e.message
                null
            }
        }

        fun resizeBitmap(bitmap: Bitmap): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val scaleWidth = PREFERRED_WIDTH / width
            val scaleHeight = PREFERRED_HEIGHT / height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            val resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false
            )
            bitmap.recycle()
            return resizedBitmap
        }

        fun getTitle(memory: Memory):String {
            return memory.title
        }

        fun getImageAsString(memory: Memory):String {
            return memory.imageAsString
        }
    }
}
