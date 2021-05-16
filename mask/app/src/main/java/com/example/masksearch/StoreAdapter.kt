package com.example.masksearch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masksearch.StoreAdapter.StoreViewholder
import com.example.masksearch.model.Store
import java.util.*

class StoreAdapter : RecyclerView.Adapter<StoreViewholder>() {

    var mItems: List<Store> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewholder(view)
    }

    override fun onBindViewHolder(holder: StoreViewholder, position: Int) {
        val store: Store = mItems[position]
        holder.tvName.text = store.name
        holder.tvAddr.text = store.addr
        holder.tvDistance.text = "Distance TODO"
        var remainStat = ""
        var count = ""
        var color = 0
        when (store.remain_stat) {
            "plenty" -> {
                remainStat = "충분"
                count = "100개 이상"
                color = Color.GREEN
            }
            "some" -> {
                remainStat = "여유"
                count = "10개 이상"
                color = Color.YELLOW
            }
            "few" -> {
                remainStat = "매진임박"
                count = "2개 이상"
                color = Color.RED
            }
            "empty" -> {
                remainStat = "재고없음"
                count = "1개 이하"
                color = Color.GRAY
            }
            else -> {
            }
        }
        holder.tvRemainStat.text = remainStat
        holder.tvRemainStat.setTextColor(color)
        holder.tvCount.text = count
        holder.tvCount.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /** 아이템뷰 정보를 가지고 있는 클래스  */
    class StoreViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvAddr: TextView = itemView.findViewById(R.id.tv_addr)
        var tvDistance: TextView = itemView.findViewById(R.id.tv_distance)
        var tvRemainStat: TextView = itemView.findViewById(R.id.tv_remain_stat)
        var tvCount: TextView = itemView.findViewById(R.id.tv_count)
    }

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged() // UI 갱신
    }
}