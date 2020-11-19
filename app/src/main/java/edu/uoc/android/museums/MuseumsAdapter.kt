package edu.uoc.android.museums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uoc.android.R
import edu.uoc.android.common.loadFromUrl
import edu.uoc.android.rest.models.Element

class MuseumsAdapter : RecyclerView.Adapter<MuseumsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewBanner = view.findViewById<ImageView>(R.id.item_museum_image)
        val textViewName = view.findViewById<TextView>(R.id.item_museum_name)
    }

    private val items = mutableListOf<Element>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_museum, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = items[position].adrecaNom
        holder.imageViewBanner.loadFromUrl(items[position].imatge?.first())
    }

    fun bindMuseums(museums: List<Element>) {
        items.clear()
        items.addAll(museums)
        notifyDataSetChanged()
    }
}