package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;

public class PuzzleAnimals extends AppCompatActivity {
    TextView p1,p2;
    ImageView iv_11,iv_12,iv_13,iv_14,iv_21,iv_22,iv_23,iv_24,iv_31,iv_32,iv_33,iv_34;
    Integer[]cardsArray={101,102,103,104,105,106,201,202,203,204,205,206};

    int image101,image102,image103,image104,image105,image106,
            image201,image202,image203,image204,image205,image206;

    int firstCard,secondCard;
    int clickedFirst,clickedSecond;
    int cardNumber=1;
    int turn=1;
    int playerPoints=0,cpuPoints=0;

    Button geri;
    ImageView badgestar;
    Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzleanimals);
        geri=(Button)findViewById(R.id.backk);
        p1=(TextView)findViewById(R.id.P1);
   //    p2=(TextView)findViewById(R.id.P2);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss=new Intent(PuzzleAnimals.this,Bolum1.class);
                startActivity(ss);
                finish();
            }
        });


        playerPoints=25;
        p1.setText(Integer.toString(playerPoints));


        iv_11=(ImageView)findViewById(R.id.iv11);
        iv_12=(ImageView)findViewById(R.id.iv12);
        iv_13=(ImageView)findViewById(R.id.iv13);
        iv_14=(ImageView)findViewById(R.id.iv14);
        iv_21=(ImageView)findViewById(R.id.iv21);
        iv_22=(ImageView)findViewById(R.id.iv22);
        iv_23=(ImageView)findViewById(R.id.iv23);
        iv_24=(ImageView)findViewById(R.id.iv24);
        iv_31=(ImageView)findViewById(R.id.iv31);
        iv_32=(ImageView)findViewById(R.id.iv32);
        iv_33=(ImageView)findViewById(R.id.iv33);
        iv_34=(ImageView)findViewById(R.id.iv34);
        badgestar=(ImageView)findViewById(R.id.badgestar);
        avatarGetir();

     /*   home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home=new Intent(getApplicationContext(),Bolum1.class);
                startActivity(home);
                finish();
            }
        });*/

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");


        Kartlar();


        Collections.shuffle(Arrays.asList(cardsArray));
       // p2.setTextColor(Color.GRAY);


      iv_11.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
               int theCard=Integer.parseInt((String)v.getTag());
              KartDizi(iv_11,theCard);
              String g11= String.valueOf(p1.getText());
              int c1=Integer.parseInt(g11)-1;
              p1.setText(Integer.toString(c1));
              if(c1==0){
                  showDialog(PuzzleAnimals.this);
              }


          }
      });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_12,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_13,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_14,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_21,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_22,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_23,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));

                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_24,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_31,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_32,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_33,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard=Integer.parseInt((String)v.getTag());
                KartDizi(iv_34,theCard);
                String g11= String.valueOf(p1.getText());
                int c1=Integer.parseInt(g11)-1;
                p1.setText(Integer.toString(c1));
                if(c1==0){
                    showDialog(PuzzleAnimals.this);
                }
            }
        });
    }

    private void KartDizi(ImageView iv,int card){
        if(cardsArray[card]==101){
            iv.setImageResource(image101);
        }else if(cardsArray[card]==102){
            iv.setImageResource(image102);
        }else if(cardsArray[card]==103){
            iv.setImageResource(image103);
        }else if(cardsArray[card]==104){
            iv.setImageResource(image104);
        }else if(cardsArray[card]==105){
            iv.setImageResource(image105);
        }else if(cardsArray[card]==106){
            iv.setImageResource(image106);
        }else if(cardsArray[card]==201){
            iv.setImageResource(image201);
        }else if(cardsArray[card]==202){
            iv.setImageResource(image202);
        }else if(cardsArray[card]==203){
            iv.setImageResource(image203);
        }else if(cardsArray[card]==204){
            iv.setImageResource(image204);
        }else if(cardsArray[card]==205){
            iv.setImageResource(image205);
        }else if(cardsArray[card]==206){
            iv.setImageResource(image206);
        }


        if(cardNumber==1){
            firstCard=cardsArray[card];
            if(firstCard>200)
            {
                firstCard=firstCard-100;
            }
            cardNumber=2;
            clickedFirst=card;



            iv.setEnabled(false);
        }
        else if(cardNumber==2)
        {
            secondCard=cardsArray[card];
            if(secondCard>200)
            {
                secondCard=secondCard-100;
            }
            cardNumber=1;
            clickedSecond=card;

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Kontrol();
                }
            },1000);

        }
    }
   public void Kontrol(){
        if(firstCard==secondCard)
        {
            if(clickedFirst==0){
                iv_11.setVisibility(View.INVISIBLE);

            }else if(clickedFirst==1){
                iv_12.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==2){
                iv_13.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==3){
                iv_14.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==4){
                iv_21.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==5){
                iv_22.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==6){
                iv_23.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==7){
                iv_24.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==8){
                iv_31.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==9){
                iv_32.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==10){
                iv_33.setVisibility(View.INVISIBLE);
            }
            else if(clickedFirst==11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            if(clickedSecond==0){
                iv_11.setVisibility(View.INVISIBLE);
            }else if(clickedSecond==1){
                iv_12.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==2){
                iv_13.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==3){
                iv_14.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==4){
                iv_21.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==5){
                iv_22.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==6){
                iv_23.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==7){
                iv_24.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==8){
                iv_31.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==9){
                iv_32.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==10){
                iv_33.setVisibility(View.INVISIBLE);
            }
            else if(clickedSecond==11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            if(turn==1){
               // p1.setText(Integer.toString(playerPoints-2));
            }
          /*  else  if(turn==2)
            {
                cpuPoints++;
                p2.setText(Integer.toString(cpuPoints));
            }*/
        }
        else {
            iv_11.setImageResource(R.drawable.block);
            iv_12.setImageResource(R.drawable.block);
            iv_13.setImageResource(R.drawable.block);
            iv_14.setImageResource(R.drawable.block);
            iv_21.setImageResource(R.drawable.block);
            iv_22.setImageResource(R.drawable.block);
            iv_23.setImageResource(R.drawable.block);
            iv_24.setImageResource(R.drawable.block);
            iv_31.setImageResource(R.drawable.block);
            iv_32.setImageResource(R.drawable.block);
            iv_33.setImageResource(R.drawable.block);
            iv_34.setImageResource(R.drawable.block);



         /* if(turn==1){
                turn=2;
                p1.setTextColor(Color.RED);
                p2.setTextColor(Color.BLACK);
            }
            else if(turn==2){
                turn=1;
                p2.setTextColor(Color.BLACK);
                p1.setTextColor(Color.RED);
            }*/
        }
        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_13.setEnabled(true);
        iv_14.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_23.setEnabled(true);
        iv_24.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);

        KontrolBitir();
    }
    private  void KontrolBitir(){

      if(iv_11.getVisibility()==View.INVISIBLE &&iv_12.getVisibility()==View.INVISIBLE
                &&iv_13.getVisibility()==View.INVISIBLE
                &&iv_14.getVisibility()==View.INVISIBLE
                &&iv_21.getVisibility()==View.INVISIBLE
                &&iv_22.getVisibility()==View.INVISIBLE
                &&iv_23.getVisibility()==View.INVISIBLE
                &&iv_24.getVisibility()==View.INVISIBLE
                &&iv_31.getVisibility()==View.INVISIBLE
                &&iv_32.getVisibility()==View.INVISIBLE
                &&iv_33.getVisibility()==View.INVISIBLE
                &&iv_34.getVisibility()==View.INVISIBLE){
         showDialog2(PuzzleAnimals.this);

        }
    }
    private void Kartlar() {
        image101=R.drawable.anim_bull;
        image102=R.drawable.anim_camel;
        image103=R.drawable.anim_cow;
        image104=R.drawable.anim_deer;
        image105=R.drawable.anim_elephant;
        image106=R.drawable.anim_horse;
        //------
        image201=R.drawable.anim_bull;
        image202=R.drawable.anim_camel;
        image203=R.drawable.anim_cow;
        image204=R.drawable.anim_deer;
        image205=R.drawable.anim_elephant;
        image206=R.drawable.anim_horse;

    }
    public void avatarGetir(){
            SharedPreferences preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
            ImageView avatarImg=(ImageView) findViewById(R.id.puzzleAnimalAvatar);
            String img_str=preferences.getString("userphoto", "");
            if (!img_str.equals("")){
                //decode string to image
                String base=img_str;
                byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
                avatarImg.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
        }
    }
    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setTitle("GAME OVER");
        dialog.setContentView(R.layout.failalert);
        Button menuBtn=(Button)dialog.findViewById(R.id.menu);
        Button okey = (Button) dialog.findViewById(R.id.d1_btn1);

        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz=new Intent(PuzzleAnimals.this, Bolum1.class);
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz1=new Intent(PuzzleAnimals.this,PuzzleAnimals.class);
                startActivity(quiz1);
                overridePendingTransition(R.anim.smalltobig, 0);
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent r=new Intent(PuzzleAnimals.this,Bolum1.class);
        startActivity(r);
        finish();
        overridePendingTransition(R.anim.smalltobig,0);
    }
    public void showDialog2(final Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alertdialog);
        Button menuBtn=(Button)dialog.findViewById(R.id.menu);
        Button okey = (Button) dialog.findViewById(R.id.d1_btn1);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent quiz=new Intent(PuzzleAnimals.this, Bolum1.class);
                //    quiz.putExtra("numberlvl",Integer.toString(numberlvl));
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);

            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz=new Intent(PuzzleAnimals.this, PuzzleAnimals.class);
                //    quiz.putExtra("numberlvl",Integer.toString(numberlvl));
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        dialog.show();
    }
}
