package com.example.sakanotakeru.myapplication;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


/**
 * Created by sakano on 2016/10/26.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView[] imageViews = new ImageView[9];
    private static Bitmap[] resouces /*= {R.mipmap.puzzle1,R.mipmap.puzzle2,R.mipmap.puzzle3,
            R.mipmap.puzzle4,R.mipmap.puzzle5,R.mipmap.puzzle6,
            R.mipmap.puzzle7,R.mipmap.puzzle8,R.mipmap.puzzle9, R.mipmap.puzzle00}*/;
    private  static int[] mBox = {0,1,2,3,4,5,6,7,9};
    private boolean moveFlag = false;
    Button endButton;
    Button backButton;
    Button suffleButton;
    private long startTime;
    private long stopTime;
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
        Resources resources = getResources();
        Bitmap image = BitmapFactory.decodeResource( resources, R.mipmap.puzzle2 );
        Bitmap image2 = BitmapFactory.decodeResource( resources, R.mipmap.puzzle00 );
        //- 縦
        int imageHeight = image.getHeight();
        //- 横
        int imageWidth = image.getWidth();
        //- 縦
        int imageHeight2 = image2.getHeight();
        //- 横
        int imageWidth2 = image2.getWidth();
        int bestSize = 390;
        //- 画像が想定サイズより大きいか、小さい場合想定サイズに伸縮
        if( imageHeight < bestSize || imageHeight > bestSize) {
            image = Bitmap.createScaledBitmap( image, bestSize, bestSize, false );
        }
        if( imageHeight2 < bestSize || imageHeight2 > bestSize) {
            image2 = Bitmap.createScaledBitmap( image2, bestSize, bestSize, false );
        }
        //- 分割する一つ一つの画像サイズを取得
        int rowSize = bestSize / 3;
        //- 全画像数
        int totalImageNum = 3 * 3;
        //- X座標
        int x = 0;
        //- Y座標
        int y = 0;
        //- 分割イメージのリスト
        resouces = new Bitmap[totalImageNum + 1];

        for( int i = 0; i < totalImageNum; i++ ) {
            //- 3分割毎にx座標を0に戻す
            if ( i > 0 && i%3 == 0 ) {
                x = 0;
                y += rowSize;
                //- 初回以外はx座標を1サイズ分足していく
            } else if ( i > 0 ) {
                x += rowSize;
            }
            resouces[i] = Bitmap.createBitmap(image, x, y, rowSize, rowSize );
            resouces[9] = Bitmap.createBitmap(image2, x, y, rowSize, rowSize);
        }

        for (int i = 0; i < 9; i++) {
            imageViews[i].setOnClickListener(this);
            imageViews[i].setImageBitmap(resouces[i]);
        }
        imageViews[8].setImageBitmap(resouces[9]);
        suffle();
        startTime = System.currentTimeMillis();
    }


    @Override
    public void onClick(View v) {
        moveFlag = false;
        if (v == endButton){
            finish();
        }else if(v == backButton){
            for (int i = 0; i < 9; i++) {
                imageViews[i].setImageBitmap(resouces[i]);
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
            imageViews[index].setImageBitmap(resouces[mBox[index]]);
            imageViews[index-3].setImageBitmap(resouces[mBox[index-3]]);
            checkComplete();
        }
    }
    public void moveDown(int index) {
        if(mBox[index+3] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index+3];
            mBox[index +3] = s;
            imageViews[index].setImageBitmap(resouces[mBox[index]]);
            imageViews[index+3].setImageBitmap(resouces[mBox[index+3]]);
            checkComplete();
        }
    }
    public void moveRight(int index) {
        if(mBox[index+1] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index+1];
            mBox[index +1] = s;
            imageViews[index].setImageBitmap(resouces[mBox[index]]);
            imageViews[index+1].setImageBitmap(resouces[mBox[index+1]]);
            checkComplete();
        }
    }
    public void moveLeft(int index) {
        if(mBox[index-1] == 9){
            moveFlag = true;
            int s;
            s = mBox[index];
            mBox[index] = mBox[index-1];
            mBox[index -1] = s;
            imageViews[index].setImageBitmap(resouces[mBox[index]]);
            imageViews[index-1].setImageBitmap(resouces[mBox[index-1]]);
            checkComplete();
        }
    }

    public void checkComplete() {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            if (mBox[i] == i){
                count++;
            }
        }
        if (count == 8){
            stopTime = System.currentTimeMillis();
            long time = stopTime - startTime;
            int second = (int) (time/1000);
            new AlertDialog.Builder(this)
                    .setTitle("おめでとう！！")
                    .setMessage(second+"秒")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

}
