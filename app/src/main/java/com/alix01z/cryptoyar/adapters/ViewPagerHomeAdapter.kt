package com.alix01z.cryptoyar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.databinding.ItemVpagerHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ViewPagerHomeAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<ViewPagerHomeAdapter.ViewPagerHomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHomeViewHolder {
        val binding:ItemVpagerHomeBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_vpager_home, parent, false
        )
        return ViewPagerHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHomeViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount():Int = imageList.size

    class ViewPagerHomeViewHolder(private val binding: ItemVpagerHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Int) {
//            binding.viewfading.visibility = View.VISIBLE
            Glide.with(binding.root.context)
                .load(image)
                //diskCache is used to cache the images
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgvSlide)
            binding.executePendingBindings()
        }
    }
}