package com.example.sakanotakeru.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by sakano on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity {
    private Timer timer;
    private CountUpTimerTask timerTask = null;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        timerStart();
    }
    public static void timerStart() {
        // Timer インスタンスを生成
        timer = new Timer();

        // TimerTask インスタンスを生成
        timerTask = new CountUpTimerTask();

        // スケジュールを設定 100msec
        // public void schedule (TimerTask task, long delay, long period)
        timer.schedule(timerTask, 0, 100);
    }
    class CountUpTimerTask extends TimerTask {
        @Override
        public void run() {
            // handlerを使って処理をキューイングする
            handler.post(new Runnable() {
                public void run() {

                }
            });
        }
    }
}
