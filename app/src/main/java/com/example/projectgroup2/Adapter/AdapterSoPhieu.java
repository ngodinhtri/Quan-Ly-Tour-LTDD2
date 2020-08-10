package com.example.projectgroup2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectgroup2.Model.QLPhieuDK;
import com.example.projectgroup2.R;

import java.util.ArrayList;

public class AdapterSoPhieu extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLPhieuDK> list;

    public AdapterSoPhieu(Context context, int layout, ArrayList<QLPhieuDK> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,null);
        TextView item = convertView.findViewById(R.id.item);
        item.setText(String.valueOf(list.get(position).getSoPhieu()));
        return convertView;
    }
}
