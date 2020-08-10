package com.example.projectgroup2.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projectgroup2.R;

public class LoadingScreen extends AppCompatActivity {
    ImageView imgLoading;
    Button btnLoadScreen;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        setcontrol();
        setEvent();
        imgLoading.setBackgroundResource(R.drawable.animation_loadinng);
        if(imgLoading == null) throw new AssertionError();
        anim = (AnimationDrawable) imgLoading.getBackground();
        anim.start();
    }

    private void setEvent() {


        btnLoadScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(LoadingScreen.this,R.anim.bounce);
                MyBounce interpolator = new MyBounce(0.2,20);
                animation.setInterpolator(interpolator);
                btnLoadScreen.startAnimation(animation);

                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoadingScreen.this,MainActivity.class);
                        startActivity(intent);
                    }
                },1700);


            }
        });
    }

    private void setcontrol() {
        imgLoading = (ImageView) findViewById(R.id.imgViewLoading);
        btnLoadScreen = (Button) findViewById(R.id.btnLoadScreen);
    }
}