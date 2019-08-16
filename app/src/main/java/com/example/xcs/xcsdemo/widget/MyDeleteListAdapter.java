package com.example.xcs.xcsdemo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xcs.xcsdemo.R;

import java.util.List;

/**
 * @author RWX
 * @time 2019-08-16.
 */
public class MyDeleteListAdapter extends ArrayAdapter<String> {
    public MyDeleteListAdapter(@NonNull Context context, int resource, List<String> contentList) {
        super(context, resource,contentList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_listview, null);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.tv_item);
        textView.setText( getItem(position));
        return view;
    }
}
