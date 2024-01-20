package com.yunusemre.kennyapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;
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

public class MainActivity extends AppCompatActivity {
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

    TextView textView;
    TextView textView1;

    int puan;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView=findViewById(R.id.textView1);
        textView1=findViewById(R.id.textView2);
        puan=0;
            sharedPreferences =this.getSharedPreferences(" com.yunusemre.kennyapp", Context.MODE_PRIVATE);
           int oyunPuanı= sharedPreferences.getInt("oyunPuanı",0);//ilk değeri sıfır veriyoruz ve kullanmak için değişkene atamalıyız..
            textView1.setText("skor: "+ oyunPuanı);

        new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Kalan: "+ millisUntilFinished/1000);
                if (millisUntilFinished/1000<5){
                    Toast.makeText(MainActivity.this, "Süre azalıyor...", Toast.LENGTH_SHORT).show();
                } else if (millisUntilFinished/1000<=1) {
                    Toast.makeText(MainActivity.this,"Süre bitti",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish() {
               handler.removeCallbacks(runnable);
                for (ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);//dizi içindeki tüm öğeleri başlangıçta saklaması için!!
                }
                Toast.makeText(MainActivity.this, "Oyun Bitti!", Toast.LENGTH_SHORT).show();
                textView.setText("Süre Bitti!");
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Oyun Bitti!");
                alert.setMessage("Sonraki Bölüme Geçmek İster misin?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),MainActivity2.class); //Sonraki sayfa!!
                        intent.putExtra("oyunPuanı",puan);


                        startActivity(intent);//başlat!!!!

                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyunu sonlandır", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
                    alert.show(); //alerti başlat!!
            }
        }.start();//sayacı başlat!!!



        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);
        imageView13=findViewById(R.id.imageView13);
        imageView14=findViewById(R.id.imageView14);
        imageView15=findViewById(R.id.imageView15);
        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15};
        imageClose();//ilk başta kahraman gözükmesin!!!!!

    }

    public void imageClose(){ //bu method karakterleri başlangıçta gizledi ve kaybolup görünmesini sağladı,,
            handler=new Handler(); //karakteri bi kaybedip bi göstermek için
            runnable=new Runnable() { //devamlı olacak bir şeyin çalışması. handler buna kısıtlama getirir.
                @Override
                public void run() {
                    for (ImageView image:imageArray){
                        image.setVisibility(View.INVISIBLE);//dizi içindeki tüm öğeleri başlangıçta saklaması için!!
                    }
                    Random random=new Random();
                    int i=random.nextInt(15); //dizinin içinde 15 adet eleman olduğu için,,
                    imageArray[i].setVisibility(View.VISIBLE);// 0dan 14e kadar herhangi bir elemanı göster demek.

                    handler.postDelayed(this,600);//this yani bu method demek ve 600 milisaniyede bir çalıştır anlamı kattık,,
                }
            };

                handler.post(runnable);//çalıştır!



    }

    public void imagePoint(View view){
        puan+=5;
        textView1.setText("skor: "+puan);

        sharedPreferences.edit().putInt("oyunPuanı",puan).apply();//apply koymazsak kaydedemeyiz!!!!
    }

}