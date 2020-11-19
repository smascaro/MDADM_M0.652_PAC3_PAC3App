package edu.uoc.android.museums

import android.os.Bundle
import edu.uoc.android.R
import edu.uoc.android.base.TargetActivity
import edu.uoc.android.common.hide
import edu.uoc.android.common.show
import edu.uoc.android.rest.RetrofitFactory
import kotlinx.android.synthetic.main.activity_museums.*
import kotlinx.android.synthetic.main.layout_loading_state.*
import kotlinx.coroutines.*

class MuseumsActivity : TargetActivity() {

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
        showError(false)
        showProgress(true)
        val retrofit = RetrofitFactory.museumAPI
        val museums = try {
            withContext(io.coroutineContext) { retrofit.museums("", "") }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        var museumsBound = false
        if (museums?.elements != null) {
            (museums_list.adapter as MuseumsAdapter).bindMuseums(museums.elements!!)
            showProgress(false)
            museums_list.show()
            museumsBound = true
        }
        if (!museumsBound) {
            notifyError()
        }
    }


    private fun notifyError() {
        showProgress(false)
        showError(true)
        error_image.setOnClickListener {
            it.setOnClickListener(null)
            showError(false)
            loadMuseums()
        }
    }

    private fun showProgress(show: Boolean) {
        when (show) {
            true -> loading_indicator.show()
            false -> loading_indicator.hide()
        }
    }

    private fun showError(show: Boolean) {
        when (show) {
            true -> error_container.show()
            false -> error_container.hide()
        }
    }

}