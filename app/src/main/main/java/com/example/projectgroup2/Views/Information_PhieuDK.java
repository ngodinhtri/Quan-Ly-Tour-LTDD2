package com.example.projectgroup2.Views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Adapter.AdapterMaKH;
import com.example.projectgroup2.Adapter.AdapterMaTour;
import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.Model.QLPhieuDK;
import com.example.projectgroup2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Information_PhieuDK extends AppCompatActivity {
    TextView tv_NgayDK;
    Button btn_Date;
    Spinner sp_MaKH2,sp_MaTour2;
    EditText et_SoNguoi2;
    DBKhachHang dbKhachHang;
    ImageButton img_sua;
    DBTour dbTour;
    Bundle bundle;
    SimpleDateFormat customDate;
    String []arrayDate;
    int maKH = 0;
    int maTour = 0;
    DB_PhieuDK db_phieuDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information__phieu_d_k);
        dbKhachHang = new DBKhachHang(Information_PhieuDK.this);
        db_phieuDK = new DB_PhieuDK(Information_PhieuDK.this);
        dbTour = new DBTour(Information_PhieuDK.this);
        setControl();
        khoitao();
        handleEvent();
    }
    public void setControl()
    {
        tv_NgayDK = findViewById(R.id.tv_NgayDK);
        btn_Date = findViewById(R.id.btn_Date);
        sp_MaKH2 = findViewById(R.id.sp_MaKH2);
        sp_MaTour2 = findViewById(R.id.sp_MaTour2);
        et_SoNguoi2 = findViewById(R.id.et_SoNguoi2);
        img_sua = findViewById(R.id.img_sua);
    }
    public void khoitao()
    {
        Calendar calendar = Calendar.getInstance();
        bundle = getIntent().getExtras();
        tv_NgayDK.setText(String.valueOf(bundle.getString("NgayDK")));
        AdapterMaKH adapterMaKH = new AdapterMaKH(Information_PhieuDK.this,R.layout.spinner_b1_phieudk,dbKhachHang.getAllSV());
        sp_MaKH2.setAdapter(adapterMaKH);
        for(int i = 0 ; i < dbKhachHang.getAllSV().size();i++)
        {
                if(dbKhachHang.getAllSV().get(i).getMaKH() == bundle.getInt("MaKH"))
                {
                    sp_MaKH2.setSelection(i);
                }
        }
        AdapterMaTour adapterMaTour = new AdapterMaTour(Information_PhieuDK.this,R.layout.spinner_tour,dbTour.getAllTour());
        sp_MaTour2.setAdapter(adapterMaTour);
        for(int i = 0 ; i < dbTour.getAllTour().size();i++)
        {
            if(dbTour.getAllTour().get(i).getMaTour() == bundle.getInt("MaTour"))
            {
                sp_MaTour2.setSelection(i);
            }
        }
        et_SoNguoi2.setText(String.valueOf(bundle.getInt("SoNguoi")));
        customDate = new SimpleDateFormat("dd/MM/yyyy");
        arrayDate = String.valueOf(customDate.format(calendar.getTime())).split("/");
    }
    public void handleEvent()
    {
        sp_MaKH2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKH = dbKhachHang.getAllSV().get(position).getMaKH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_MaTour2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTour = dbTour.getAllTour().get(position).getMaTour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Information_PhieuDK.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year,month,dayOfMonth);
                        tv_NgayDK.setText(customDate.format(cal.getTime()));
                    }
                },Integer.parseInt(arrayDate[2]),Integer.parseInt(arrayDate[1]) - 1,Integer.parseInt(arrayDate[0]));
                datePickerDialog.show();
            }
        });
        img_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                builder.setTitle("Thông Báo");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                if(!tv_NgayDK.getText().toString().equals(""))
                {
                    if(!et_SoNguoi2.getText().toString().equals(""))
                    {
                        QLPhieuDK qlPhieuDK = new QLPhieuDK();
                        qlPhieuDK.setSoPhieu(bundle.getInt("SoPhieu"));
                        qlPhieuDK.setNgayDK(tv_NgayDK.getText().toString());
                        qlPhieuDK.setMaKH(maKH);
                        qlPhieuDK.setMaTour(maTour);
                        qlPhieuDK.setSoNguoi(Integer.parseInt(et_SoNguoi2.getText().toString()));
                        db_phieuDK.sua(qlPhieuDK);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(Information_PhieuDK.this);
                        builder1.setTitle("Thông Báo");
                        builder1.setMessage("Cập Nhật Thành Công,Nhấn Ok để quay về Danh Sách Phiếu Đăng Ký");
                        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Information_PhieuDK.this,MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();

                    }else
                    {
                        builder.setMessage("Bạn chưa nhập Số Lượng Người");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }else
                {
                    builder.setMessage("Bạn chưa nhập Ngày Đăng Ký");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}
