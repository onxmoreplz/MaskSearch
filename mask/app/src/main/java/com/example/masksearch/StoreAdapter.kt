package com.example.masksearch

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.masksearch.StoreAdapter.StoreViewholder
import com.example.masksearch.databinding.ItemStoreBinding
import com.example.masksearch.model.Store
import java.util.*

class StoreAdapter : RecyclerView.Adapter<StoreViewholder>() {

    var mItems: List<Store> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return StoreViewholder(view)
    }

    override fun onBindViewHolder(holder: StoreViewholder, position: Int) {
        holder.binding.store = mItems[position]
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /** 아이템뷰 정보를 가지고 있는 클래스  */
    class StoreViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemStoreBinding.bind(itemView)
    }

    fun updateItems(items: List<Store>) {
        mItems = items
        notifyDataSetChanged() // UI 갱신
    }
}

@BindingAdapter("remainStatInAdapter")
fun setReaminStat(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.text = "충분"
        "some" -> textView.text = "여유"
        "few" -> textView.text = "매진임박"
        "empty" -> textView.text = "재고없음"
        else -> { }
    }
}

@BindingAdapter("countInAdapter")
fun setCount(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.text = "100개 이상"
        "some" -> textView.text = "30개 이상"
        "few" -> textView.text = "2개 이상"
        "empty" -> textView.text = "1개 이하"
        else -> { }
    }
}

@BindingAdapter("colorInAdapter")
fun setColor(textView: TextView, store: Store) {
    when (store.remain_stat) {
        "plenty" -> textView.setTextColor(Color.GREEN)
        "some" -> textView.setTextColor(Color.YELLOW)
        "few" -> textView.setTextColor(Color.RED)
        "empty" -> textView.setTextColor(Color.GRAY)
        else -> { }
    }
}