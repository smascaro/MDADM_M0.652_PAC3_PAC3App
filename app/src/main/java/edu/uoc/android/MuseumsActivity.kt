package edu.uoc.android

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.android.rest.RetrofitFactory
import kotlinx.android.synthetic.main.activity_museums.*
import kotlinx.coroutines.*

class MuseumsActivity : AppCompatActivity() {

    private val ui = CoroutineScope(Dispatchers.Main + Job())
    private val io = CoroutineScope(Dispatchers.IO + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museums)
        initializeMuseumsList()
        loadMuseums()
    }

    private fun initializeMuseumsList() {
        museums_list.adapter = MuseumsAdapter()
    }

    private fun loadMuseums() = ui.launch {
        error_container.hide()
        museums_loading_indicator.show()
        val retrofit = RetrofitFactory.museumAPI
        val museums = try {
            withContext(io.coroutineContext) { retrofit.museums("", "") }
        } catch (e:Exception){
            e.printStackTrace()
            null
        }
        var museumsBound = false
        if (museums?.elements != null) {
            (museums_list.adapter as MuseumsAdapter).bindMuseums(museums.elements!!)
            museums_loading_indicator.hide()
            museums_list.show()
            museumsBound = true
        }
        if (!museumsBound) {
            notifyError()
        }
    }

    private fun notifyError() {
        museums_loading_indicator.hide()
        error_container.show()
        error_image.setOnClickListener {
            it.setOnClickListener(null)
            error_container.hide()
            loadMuseums()
        }

    }
}