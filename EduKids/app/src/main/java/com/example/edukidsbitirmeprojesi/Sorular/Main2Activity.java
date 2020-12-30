package com.example.edukidsbitirmeprojesi.Sorular;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edukidsbitirmeprojesi.R;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    private int sayac = 0;
    private int maxSayac= 3;
    private String[] keys = {"F", "I", "G"};
    private String sonuc = "FIG";
    TextView textScreen, textTitle;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        anim = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        keys = oku(keys);
        for (String key : keys) {
            addView(((LinearLayout) findViewById(R.id.layoutParent)), key, ((EditText) findViewById(R.id.editText)));
        }
        maxSayac = 3;
    }
    private String[] oku(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    private void addView(LinearLayout viewParent, final String text, final EditText editText) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 30;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.purple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/FredokaOneRegular.ttf");
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);
        editText.setTypeface(typeface);
        textView.setTypeface(typeface);
        textView.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(sayac < maxSayac) {
                    if (sayac == 0)
                        editText.setText("");

                    editText.setText(editText.getText().toString() + text);
                    textView.startAnimation(anim);
                    textView.animate().alpha(0).setDuration(300);
                    sayac++;

                    if (sayac == maxSayac)
                        doValidate();
                }
            }
        });
        viewParent.addView(textView);
    }
    private void doValidate() {
        sayac= 0;
        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);
        if(editText.getText().toString().equals(sonuc)) {
//            Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(Main2Activity.this,Cevap2.class);
            startActivity(a);

            editText.setText("");
        } else {
            Toast.makeText(Main2Activity.this, "Wrong", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }

        keys = oku(keys);
        linearLayout.removeAllViews();
        for (String key : keys) {
            addView(linearLayout, key, editText);
        }
    }
    @Override
    public void onBackPressed() {

    }
}
