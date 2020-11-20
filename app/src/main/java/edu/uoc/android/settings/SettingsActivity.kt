package edu.uoc.android.settings

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.provider.MediaStore
import androidx.exifinterface.media.ExifInterface
import edu.uoc.android.R
import edu.uoc.android.base.TargetActivity
import edu.uoc.android.common.hide
import edu.uoc.android.common.show
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : TargetActivity() {
    private val REQUEST_CAMERA_PERMISSION = 2001
    private val REQUEST_CAMERA_INTENT = 2002

    private lateinit var imageHelper: ImageHelper
    private lateinit var savedPictureData: ImageHelper.SavedImageData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        imageHelper = ImageHelper(this)
        savedPictureData = imageHelper.outputImageData
        if (imageHelper.isImageAlreadyStored()) {
            loadImageFromFile()
        } else {
            hideImage()
        }
        initializeTakePictureButton()
    }

    private fun hideImage() {
        no_image_selected_text.show()
        image_picture_taken.hide()
    }

    private fun initializeTakePictureButton() {
        button_take_picture.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                ) {
                    requestCameraPicture()
                } else {
                    requestPermissions(
                        arrayOf(
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        REQUEST_CAMERA_PERMISSION
                    )
                }
            } else {
                requestCameraPicture()
            }
        }
    }

    private fun requestCameraPicture() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        savedPictureData = imageHelper.outputImageData
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, savedPictureData.providerUri)
        try {
            startActivityForResult(cameraIntent, REQUEST_CAMERA_INTENT)
        } catch (e: Exception) {
            notifyError()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                requestCameraPicture()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CAMERA_INTENT && resultCode == Activity.RESULT_OK) {
            loadImageFromFile()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadImageFromFile() {
        no_image_selected_text.hide()
        image_picture_taken.show()
        val exif = ExifInterface(savedPictureData.filePath)
        if (exif.thumbnailBitmap != null) {
            val matrix = Matrix().apply { postRotate(exif.rotationDegrees.toFloat()) }
            val rotatedBitmap = Bitmap.createBitmap(
                exif.thumbnailBitmap!!,
                0,
                0,
                exif.thumbnailBitmap!!.getScaledWidth(resources.displayMetrics.densityDpi),
                exif.thumbnailBitmap!!.getScaledHeight(resources.displayMetrics.densityDpi),
                matrix,
                true
            )
            image_picture_taken.setImageBitmap(rotatedBitmap)
        }
    }

    private fun notifyError() {
        // Nothing to do YET
    }
}