package com.example.projectgroup2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.Model.QLPhieuDK;
import com.example.projectgroup2.R;
import com.example.projectgroup2.Views.Information_PhieuDK;

import java.util.ArrayList;

public class AdapterPhieuDK extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLPhieuDK> QLPhieuDK;
    public AdapterPhieuDK(Context context, int layout, ArrayList<com.example.projectgroup2.Model.QLPhieuDK> QLPhieuDK) {
        this.context = context;
        this.layout = layout;
        this.QLPhieuDK = QLPhieuDK;
    }

    @Override

    public int getCount() {
        return QLPhieuDK.size();
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
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout,null);
        ImageView img_PDK = convertView.findViewById(R.id.img_PDK);
        TextView tv_card = convertView.findViewById(R.id.tv_card);
        TextView tv_name = convertView.findViewById(R.id.tv_nameShow);
        TextView tv_tour = convertView.findViewById(R.id.tv_tour);
        ImageButton btn_transfer = convertView.findViewById(R.id.btn_transfer);
        ImageButton btn_delete = convertView.findViewById(R.id.btn_delete);
        tv_card.setText("Mã Phiếu: " + QLPhieuDK.get(position).getSoPhieu());
        DBKhachHang dbKhachHang = new DBKhachHang(context);
        for(int i = 0 ; i < dbKhachHang.getAllTimKiem(QLPhieuDK.get(position).getMaKH()).size();i++)
        {
            tv_name.setText(dbKhachHang.getAllTimKiem(QLPhieuDK.get(position).getMaKH()).get(0).getTenKH());
        }
        DBTour dbTour = new DBTour(context);
        for(int i = 0; i < dbTour.getAllTimKiem(QLPhieuDK.get(position).getMaTour()).size();i++)
        {
            tv_tour.setText(dbTour.getAllTimKiem(QLPhieuDK.get(position).getMaTour()).get(0).getLoTrinh());
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB_PhieuDK db_phieuDK = new DB_PhieuDK(context);
                db_phieuDK.xoa(QLPhieuDK.get(position));
                QLPhieuDK = db_phieuDK.getAllPhieuDK();
                notifyDataSetChanged();
            }
        });
        btn_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Information_PhieuDK.class);
                Bundle bundle = new Bundle();
                bundle.putInt("SoPhieu",QLPhieuDK.get(position).getSoPhieu());
                bundle.putString("NgayDK",QLPhieuDK.get(position).getNgayDK());
                bundle.putInt("MaKH",QLPhieuDK.get(position).getMaKH());
                bundle.putInt("MaTour",QLPhieuDK.get(position).getMaTour());
                bundle.putInt("SoNguoi",QLPhieuDK.get(position).getSoNguoi());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        notifyDataSetChanged();
        return convertView;
    }
}
