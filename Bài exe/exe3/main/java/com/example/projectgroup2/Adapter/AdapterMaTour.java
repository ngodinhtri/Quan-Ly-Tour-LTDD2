package com.example.projectgroup2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectgroup2.Model.QLTour;
import com.example.projectgroup2.R;

import java.util.ArrayList;

public class AdapterMaTour extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLTour> QLTOUR_LIST;

    public AdapterMaTour(Context context, int layout, ArrayList<QLTour> QLTOUR_LIST) {
        this.context = context;
        this.layout = layout;
        this.QLTOUR_LIST = QLTOUR_LIST;
    }

    @Override
    public int getCount() {
        return QLTOUR_LIST.size();
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
        TextView tv_LoTrinh = convertView.findViewById(R.id.tv_LoTrinh);
        TextView tv_HanhTrinh = convertView.findViewById(R.id.tv_HanhTrinh);
        TextView tv_GiaTour = convertView.findViewById(R.id.tv_GiaTour);
        tv_LoTrinh.setText(QLTOUR_LIST.get(position).getLoTrinh());
        tv_HanhTrinh.setText(QLTOUR_LIST.get(position).getHanhTrinh());
        tv_GiaTour.setText(QLTOUR_LIST.get(position).getGiaTour() + "VNĐ");
        return convertView;
    }
}
