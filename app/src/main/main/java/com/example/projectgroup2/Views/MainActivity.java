package com.example.projectgroup2.Views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectgroup2.Adapter.AdapterPhieuDK;
import com.example.projectgroup2.Database.DB_PhieuDK;
import com.example.projectgroup2.R;

public class MainActivity extends AppCompatActivity {

    Button btn_DKTour,btn_DKCustomer;
    ListView lv_PDK;
    DB_PhieuDK db_phieuDK;
    ImageButton btn_Create;
    private  long backPressedTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db_phieuDK = new DB_PhieuDK(MainActivity.this);
        setControl();
        handleEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.actionSearch);
        SearchView  searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AdapterPhieuDK adapterPhieuDK = new AdapterPhieuDK(MainActivity.this,R.layout.listview_phieudangky,db_phieuDK.getAllPhieuDKSearch(newText));
                lv_PDK.setAdapter(adapterPhieuDK);
                adapterPhieuDK.notifyDataSetChanged();
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
                break;
            default:
                Toast.makeText(getApplication(),"ad",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setControl(){
        btn_DKTour = findViewById(R.id.btn_DKTour);
        btn_DKCustomer = findViewById(R.id.btn_DKCustomer);
        lv_PDK = findViewById(R.id.lv_PDK);
        btn_Create = findViewById(R.id.btn_Create);
    }
    public void khoiTao()
    {
        AdapterPhieuDK adapterPhieuDK = new AdapterPhieuDK(MainActivity.this,R.layout.listview_phieudangky,db_phieuDK.getAllPhieuDK());
        lv_PDK.setAdapter(adapterPhieuDK);
        adapterPhieuDK.notifyDataSetChanged();
    }
    public void handleEvent(){
        khoiTao();

        btn_DKTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                MyBounce interpolator = new MyBounce(0.2,20);
                animation.setInterpolator(interpolator);
                btn_DKTour.startAnimation(animation);

                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this,ViewTour.class);
                        startActivity(intent);
                    }
                },900);

            }
        });
        btn_DKCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
                MyBounce interpolator = new MyBounce(0.2,20);
                animation.setInterpolator(interpolator);
                btn_DKCustomer.startAnimation(animation);

                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ViewCustomer.class);
                        startActivity(intent);
                    }
                },900);
            }
        });
        btn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,B1TaoPhieuDK.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
//            //Khoi tao lai Activity main
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//
//            // Tao su kien ket thuc app
//            Intent startMain = new Intent(Intent.ACTION_MAIN);
//            startMain.addCategory(Intent.CATEGORY_HOME);
//            startActivity(startMain);
//            finish();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(MainActivity.this, "Nhấn back 2 lần để thoát",Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
