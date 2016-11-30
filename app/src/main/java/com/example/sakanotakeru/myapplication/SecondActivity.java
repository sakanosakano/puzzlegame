package com.example.sakanotakeru.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sakano on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView[] imageViews = new ImageView[9];
    private static int[] resouces = {R.mipmap.puzzle1,R.mipmap.puzzle2,R.mipmap.puzzle3,
            R.mipmap.puzzle4,R.mipmap.puzzle5,R.mipmap.puzzle6,
            R.mipmap.puzzle7,R.mipmap.puzzle8,R.mipmap.puzzle9};
    private static int count = 0;
    Button endButton;
    Button backButton;
    Button suffleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imageViews = new ImageView[]{(ImageView) findViewById(R.id.panel0), (ImageView) findViewById(R.id.panel1),
                (ImageView) findViewById(R.id.panel2), (ImageView) findViewById(R.id.panel3), (ImageView) findViewById(R.id.panel4),
                (ImageView) findViewById(R.id.panel5), (ImageView) findViewById(R.id.panel6), (ImageView) findViewById(R.id.panel7),
                (ImageView) findViewById(R.id.panel8)};
        endButton = (Button) findViewById(R.id.endButton);
        backButton = (Button) findViewById(R.id.stopButton);
        suffleButton = (Button) findViewById(R.id.suffuleButton);
        endButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        suffleButton.setOnClickListener(this);
        for (int i = 0; i < 9; i++) {
            imageViews[i].setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == endButton){
            finish();
        }else if(v == backButton){
        }else if(v == suffleButton){
        }
    }

    public void suffle() {
        Random ran;
        ran = new Random();
        int random = ran.nextInt(9);
        for (int i = 0; i < 100 ;i++) {

        }
    }


}
