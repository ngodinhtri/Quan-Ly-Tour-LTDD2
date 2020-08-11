package com.example.projectgroup2.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.R;

import java.io.ByteArrayOutputStream;

public class create_customer extends AppCompatActivity {
    Button btn_Save;
    EditText pt_NhapTenKH , pt_NhapDiaChiKH;
    DBKhachHang dbKhachHang;
    ImageView img_KH;
    Button btn_ThemImg,btn_HuyImg;
    int REQUEST_CODE_MEDIA = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
         dbKhachHang = new DBKhachHang(create_customer.this);
        setControl();
        handleEvent();
    }
    public void setControl()
    {
        btn_Save = findViewById(R.id.btn_Save);
        pt_NhapTenKH = findViewById(R.id.pt_NhapTenKH);
        pt_NhapDiaChiKH = findViewById(R.id.pt_NhapDiaChiKH);
        btn_ThemImg = findViewById(R.id.btn_ThemImg);
        btn_HuyImg = findViewById(R.id.btn_HuyImg);
        img_KH = findViewById(R.id.img_KH);
    }
    public void handleEvent()
    {
//        CLick nut Save để thêm Khách Hàng
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(create_customer.this);
                builder.setTitle("Thông Báo");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                if(!pt_NhapTenKH.getText().toString().equals(""))
                {
                    if(!pt_NhapDiaChiKH.getText().toString().equals(""))
                    {
//                       Khởi tạo Khách Hàng
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_KH.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                        byte [] hinhAnh = byteArray.toByteArray();
                        QLKH qlkh = new QLKH();
                        qlkh.setTenKH(pt_NhapTenKH.getText().toString());
                        qlkh.setDiaChi(pt_NhapDiaChiKH.getText().toString());
                        qlkh.setHinhAnh(hinhAnh);
                        dbKhachHang.them(qlkh); // ad vào database
                        pt_NhapTenKH.setText(""); // clear dữ liệu Tên trong create Customer
                        pt_NhapDiaChiKH.setText("");// clear dữ liệu Địa Chỉ trong create Customer
                        Intent intent = new Intent(create_customer.this,ViewCustomer.class);
                        startActivity(intent);
                    }
                    else
                    {
                        builder.setMessage("Bạn hãy nhập địa chỉ!!!");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                else
                {
                    builder.setMessage("Bạn hãy nhập tên!!!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });


        btn_ThemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_MEDIA);
            }
        });
        btn_HuyImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_KH.setImageResource(R.drawable.white);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_MEDIA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_KH.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
