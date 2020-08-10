package com.example.projectgroup2.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectgroup2.Adapter.AdapterTour;
import com.example.projectgroup2.Database.DBTour;
import com.example.projectgroup2.R;

public class ViewTour extends AppCompatActivity {
    ListView lv_Tour;
    ImageButton btn_createTour;
    AdapterTour adapterTour;
    DBTour dbTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour);
        dbTour = new DBTour(ViewTour.this);
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
                AdapterTour adapterTour = new AdapterTour(ViewTour.this,R.layout.listview_tour,dbTour.getAllTourSearch(newText));
                lv_Tour.setAdapter(adapterTour);
                adapterTour.notifyDataSetChanged();
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
                Intent intent = new Intent(ViewTour.this,MainActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getApplication(),"ad",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setControl()
    {
        btn_createTour = findViewById(R.id.btn_createTour);
        lv_Tour = findViewById(R.id.lv_Tour);
    }
    private  void khoitao()
    {
        adapterTour = new AdapterTour(ViewTour.this,R.layout.listview_tour,dbTour.getAllTour());
        lv_Tour.setAdapter(adapterTour);
        adapterTour.notifyDataSetChanged();
    }
    private void handleEvent()
    {
        khoitao();
        btn_createTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),create_tour.class);
                startActivity(intent);
            }
        });
        lv_Tour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e( "onItemClick: ","sad" );
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ViewTour.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
