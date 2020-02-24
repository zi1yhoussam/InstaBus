package com.example.instabus.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.instabus.R
import com.example.instabus.db.MemoryDbHelper
import com.example.instabus.model.Memory
import java.io.IOException
import java.io.InputStream


class NewMemoryActivity : AppCompatActivity() {
    private var selectedImageView: ImageView? = null
    private var titleEditText: EditText? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_memory_activity)
        selectedImageView =
            findViewById(R.id.new_memory_selected_image) as ImageView?
        titleEditText = findViewById(R.id.new_memory_title) as EditText?
    }

    fun openGallery(view: View?) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            GALLERY_REQUEST_CODE
        )
    }

    fun openCamera(view: View?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(
                takePictureIntent,
                CAMERA_REQUEST_CODE
            )
        }
    }

    fun cancel(view: View?) {
        finish()
    }

    fun save(view: View?) {
        val image = (selectedImageView!!.drawable as BitmapDrawable).bitmap
        MemoryDbHelper(this).addMemory(Memory(titleEditText!!.text.toString(), image))
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                val selectedImage = data?.data
                val imageStream: InputStream? =
                    selectedImage?.let { getContentResolver().openInputStream(it) }
                selectedImageView!!.setImageBitmap(BitmapFactory.decodeStream(imageStream))
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val extras = data?.extras
            val imageBitmap = extras!!["data"] as Bitmap?
            selectedImageView!!.setImageBitmap(imageBitmap)
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
        private const val CAMERA_REQUEST_CODE = 200
    }
}
