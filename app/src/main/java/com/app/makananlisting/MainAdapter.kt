package com.app.makananlisting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.makananlisting.databinding.ItemMainBinding
import com.app.makananlisting.model.Makanan
import com.bumptech.glide.Glide

class MainAdapter(
    private val data: List<Makanan>,
    private val context: Context
): RecyclerView.Adapter<MainAdapter.MainHolder>() {
    class MainHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        Glide.with(context).load(data[position].gambar)
            .fitCenter().into(holder.binding.itemMainImage)
        holder.binding.itemMainTitle.text = data[position].nama
        holder.binding.itemMainPrice.text = context.getString(R.string.harga_format, data[position].harga)
    }

    override fun getItemCount(): Int = data.size
}