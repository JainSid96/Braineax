package com.example.siddhantjain.braineax;

import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton ,b1 , b2 , b3 , b4 , playAgain;
    TextView sumTextView , resultTextView , pointsTextView , timeTextView;
    GridLayout gridLayout;
    RelativeLayout gameLayout ;
    ImageView img;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int local , score = 0 ,ques = 0;

    public void playAgain (View view){
        score = 0;
        ques = 0 ;
        genQues();
        timeTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        pointsTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100 , 1000){
            @Override
            public void onTick(long l) {
                timeTextView.setText((int) l/1000 + "s");
            }
            @Override
            public void onFinish() {
                timeTextView.setText("0s");
                pointsTextView.setVisibility(View.INVISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                resultTextView.setText("SCORE :" + score);
            }
        }.start();
    }

    public void genQues(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b) + "= ?");
        local = rand.nextInt(4);
        answers.clear();
        int incorrect;
        for (int i = 0; i < 4 ; i++){
            if (i == local){
                answers.add(a+b);
            }else {
                incorrect = rand.nextInt(41);
                while(incorrect == a+b){
                    incorrect = rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        b1.setText(Integer.toString(answers.get(0)));
        b2.setText(Integer.toString(answers.get(1)));
        b3.setText(Integer.toString(answers.get(2)));
        b4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(local))){
            score++;
            resultTextView.setText("CORRECT !");
        }else {
            resultTextView.setText("INCORRECT !");
        }
        ques++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(ques));
        genQues();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timeTextView = (TextView)findViewById(R.id.timerTextView);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        playAgain = (Button)findViewById(R.id.playAgain);
        img = (ImageView)findViewById(R.id.img);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        playAgain(findViewById(R.id.playAgain));
    }
}
