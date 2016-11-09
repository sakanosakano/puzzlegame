package com.example.sakanotakeru.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sakano on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private Timer timer;
    private CountUpTimerTask timerTask = null;
    private Handler handler = new Handler();
    private ImageView[] imageViews = new ImageView[9];
    private static int[] resouces = {R.mipmap.droid1,R.mipmap.droid2,R.mipmap.droid3};
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
        timerStart();
    }
    public void timerStart() {
        // Timer インスタンスを生成
        timer = new Timer();

        // TimerTask インスタンスを生成
        timerTask = new CountUpTimerTask();

        // スケジュールを設定 100msec
        // public void schedule (TimerTask task, long delay, long period)
        timer.schedule(timerTask, 0, 200);
    }

    @Override
    public void onClick(View v) {
        if (v == endButton){
            finish();
        }else if(v == backButton){
            timer.cancel();
        }else if(v == suffleButton){
            timerStart();
        }
    }

    class CountUpTimerTask extends TimerTask {
        @Override
        public void run() {
            // handlerを使って処理をキューイングする
            handler.post(new Runnable() {
                public void run() {
                    imageViews[0].setImageResource(resouces[count]);
                }
            });
            count++;
            if (count > 2){
                count = 0;
            }
        }
    }
}
