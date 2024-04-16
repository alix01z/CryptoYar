package com.alix01z.cryptoyar.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.databinding.ItemRvTopCoinBinding
import com.alix01z.cryptoyar.models.DataItem
import com.bumptech.glide.Glide

class TopCoinRvAdapter(private val coinList:ArrayList<DataItem>): RecyclerView.Adapter<TopCoinRvAdapter.TopCoinRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopCoinRvViewHolder {
        val binding:ItemRvTopCoinBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context) ,
            R.layout.item_rv_top_coin , parent , false)
        return TopCoinRvViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: TopCoinRvViewHolder, position: Int) {
        holder.bindData(coinList[position])
    }

    class TopCoinRvViewHolder(private val binding: ItemRvTopCoinBinding): RecyclerView.ViewHolder(binding.root){

        fun bindData(coinData:DataItem){
            setCoinLogo(coinData)
            setTextColor(coinData)
            setDecimalsForPrice(coinData)
            binding.topCoinName.text = String.format("%s/USD", coinData.symbol)
            binding.topCoinChange.text = if (coinData.quotes[0].percentChange24h > 0) {
                String.format("+%.2f%%", coinData.quotes[0].percentChange24h)
            } else {
                String.format("%.2f%%", coinData.quotes[0].percentChange24h)
            }
            binding.executePendingBindings()
        }
        private fun setCoinLogo(coinData: DataItem) {
            Glide.with(binding.root)
                .load("https://s2.coinmarketcap.com/static/img/coins/32x32/" + coinData.id + ".png")
                .placeholder(R.drawable.baseline_bookmark_24)
                .into(binding.imgvCoinLogo)
        }
        private fun setTextColor(coinData: DataItem){
            val percentChange = coinData.quotes[0].percentChange24h
            binding.topCoinChange.setTextColor(if (percentChange < 0) Color.RED else Color.GREEN)
            binding.topCoinPrice.setTextColor(if (percentChange < 0) Color.RED else Color.GREEN)
        }
        private fun setDecimalsForPrice(coinEntity: DataItem){
            val price = coinEntity.quotes[0].price
            binding.topCoinPrice.text = when {
                price < 1 -> String.format("%.6f", price)
                price < 10 -> String.format("%.4f", price)
                else -> String.format("%.2f", price)
            }
        }

    }
    fun updateData(newData: List<DataItem>) {
        coinList.clear()
        coinList.addAll(newData)
        notifyDataSetChanged()
    }
}