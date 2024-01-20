package com.yunusemre.kennyapp;

import static com.yunusemre.kennyapp.R.id.textView4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    int puan;
    TextView textView3;
    TextView textView4;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView imageView17;
    ImageView imageView18;
    ImageView imageView19;
    ImageView imageView20;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);

        puan = 0;




        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);

        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14,
                imageView15, imageView16, imageView17, imageView18, imageView19, imageView20};
        marioClose();

        Intent intent=getIntent();
        int oyunPuanı=intent.getIntExtra("oyunPuanı",puan);

        new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView3.setText("kalan: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);//dizi içindeki tüm öğeleri başlangıçta saklaması için!!
                }
                Toast.makeText(MainActivity2.this, "Oyun Bitti!", Toast.LENGTH_SHORT).show();
                textView3.setText("Süre Bitti!");
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity2.this); //burda  getApplication yemiyor,,
                alert.setTitle("Oyun Bitti!");
                alert.setMessage("en yüksek skor: "+oyunPuanı);
                //alert.setMessage("");//en yüksek skoru yazdır,,
                alert.setPositiveButton("yeniden", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        finish();
                        startActivity(intent);
                        Toast.makeText(MainActivity2.this, "Oyun yeniden başlıyor...", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("Oyunu Bitir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity2.this, "Oyundan sonlandırılıyor", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                alert.show();
            }
        }.start();
                                                //Sonunda yaptık helalalllll!!!!!!
                                                //aktiviteler arası bilgi ve geçiş ve oyun akışı iyi oldu
                                   // sadece bilgi mesajlarının güncelle ve en yüksek skor değeri için if bloğunu kullan


    }

    public void marioClose() {//karakter görünmesin,,

       handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView:imageArray){
                    imageView.setVisibility(View.INVISIBLE);//bu kod ile dizi içindeki karakterler ilk etapda görünmeyecek.
                }
                Random random=new Random();
                int i=random.nextInt(20);
                imageArray[i].setVisibility(View.VISIBLE); //dizideki elemanları karışık göster..

                handler.postDelayed(this,400); //ne aralıkta göstersin--->4salise,,
            }
        };
        handler.post(runnable);//handler i çalıştır..
    }


    public void marioImage(View view) { //mario üzerine basınca skoru artırsın..
        puan+=10;
        textView4.setText("Skor: " + puan);

    }
}