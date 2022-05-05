package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;
import android.content.Intent;




public class MainActivity5 extends AppCompatActivity {

    ImageView curView = null; //預設蓋牌
    private int countPair = 0; //答對總數預設0
    final int[] drawable = new int[]{R.drawable.sample_0,R.drawable.sample_1,
            R.drawable.sample_2,R.drawable.sample_3,R.drawable.sample_4,R.drawable.sample_5,
            R.drawable.sample_6,R.drawable.sample_7};

    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};


    int currentPos = -1; //預設-1是沒選任何東西

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);



        GridView gridView = (GridView)findViewById(R.id.gridView);
        ImageAdapter imageAdapter =new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (currentPos < 0) { //當currentPos < 0 就會開始看是否選對 選錯直接蓋牌 選對則亮牌

                            currentPos = position;      //比較是否一致
                            curView = (ImageView) view; //秀出當前的牌
                            ((ImageView) view).setImageResource(drawable[pos[position]]);//一致 2張秀出
                        }
                        else {  //當currentPos > 0 條件
                            if (currentPos == position) { //如果第一張 與 第二張 一致
                                ((ImageView) view).setImageResource(R.drawable.hidden); //2張秀出
                            } else if (pos[currentPos] != pos[position]) { //不一致
                                curView.setImageResource(R.drawable.hidden); //蓋牌

                            } else {
                                ((ImageView) view).setImageResource(drawable[pos[position]]);
                                countPair++;

                                if (countPair == 8) {

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            final Intent mainIntent = new Intent(MainActivity5.this, MainActivity2.class);
                                            startActivity(mainIntent);
                                            finish();        // 當跳到 MainActivity2 時，讓 MainActivity5 這隻程式結束。
                                            // 這樣設計可以方便使用者。
                                        }
                                    }, 2000);//2s
                                }
                            }

                            currentPos = -1;
                        }

                    }
        });
    }
}