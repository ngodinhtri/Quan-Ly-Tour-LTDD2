package com.example.projectgroup2.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.Model.QLTour;
import com.example.projectgroup2.R;

public class create_tour extends AppCompatActivity {
    EditText pt_LoTrinh,pt_HanhTrinh,pt_GiaTour;
    Button btn_Save;
    DBTour dbTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tour);
        dbTour = new DBTour(create_tour.this);
        setControl();
        handleEvent();
    }
    public void setControl()
    {
        btn_Save = findViewById(R.id.btn_Save);
        pt_LoTrinh = findViewById(R.id.pt_LoTrinh);
        pt_HanhTrinh = findViewById(R.id.pt_HanhTrinh);
        pt_GiaTour = findViewById(R.id.pt_GiaTour);
    }
    public void handleEvent()
    {
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(create_tour.this);
                builder.setTitle("Thông Báo");
                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                if(!pt_LoTrinh.getText().toString().equals(""))
                {
                    if(!pt_HanhTrinh.getText().toString().equals(""))
                    {
                        if(!pt_GiaTour.getText().toString().equals(""))
                        {
                            QLTour qlTour =  new QLTour();
                            qlTour.setLoTrinh(pt_LoTrinh.getText().toString());
                            qlTour.setHanhTrinh(pt_HanhTrinh.getText().toString());
                            qlTour.setGiaTour(Integer.parseInt(pt_GiaTour.getText().toString()));
                            dbTour.them(qlTour);
                            pt_LoTrinh.setText("");
                            pt_HanhTrinh.setText("");
                            pt_GiaTour.setText("");
                            Intent intent = new Intent(create_tour.this, ViewTour.class);
                            startActivity(intent);
                        }else
                        {
                            builder.setMessage("Hãy Nhập Giá Tour");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }else
                    {
                        builder.setMessage("Hãy Nhập Hành Trình");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }else
                {
                    builder.setMessage("Hãy Nhập Thông Báo");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}
