package com.example.projectgroup2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.R;
import com.example.projectgroup2.Views.Information_customer;

import java.util.ArrayList;

public class AdapterCustomer extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<QLKH> QLKH_List;
    public AdapterCustomer(Context context, int layout, ArrayList<QLKH> QLKH_List) {
        this.context = context;
        this.layout = layout;
        this.QLKH_List = QLKH_List;
    }

    @Override
    public int getCount() {
        return QLKH_List.size();
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
        convertView = inflater.inflate(layout,null);
        TextView tv_nameShow = convertView.findViewById(R.id.tv_nameShow);
        TextView tv_Address = convertView.findViewById(R.id.tv_Address);
        ImageButton img_delete2 = convertView.findViewById(R.id.img_delete2);
        ImageButton img_transfer = convertView.findViewById(R.id.img_transfer);
        tv_nameShow.setText(QLKH_List.get(position).getTenKH());
        tv_Address.setText(QLKH_List.get(position).getDiaChi());
        ImageView img_user2 = convertView.findViewById(R.id.img_user2);
        img_user2.setImageBitmap(BitmapFactory.decodeByteArray(QLKH_List.get(position).getHinhAnh(), 0 , QLKH_List.get(position).getHinhAnh().length));
        img_delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông Báo");
                builder.setMessage("Nếu Nhấn Xóa thì sẽ Xóa Luôn Phiếu Đăng Ký Của Khách Hàng Này !");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBKhachHang dbKhachHang = new DBKhachHang(context);
                        DB_PhieuDK db_phieuDK = new DB_PhieuDK(context);
                        db_phieuDK.xoaPhieuDKTheoMaKH(QLKH_List.get(position));
                        dbKhachHang.xoa(QLKH_List.get(position));
                        QLKH_List = dbKhachHang.getAllSV();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        img_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Information_customer.class);
                Bundle bundle = new Bundle();
                bundle.putInt("MaKH",QLKH_List.get(position).getMaKH());
                bundle.putString("TenKH",QLKH_List.get(position).getTenKH());
                bundle.putString("DiaChi",QLKH_List.get(position).getDiaChi());
                bundle.putByteArray("HinhAnh",QLKH_List.get(position).getHinhAnh());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
