package com.example.projectgroup2.Views;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.Model.QLPhieuDK;
import com.example.projectgroup2.Model.QLTour;
import com.example.projectgroup2.R;

public class ViewThongKe extends AppCompatActivity {
    EditText et_thongke;
    DBKhachHang dbKhachHang;
    DBTour dbTour;
    DB_PhieuDK db_phieuDK;
    String str = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thong_ke);
        dbKhachHang = new DBKhachHang(ViewThongKe.this);
        dbTour = new DBTour(ViewThongKe.this);
        db_phieuDK = new DB_PhieuDK(ViewThongKe.this);
        setControl();
        handle();
    }
    public void setControl()
    {
        et_thongke = findViewById(R.id.et_thongke);
    }
    public void handle()
    {
        for(int i = 0 ; i < dbKhachHang.getAllSV().size() ; i++)
        {
            str += "\n" + dbKhachHang.getAllSV().get(i).getTenKH() + '\n';
            int maKH =dbKhachHang.getAllSV().get(i).getMaKH();
            for(QLPhieuDK t : db_phieuDK.getAllMaTour(maKH)){
                for(QLTour tour : dbTour.getAllTourByTour(t.getMaTour())){
                    str += " _______ " + tour.getLoTrinh() + "_______\n";
                    str += "Giá Tiền: " + tour.getGiaTour()+ '\n';
                    str += "Hành Trình: " + tour.getHanhTrinh()+ '\n' + '\n';

                }
            }
            str += "------------------------------\n";
        }
        et_thongke.setText(str);
    }
}
