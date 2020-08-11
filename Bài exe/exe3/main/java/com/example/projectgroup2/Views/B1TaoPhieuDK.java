package com.example.projectgroup2.Views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class B1TaoPhieuDK extends AppCompatActivity {
    EditText et_SoNguoi;
    TextView tv_NgayDK;
    Button btn_Date;
    Spinner sp_MaKH,sp_MaTour;
    SimpleDateFormat customDate;
    Button btn_QuaBuoc;
    DBKhachHang dbKhachHang;
    DBTour dbTour;
    String []arrayDate;
    int maKHMemo = 0;
    int maTourMemo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_tao_phieu_d_k);
        setControl();
        setValue();
        handleEvent();
    }
    public void setControl()
    {
        et_SoNguoi = findViewById(R.id.et_SoNguoi);
        btn_Date = findViewById(R.id.btn_Date);
        tv_NgayDK = findViewById(R.id.tv_NgayDK);
        sp_MaKH = findViewById(R.id.sp_MaKH);
        btn_QuaBuoc = findViewById(R.id.btn_QuaBuoc);
        sp_MaTour = findViewById(R.id.sp_MaTour);
    }
    public void setValue()
    {
        // Thiết Lập Spinner
        Calendar calendar = Calendar.getInstance();
        dbKhachHang = new DBKhachHang(B1TaoPhieuDK.this);
        dbTour = new DBTour(B1TaoPhieuDK.this);
//        Mã Khách Hàng
        AdapterMaKH adapterMaKH = new AdapterMaKH(B1TaoPhieuDK.this,R.layout.spinner_b1_phieudk,dbKhachHang.getAllSV());
        sp_MaKH.setAdapter(adapterMaKH);
        sp_MaKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maKHMemo = dbKhachHang.getAllSV().get(position).getMaKH();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        Mã Tour
        AdapterMaTour adapterMaTour = new AdapterMaTour(B1TaoPhieuDK.this,R.layout.spinner_tour,dbTour.getAllTour());
        sp_MaTour.setAdapter(adapterMaTour);
        sp_MaTour.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTourMemo = dbTour.getAllTour().get(position).getMaTour();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Thiết Lập Ngày
        customDate = new SimpleDateFormat("dd/MM/yyyy");
        tv_NgayDK.setText(customDate.format(calendar.getTime()));
        arrayDate = String.valueOf(customDate.format(calendar.getTime())).split("/");
    }
    public void handleEvent()
    {
        btn_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(B1TaoPhieuDK.this, new DatePickerDialog.OnDateSetListener() {
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
        btn_QuaBuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(B1TaoPhieuDK.this);
                builder.setTitle("Thông Báo");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                if(!tv_NgayDK.getText().toString().equals(""))
                {
                    if(!et_SoNguoi.getText().toString().equals(""))
                    {
                        QLPhieuDK qlPhieuDK = new QLPhieuDK();
                        qlPhieuDK.setNgayDK(tv_NgayDK.getText().toString());
                        qlPhieuDK.setMaKH(maKHMemo);
                        qlPhieuDK.setMaTour(maTourMemo);
                        qlPhieuDK.setSoNguoi(Integer.parseInt(et_SoNguoi.getText().toString()));
                        DB_PhieuDK db_phieuDK = new DB_PhieuDK(B1TaoPhieuDK.this);
                        db_phieuDK.them(qlPhieuDK);
                        et_SoNguoi.setText("");
                        Intent intent = new Intent(B1TaoPhieuDK.this,MainActivity.class);
                        startActivity(intent);
                    }else
                    {
                        Log.e("asd","vào ");
                        builder.setMessage("Bạn Chưa Nhập Số Người Đi");
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
