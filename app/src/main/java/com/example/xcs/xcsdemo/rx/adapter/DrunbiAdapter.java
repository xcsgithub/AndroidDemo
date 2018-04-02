package com.example.xcs.xcsdemo.rx.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.rx.model.DrunbiImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Xcs on 2018-04-02.
 */

public class DrunbiAdapter extends RecyclerView.Adapter {

    List<DrunbiImage> images;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new DebounceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder)holder;
        DrunbiImage image = images.get(position);
        Glide.with(holder.itemView.getContext())
                .load(image.image_url)
                .into(debounceViewHolder.imageView);
        debounceViewHolder.textView.setText(image.description);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public void setImages(List<DrunbiImage> images){
        this.images = images;
        notifyDataSetChanged();
    }

    static class DebounceViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageIv)
        ImageView imageView;
        @BindView(R.id.descriptionTv)
        TextView textView;
        public DebounceViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
