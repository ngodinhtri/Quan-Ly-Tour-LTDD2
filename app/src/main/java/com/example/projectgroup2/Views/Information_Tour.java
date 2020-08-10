package com.example.projectgroup2.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Model.QLTour;
import com.example.projectgroup2.R;

public class Information_Tour extends AppCompatActivity {
    EditText et_LoTrinh,et_HanhTrinh ,et_GiaTour;
    ImageButton btn_Sua;
    Bundle bundle;
    DBTour dbTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information__tour);
        dbTour = new DBTour(Information_Tour.this);
        setControl();
        handleControl();
    }
    public void setControl()
    {
        et_LoTrinh = findViewById(R.id.et_LoTrinh);
        et_HanhTrinh = findViewById(R.id.et_HanhTrinh);
        et_GiaTour = findViewById(R.id.et_GiaTour);
        btn_Sua = findViewById(R.id.btn_Sua);
    }
    public void khoiTao()
    {
        bundle = getIntent().getExtras();
        et_LoTrinh.setText(bundle.getString("LoTrinh"));
        et_HanhTrinh.setText(bundle.getString("HanhTrinh"));
        et_GiaTour.setText(String.valueOf(bundle.getInt("GiaTour")));
    }
    public void handleControl()
    {
        khoiTao();
        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Information_Tour.this);
                builder.setTitle("Thông Báo");
                if(!et_LoTrinh.getText().toString().equals(""))
                {
                    if(!et_HanhTrinh.getText().toString().equals(""))
                    {
                        if(!et_GiaTour.getText().toString().equals(""))
                        {
                            QLTour qlTour = new QLTour();
                            qlTour.setLoTrinh(et_LoTrinh.getText().toString());
                            qlTour.setHanhTrinh(et_HanhTrinh.getText().toString());
                            qlTour.setGiaTour(Integer.parseInt(et_GiaTour.getText().toString()));
                            qlTour.setMaTour(bundle.getInt("MaTour"));
                            dbTour.sua(qlTour);
                            builder.setMessage("Bạn Đã Cập Nhật Thành Công ^^");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(Information_Tour.this,ViewTour.class);
                                    startActivity(intent);
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }else
                        {
                            builder.setMessage("Bạn phải nhập Giá Tour");
                            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }else
                    {
                        builder.setMessage("Bạn phải nhập Hành Trình");
                        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }else
                {
                    builder.setMessage("Bạn phải nhập Lộ Trình");
                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}

