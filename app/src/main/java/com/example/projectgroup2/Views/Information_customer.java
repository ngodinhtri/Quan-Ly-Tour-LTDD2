package com.example.projectgroup2.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Adapter.AdapterSoPhieu;
import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.R;

import java.io.ByteArrayOutputStream;

public class Information_customer extends AppCompatActivity {
    EditText tv_TenKH,tv_DiaChiKH;
    Bundle bundle;
    Button btn_ThemAnh;
    ImageView img_KH;
    ImageButton imgBtn_Fix;
    DBKhachHang dbKhachHang;
    DB_PhieuDK db_phieuDK;
    int REQUEST_CODE_MEDIA = 123;
    ListView lv_SoPhieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_customer);
        dbKhachHang = new DBKhachHang(Information_customer.this);
        db_phieuDK = new DB_PhieuDK(Information_customer.this);
        setControl();
        handleEvent();
    }
    public void setControl()
    {
        lv_SoPhieu = findViewById(R.id.lv_SoPhieu);
        tv_TenKH = findViewById(R.id.tv_TenKH);
        tv_DiaChiKH = findViewById(R.id.tv_DiaChiKH);
        imgBtn_Fix = findViewById(R.id.imgBtn_Fix);
        img_KH = findViewById(R.id.img_KH);
        btn_ThemAnh = findViewById(R.id.btn_ThemAnh);
    }
    public void KhoiTao()
    {

        bundle = getIntent().getExtras();
        tv_TenKH.setText(bundle.getString("TenKH"));
        tv_DiaChiKH.setText(bundle.getString("DiaChi"));
        AdapterSoPhieu adapterSoPhieu = new AdapterSoPhieu(Information_customer.this,R.layout.lv,db_phieuDK.getAllTheoMaKH(String.valueOf(bundle.getInt("MaKH"))));
        lv_SoPhieu.setAdapter(adapterSoPhieu);
        img_KH.setImageBitmap(BitmapFactory.decodeByteArray(bundle.getByteArray("HinhAnh"), 0 , bundle.getByteArray("HinhAnh").length));
    }
    public void handleEvent()
    {
        KhoiTao();
        imgBtn_Fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Information_customer.this);
                builder.setTitle("Thông Báo");
                if(!tv_TenKH.getText().toString().equals(""))
                {
                    if(!tv_DiaChiKH.getText().toString().equals(""))
                    {
                        QLKH qlkh = new QLKH();
                        qlkh.setMaKH(bundle.getInt("MaKH"));
                        qlkh.setTenKH(tv_TenKH.getText().toString());
                        qlkh.setDiaChi(tv_DiaChiKH.getText().toString());
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) img_KH.getDrawable();
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                        byte [] hinhAnh = byteArray.toByteArray();
                        qlkh.setHinhAnh(hinhAnh);
                        dbKhachHang.sua(qlkh);
                        builder.setMessage("Bạn Đã Cập Nhật Thành Công");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Information_customer.this,ViewCustomer.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
                        AlertDialog alertDialog =  builder.create();
                        alertDialog.show();
                    }else
                    {
                        builder.setMessage("Bạn Hãy Nhập Địa Chỉ Khách Hàng");
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
                    builder.setMessage("Bạn Hãy Nhập Tên Khách Hàng");
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
        btn_ThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_MEDIA);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_MEDIA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_KH.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
