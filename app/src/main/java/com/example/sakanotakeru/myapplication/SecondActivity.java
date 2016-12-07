package com.example.sakanotakeru.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
            R.mipmap.puzzle7,R.mipmap.puzzle8,R.mipmap.puzzle9, R.mipmap.puzzle00};
    private  static int[] mBox = {0,1,2,3,4,5,6,7,9};
    private static int count = 0;
    private boolean moveFlag = false;
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
            imageViews[i].setImageResource(resouces[i]);
        }
        imageViews[8].setImageResource(resouces[9]);
        suffle();
    }


    @Override
    public void onClick(View v) {
        moveFlag = false;
        if (v == endButton){
            finish();
        }else if(v == backButton){
            for (int i = 0; i < 9; i++) {
                imageViews[i].setImageResource(resouces[i]);
            }
        }else if(v == suffleButton){
            suffle();
        } else if(v == imageViews[0]){
            move(0);
        }else if(v == imageViews[1]){
            move(1);
        }else if(v == imageViews[2]){
            move(2);
        }else if(v == imageViews[3]){
            move(3);
        }else if(v == imageViews[4]){
            move(4);
        }else if(v == imageViews[5]){
            move(5);
        }else if(v == imageViews[6]){
            move(6);
        }else if(v == imageViews[7]){
            move(7);
        }else if(v == imageViews[8]){
            move(8);
        }
    }

    public void suffle() {
        Random ran = new Random();
        for (int i = 0; i < 500 ;i++) {
            int random = ran.nextInt(9);
            moveFlag = false;
            move(random);
        }
    }

    public void move(int ran) {
        if(ran-1 >= 0 && ran != 3 && ran != 6 && !moveFlag){
            moveLeft(ran);
        }
        if (ran+1 <= 8 && ran != 2&&ran!=5 && !moveFlag) {
            moveRight(ran);
        }
        if (ran + 3 <= 8 && !moveFlag){
            moveDown(ran);
        }
        if (ran - 3 >= 0 && !moveFlag){
            moveUp(ran);
        }
    }
    public void moveUp(int index) {
        if(mBox[index-3] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index-3];
            mBox[index -3] = s;
            imageViews[index].setImageResource(resouces[mBox[index]]);
            imageViews[index-3].setImageResource(resouces[mBox[index-3]]);
        }
    }
    public void moveDown(int index) {
        if(mBox[index+3] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index+3];
            mBox[index +3] = s;
            imageViews[index].setImageResource(resouces[mBox[index]]);
            imageViews[index+3].setImageResource(resouces[mBox[index+3]]);
        }
    }
    public void moveRight(int index) {
        if(mBox[index+1] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index+1];
            mBox[index +1] = s;
            imageViews[index].setImageResource(resouces[mBox[index]]);
            imageViews[index+1].setImageResource(resouces[mBox[index+1]]);
        }
    }
    public void moveLeft(int index) {
        if(mBox[index-1] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index-1];
            mBox[index -1] = s;
            imageViews[index].setImageResource(resouces[mBox[index]]);
            imageViews[index-1].setImageResource(resouces[mBox[index-1]]);
        }
    }

}
