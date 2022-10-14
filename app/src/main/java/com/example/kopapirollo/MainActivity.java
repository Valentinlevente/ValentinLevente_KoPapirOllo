package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView teValasztasod_img;
    private ImageView gepValasztasa_img;
    private TextView eredmeny_txt;
    private ImageView ko_btn;
    private ImageView papir_btn;
    private ImageView ollo_btn;
    private int emberElet = 3;
    private int gepElet = 3;
    private int emberValasztasa;
    private int gepValasztasa;
    private AlertDialog.Builder builder;
    private TextView dontetlen_txt;
    private ImageView gepElet1_img;
    private ImageView gepElet2_img;
    private ImageView gepElet3_img;
    private ImageView emberElet1_img;
    private ImageView emberElet2_img;
    private ImageView emberElet3_img;
    private ImageView[] gepEletTomb_img;
    private ImageView[] emberEletTomb_img;
    private int dontetlenekSzama = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addListener();

    }

    private void init(){

        teValasztasod_img = findViewById(R.id.teValasztasod_img);
        gepValasztasa_img = findViewById(R.id.gepValasztasa_img);
        ko_btn = findViewById(R.id.ko_btn);
        papir_btn = findViewById(R.id.papir_btn);
        ollo_btn = findViewById(R.id.ollo_btn);
        builder = new AlertDialog.Builder(MainActivity.this);
        dontetlen_txt = findViewById(R.id.dontetlen_txt);
        gepElet1_img = findViewById(R.id.gepElet1_img);
        gepElet2_img = findViewById(R.id.gepElet2_img);
        gepElet3_img = findViewById(R.id.gepElet3_img);
        emberElet1_img = findViewById(R.id.emberElet1_img);
        emberElet2_img = findViewById(R.id.emberElet2_img);
        emberElet3_img = findViewById(R.id.emberElet3_img);
        gepEletTomb_img = new ImageView[] {gepElet1_img, gepElet2_img, gepElet3_img};
        emberEletTomb_img = new ImageView[] {emberElet1_img, emberElet2_img, emberElet3_img};

    }
    private void addListener(){
        ko_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberValasztasa = 0;
                teValasztasod_img.setImageResource(R.drawable.rock);
                //Kő = 0
                nyertes(emberValasztasa);
            }
        });
        papir_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberValasztasa = 1;
                teValasztasod_img.setImageResource(R.drawable.paper);
                //Papír = 1
                nyertes(emberValasztasa);
            }
        });
        ollo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emberValasztasa = 2;
                teValasztasod_img.setImageResource(R.drawable.scissors);
                //Olló = 2
                nyertes(emberValasztasa);
            }
        });

    }

    private void nyertes(int valasztas){
        Random rnd = new Random();
        gepValasztasa = rnd.nextInt(3);

        if(gepValasztasa == 0){
            gepValasztasa_img.setImageResource(R.drawable.rock);
        }
        else if(gepValasztasa == 1){
            gepValasztasa_img.setImageResource(R.drawable.paper);
        }
        else if(gepValasztasa == 2){
            gepValasztasa_img.setImageResource(R.drawable.scissors);
        }

        boolean emberNyerte = true;
        boolean dontetlen = true;

        if (valasztas == 0){
            if(gepValasztasa == 1){
                emberNyerte = false;
                dontetlen = false;
            }
            else if(gepValasztasa == 2){
                emberNyerte = true;
                dontetlen = false;
            }
        }
        if (valasztas == 1){
            if(gepValasztasa == 0){
                emberNyerte = true;
                dontetlen = false;
            }
            else if(gepValasztasa == 2){
                emberNyerte = false;
                dontetlen = false;
            }
        }
        if (valasztas == 2){
            if(gepValasztasa == 0){
                emberNyerte = false;
                dontetlen = false;
            }
            else if(gepValasztasa == 1){
                emberNyerte = true;
                dontetlen = false;
            }
        }

        if(dontetlen){
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();
            dontetlenekSzama++;
            dontetlen_txt.setText("Döntetlenek száma: " + String.valueOf(dontetlenekSzama));
        }
        else if(emberNyerte){
            gepElet--;
            Toast.makeText(MainActivity.this, "Nyertél", Toast.LENGTH_SHORT).show();
            gepEletTomb_img[gepElet].setImageResource(R.drawable.heart1);
        }else{
            emberElet--;
            Toast.makeText(MainActivity.this, "Vesztettél", Toast.LENGTH_SHORT).show();
            emberEletTomb_img[emberElet].setImageResource(R.drawable.heart1);
        }


        if(gepElet == 0 || emberElet == 0){
            if(gepElet > emberElet){

                builder.setTitle("Vesztettél!");

            }else{
                builder.setTitle("Nyertél!");
            }
            builder.setMessage("Szeretnél új játékot játszani?");
            builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    emberElet = 3;
                    gepElet = 3;
                    dontetlenekSzama = 0;
                    gepValasztasa_img.setImageResource(R.drawable.rock);
                    teValasztasod_img.setImageResource(R.drawable.rock);
                    for (int i = 0; i < gepEletTomb_img.length; i++){
                        gepEletTomb_img[i].setImageResource(R.drawable.heart2);
                    }
                    for (int i = 0; i < emberEletTomb_img.length; i++){
                        emberEletTomb_img[i].setImageResource(R.drawable.heart2);
                    }
                    dontetlen_txt.setText("Döntetlenek száma: " + String.valueOf(dontetlenekSzama));
                }
            });
            builder.setCancelable(false);
            builder.create().show();
        }

    }
}