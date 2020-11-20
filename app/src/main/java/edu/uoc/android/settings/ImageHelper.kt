package edu.uoc.android.settings

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File

class ImageHelper(private val context: Context) {
    data class SavedImageData(val filePath: String, val providerUri: Uri)

    val outputImageData: SavedImageData by lazy { loadImageUrl() }


    private fun loadImageUrl(): SavedImageData {
        val imageDir =
            File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "UOCImageApp")
        if (!imageDir.exists()) {
            imageDir.mkdirs()
        }
        val imageFile = File(imageDir, "imageapp.jpg")
        return SavedImageData(
            imageFile.absolutePath,
            FileProvider.getUriForFile(context, "edu.uoc.android.fileprovider", imageFile)
        )
    }

    fun isImageAlreadyStored() = File(outputImageData.filePath).exists()

}