package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Score extends AppCompatActivity {
    TextView result,marks;
    RatingBar rb;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);
        marks = (TextView)findViewById(R.id.score);
        result = (TextView)findViewById(R.id.result);
        rb = (RatingBar) findViewById(R.id.ratingBar);
        home = (ImageView) findViewById(R.id.home);

        String score = getIntent().getExtras().getString("myscore");
        marks.setText(score);
        Float sc = Float.valueOf(score);

        ObjectAnimator anim = ObjectAnimator.ofFloat(rb,"rating",sc);
        anim.setDuration(2000);
        anim.start();
        rb.setRating(sc);

        switch (score)
        {
            case "1" : rb.setRating(1.0f);
                result.setText("Poor");
                out();
                break;

            case "2" : rb.setRating(2.0f);
                result.setText("Fair");
                out();
                break;

            case "3" : rb.setRating(3.0f);
                result.setText("Good");
                out();
                break;

            case "4" : rb.setRating(4.0f);
                result.setText("Very Good");
                out();
                break;

            case "5" : rb.setRating(5.0f);
                result.setText("Excellent");
                out();
                break;

        }


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Score.this, Bolum1.class);
                startActivity(intent);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupWindow();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Score.this, Bolum1.class);
        startActivity(intent);
    }

    private PopupWindow pw;
    private void initiatePopupWindow() {
        try {
            LayoutInflater inflater = (LayoutInflater) Score.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pw = new PopupWindow(layout, 1400, 800, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            cancelButton.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };

    public void out(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(Score.this, Bolum1.class);
                startActivity(intent);
            }
        },3000);
    }

}
