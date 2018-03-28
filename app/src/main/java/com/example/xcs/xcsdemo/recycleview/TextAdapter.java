package com.example.xcs.xcsdemo.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xcs.xcsdemo.R;

import java.util.ArrayList;

/**
 * Created by Xcs on 2018-02-26.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.BaseViewHolder> {

    ArrayList<String> list ;
    Context context;
    public TextAdapter(Context context, ArrayList<String> list){
        this.list = list;
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
         holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public BaseViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}
