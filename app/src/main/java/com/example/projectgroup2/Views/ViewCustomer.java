package com.example.projectgroup2.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Adapter.AdapterCustomer;
import com.example.projectgroup2.Database.DBKhachHang;
import com.example.projectgroup2.Model.QLKH;
import com.example.projectgroup2.R;

public class ViewCustomer extends AppCompatActivity {
    ListView lv_Customer;
    ImageButton btn_createKH;
    QLKH qlKH;
    DBKhachHang dbKhachHang;
    AdapterCustomer adapterCustomer; // adapter of Customer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
         dbKhachHang = new DBKhachHang(ViewCustomer.this);
        setControl();
        handleEvent();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AdapterCustomer adapterCustomer = new AdapterCustomer(ViewCustomer.this,R.layout.listview_customer,dbKhachHang.getAllKhachHangSearch(newText));
                lv_Customer.setAdapter(adapterCustomer);
                adapterCustomer.notifyDataSetChanged();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.actionTransfer:
                Log.e("actionTransfer: ","clicked");
                Intent intent = new Intent(ViewCustomer.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplication(),"ad",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public  void setControl()
    {
        lv_Customer = findViewById(R.id.lv_Customer);
        btn_createKH = findViewById(R.id.btn_createKH);

    }
    private  void khoitao()
    {
        adapterCustomer = new AdapterCustomer(ViewCustomer.this,R.layout.listview_customer,dbKhachHang.getAllSV());
        lv_Customer.setAdapter(adapterCustomer);
        adapterCustomer.notifyDataSetChanged();
    }
    public  void handleEvent()
    {
        khoitao();
        btn_createKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),create_customer.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewCustomer.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
