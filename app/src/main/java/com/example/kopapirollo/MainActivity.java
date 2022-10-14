package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView teValasztasod_img;
    private ImageView gepValasztasa_img;
    private TextView eredmeny_txt;
    private Button ko_btn;
    private Button papir_btn;
    private Button ollo_btn;
    private int ember = 0;
    private int gep = 0;
    private int emberValasztasa;
    private int gepValasztasa;
    private AlertDialog.Builder builder;

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
        eredmeny_txt = findViewById(R.id.eredmeny_txt);
        ko_btn = findViewById(R.id.ko_btn);
        papir_btn = findViewById(R.id.papir_btn);
        ollo_btn = findViewById(R.id.ollo_btn);
        builder = new AlertDialog.Builder(MainActivity.this);

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
        }
        else if(emberNyerte){
            ember++;
            Toast.makeText(MainActivity.this, "Nyertél", Toast.LENGTH_SHORT).show();
        }else{
            gep++;
            Toast.makeText(MainActivity.this, "Vesztettél", Toast.LENGTH_SHORT).show();
        }

        eredmeny_txt.setText("Eredmény: Ember:" + String.valueOf(ember) + " Computer:" + String.valueOf(gep));

        if(gep >= 3 || ember >= 3){
            if(gep > ember){

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
                public void onClick(DialogInterface dialogInterface, int i) {
                    ember = 0;
                    gep = 0;
                    eredmeny_txt.setText("Eredmény: Ember:" + String.valueOf(ember) + " Computer:" + String.valueOf(gep));
                    gepValasztasa_img.setImageResource(R.drawable.rock);
                    teValasztasod_img.setImageResource(R.drawable.rock);
                }
            });
            builder.create().show();
        }

    }
}