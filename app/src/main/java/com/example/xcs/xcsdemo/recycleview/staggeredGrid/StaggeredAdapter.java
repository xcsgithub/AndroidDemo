package com.example.xcs.xcsdemo.recycleview.staggeredGrid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xcs.xcsdemo.R;
import com.example.xcs.xcsdemo.model.image.Image;
import com.example.xcs.xcsdemo.widget.CustomImageView;

import java.util.List;

/**
 * @author RWX
 * @time 2019-04-12.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private List<Image> imageList;
    private Context context;

    public StaggeredAdapter(Context context,List<Image> images){
        this.context = context;
        imageList = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = imageList.get(position);
        holder.imageView.setImageSize(image.getWidth(),image.getHeight());
        holder.tvName.setText(image.getId());
        Glide.with(context)
                .load(image.getUrls().getSmall())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

    public void setImageList(List<Image> imageList){
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CustomImageView imageView;

        TextView tvName;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
