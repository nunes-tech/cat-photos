package com.example.catphotos.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.catphotos.R
import com.example.catphotos.databinding.RecyclerCatsBinding
import com.example.catphotos.domain.models.UrlImageCat
import com.squareup.picasso.Picasso

class CatsAdapter: Adapter<CatsAdapter.CatsViewHolder>() {

    private var listCats = emptyList<UrlImageCat>()

    inner class CatsViewHolder(
        private val binding: RecyclerCatsBinding
    ): ViewHolder(binding.root) {
        fun bind(position: Int) {

            Picasso.get()
                .load(listCats[position].url)
                .placeholder(R.drawable.load)
                .error(R.drawable.error)
                .into(binding.imageGato)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerCatsBinding.inflate(layoutInflater, parent, false)
        return CatsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCats.size
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateListImagesCats(list: List<UrlImageCat>){
        listCats += list
        notifyDataSetChanged()
    }

}