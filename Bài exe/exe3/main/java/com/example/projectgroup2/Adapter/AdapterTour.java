package com.example.projectgroup2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Model.QLTour;
import com.example.projectgroup2.R;
import com.example.projectgroup2.Views.Information_Tour;

import java.util.ArrayList;

public class AdapterTour extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLTour> QLTour_List;
    public AdapterTour(Context context, int layout, ArrayList<com.example.projectgroup2.Model.QLTour> QLTour) {
        this.context = context;
        this.layout = layout;
        this.QLTour_List = QLTour;
    }

    @Override
    public int getCount() {
        return QLTour_List.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        Log.e("getView: ", "asdsad");
        convertView = inflater.inflate(layout,null);
        ImageView img_tour = convertView.findViewById(R.id.img_tour);
        TextView tv_LoTrinh = convertView.findViewById(R.id.tv_LoTrinh);
        TextView tv_HanhTrinh = convertView.findViewById(R.id.tv_HanhTrinh);
        TextView tv_GiaTour = convertView.findViewById(R.id.tv_GiaTour);
        ImageButton btn_delete = convertView.findViewById(R.id.btn_delete);
        ImageButton btn_Transfer = convertView.findViewById(R.id.btn_Transfer);
        tv_LoTrinh.setText(QLTour_List.get(position).getLoTrinh());
        tv_HanhTrinh.setText(QLTour_List.get(position).getHanhTrinh());
        tv_GiaTour.setText(String.valueOf(QLTour_List.get(position).getGiaTour()) + "VNĐ");
        notifyDataSetChanged();
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTour dbTour = new DBTour(context);
                dbTour.xoa(QLTour_List.get(position));
                QLTour_List = dbTour.getAllTour();
                notifyDataSetChanged();
            }
        });
        btn_Transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Information_Tour.class);
                Bundle bundle = new Bundle();
                bundle.putInt("MaTour",QLTour_List.get(position).getMaTour());
                bundle.putString("LoTrinh",QLTour_List.get(position).getLoTrinh());
                bundle.putString("HanhTrinh",QLTour_List.get(position).getHanhTrinh());
                bundle.putInt("GiaTour",QLTour_List.get(position).getGiaTour());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
