package com.example.projectgroup2.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.R;

import java.util.ArrayList;

public class AdapterMaKH extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLKH> QLKH_List;
    @Override
    public int getCount() {
        return QLKH_List.size();
    }

    public AdapterMaKH(Context context, int layout, ArrayList<QLKH> QLKH_List) {
        this.context = context;
        this.layout = layout;
        this.QLKH_List = QLKH_List;
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
        ImageView img_SpinnerKH = convertView.findViewById(R.id.img_SpinnerKH);
        TextView tv_SpinnerNameKH = convertView.findViewById(R.id.tv_SpinnerNameKH);
        img_SpinnerKH.setImageBitmap(BitmapFactory.decodeByteArray(QLKH_List.get(position).getHinhAnh(), 0 , QLKH_List.get(position).getHinhAnh().length));
        tv_SpinnerNameKH.setText(QLKH_List.get(position).getTenKH());
        return convertView;
    }
}
