package com.example.masksearch;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.masksearch.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewholder> {

    List<Store> mItems = new ArrayList<>();

    @NonNull
    @Override
    public StoreViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new StoreViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewholder holder, int position) {
        Store store = mItems.get(position);

        holder.tvName.setText(store.getName());
        holder.tvAddr.setText(store.getAddr());
        holder.tvDistance.setText("Distance TODO");

        String remainStat = "";
        String count = "";
        int color = 0;
        switch (store.getRemainStat()) {
            case "plenty" :
                remainStat = "충분";
                count = "100개 이상";
                color = Color.GREEN;
                break;
            case "some" :
                remainStat = "여유";
                count = "10개 이상";
                color = Color.YELLOW;
                break;
            case "few":
                remainStat = "매진임박";
                count = "2개 이상";
                color = Color.RED;
                break;
            case "empty":
                remainStat = "재고없음";
                count = "1개 이하";
                color = Color.GRAY;
                break;
            default:
        }
        holder.tvRemainStat.setText(remainStat);
        holder.tvRemainStat.setTextColor(color);
        holder.tvCount.setText(count);
        holder.tvCount.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /** 아이템뷰 정보를 가지고 있는 클래스 */
    static class StoreViewholder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAddr;
        TextView tvDistance;
        TextView tvRemainStat;
        TextView tvCount;

        public StoreViewholder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAddr = itemView.findViewById(R.id.tv_addr);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            tvRemainStat = itemView.findViewById(R.id.tv_remain_stat);
            tvCount = itemView.findViewById(R.id.tv_count);
        }
    }

    public void updateItems(List<Store> items) {
        mItems = items;
        notifyDataSetChanged();     // UI 갱신
    }
}
