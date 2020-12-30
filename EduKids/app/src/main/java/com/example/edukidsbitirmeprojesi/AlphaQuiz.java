package com.example.edukidsbitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AlphaQuiz extends AppCompatActivity {
    TextView ques;
    ImageView a1,a2,a3,a4;
    private Cursor c;
    SQLiteDatabase db;
    int score,qno=0;
    String a, myscore;
    DbPuzzle dbPuzzle;
    ProgressBar progressBar;
    int geri=-10;
    private static final String SELECT_SQL = "SELECT * FROM puzzles";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alphaquiz);

        ques = (TextView) findViewById(R.id.textView3);
        a1 = (ImageView) findViewById(R.id.imageView3);
        a2 = (ImageView) findViewById(R.id.imageView4);
        a3 = (ImageView) findViewById(R.id.imageView5);
        a4 = (ImageView) findViewById(R.id.imageView6);

        progressBar=(ProgressBar)findViewById(R.id.progressBar3);
        progressBar.setProgress(100);
        sayac();
        // dbPuzzle = new DbPuzzle(this);
        // db = dbPuzzle.getWritableDatabase();
        createDatabase();
        openDatabase();
        insertIntoDB();
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a1"))
                {
                    score++;
                    progressBar.setProgress(100);
                }
                c.moveToNext();
                showRecords();

            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a2"))
                {
                    score++;
                    progressBar.setProgress(100);
                }
                c.moveToNext();
                showRecords();
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a3"))
                {
                    score++;
                    progressBar.setProgress(100);
                }
                c.moveToNext();
                showRecords();
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a.equals("a4"))
                {
                    score++;
                    progressBar.setProgress(100);
                }
                c.moveToNext();
                showRecords();
            }
        });
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
    }
    protected void createDatabase(){
        db=openOrCreateDatabase("KLWDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS puzzles(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ques VARCHAR, a1 VARCHAR, a2 VARCHAR, a3 VARCHAR, a4 VARCHAR, ans VARCHAR);");
    }
    protected void showRecords() {
        if(qno == 5)
        {
            myscore = String.valueOf(score);
            Intent intent = new Intent(AlphaQuiz.this, Score.class);
            intent.putExtra("myscore",myscore);
            startActivity(intent);
        }
        else {
            String text = c.getString(1);
            String img1 = c.getString(2);
            String img2 = c.getString(3);
            String img3 = c.getString(4);
            String img4 = c.getString(5);
            a = c.getString(6);
            ques.setText(text);
            int id1 = getResources().getIdentifier(img1, "drawable", getPackageName());
            a1.setImageResource(id1);
            int id2 = getResources().getIdentifier(img2, "drawable", getPackageName());
            a2.setImageResource(id2);
            int id3 = getResources().getIdentifier(img3, "drawable", getPackageName());
            a3.setImageResource(id3);
            int id4 = getResources().getIdentifier(img4, "drawable", getPackageName());
            a4.setImageResource(id4);
            qno++;
        }

    }
    protected void insertIntoDB() {
        String query1 = "INSERT INTO puzzles(ques,a1,a2,a3,a4,ans) VALUES('O','a','b','o','d','a3');";
        String query2 = "INSERT INTO puzzles(ques,a1,a2,a3,a4,ans) VALUES('I','i','o','e','t','a1');";
        String query3 = "INSERT INTO puzzles(ques,a1,a2,a3,a4,ans) VALUES('E','d','a','c','e','a4');";
        String query4 = "INSERT INTO puzzles(ques,a1,a2,a3,a4,ans) VALUES('H','d','o','h','b','a3');";
        String query5 = "INSERT INTO puzzles(ques,a1,a2,a3,a4,ans) VALUES('Y','a','y','b','c','a2');";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }
    public  void sayac(){
        final Timer timer=new Timer();
        final Handler handler=new Handler();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progressBar.getProgress()-10,true);
                        if(progressBar.getProgress()==0){
                            showDialog(AlphaQuiz.this);
                            timer.cancel();
                        }
                    }
                });
            }
        },0,1500);
    }
    public void showDialog(final Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alertdialog2);
        Button menuBtn=(Button)dialog.findViewById(R.id.menu);
        Button okey = (Button) dialog.findViewById(R.id.d1_btn1);
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent quiz=new Intent(AlphaQuiz.this, alphaquizgiris.class);
            //    quiz.putExtra("numberlvl",Integer.toString(numberlvl));
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);

            }
        });
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent quiz=new Intent(AlphaQuiz.this, AlphaQuiz.class);
                //    quiz.putExtra("numberlvl",Integer.toString(numberlvl));
                startActivity(quiz);
                overridePendingTransition(R.anim.smalltobig,0);
            }
        });
        dialog.show();
    }
}
