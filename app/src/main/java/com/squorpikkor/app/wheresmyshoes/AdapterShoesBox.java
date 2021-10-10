package com.squorpikkor.app.wheresmyshoes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squorpikkor.app.wheresmyshoes.data.ShoesBox;

import java.util.ArrayList;
import java.util.List;

public class AdapterShoesBox extends RecyclerView.Adapter<AdapterShoesBox.ShoesBoxHolder> {

    private List<ShoesBox> list = new ArrayList<>();

    /**Лисенер, который будет возвращать объект Nuclide по позиции выбранного элемента*/
    private OnDeviceClickListener onDeviceClickListener;
    private OnItemClickListener onItemClickListener;

    public interface OnDeviceClickListener {
        void onDeviceClick(ShoesBox shoesBox);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnObjectClickListener(OnDeviceClickListener onDeviceClickListener) {
        this.onDeviceClickListener = onDeviceClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<ShoesBox> list) {
        if (list==null) this.list = new ArrayList<>();
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoesBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        return new ShoesBoxHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesBoxHolder holder, int position) {
        ShoesBox shoesBox = list.get(position);
        holder.img.setImageResource(shoesBox.getImageResId());
//        holder.name.setText("№"+shoesBox.getBoxNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShoesBoxHolder extends RecyclerView.ViewHolder {

        ImageView img;
//        TextView name;

        public ShoesBoxHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image);
//            name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(view -> {
                if (onDeviceClickListener != null) onDeviceClickListener.onDeviceClick(list.get(getAdapterPosition()));
                if (onItemClickListener != null) onItemClickListener.onItemClick(getAdapterPosition());
            });
        }
    }
}
