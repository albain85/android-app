package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//Start game screen
public class Main3Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Integer numOfQuestion = 1;
    private Integer correctAnswers = 0;
    TextView questionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        database = FirebaseDatabase.getInstance();
        questionTextView = (TextView)findViewById(R.id.textView);
        showQuestion();
    }

    public void myAnswer1(View view) {
        Button b = (Button) findViewById(R.id.button2);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        b.startAnimation(a);
        final Button button2 = (Button) findViewById(R.id.button2);
        TextView buttonName = (TextView)findViewById(R.id.button2);
        final String str = buttonName.getText().toString();
        isRightAnswer(buttonName , button2);
    }

    public void myAnswer2(View view) {
        Button b = (Button) findViewById(R.id.button4);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        b.startAnimation(a);
        final Button button4 = (Button) findViewById(R.id.button4);
        TextView buttonName = (TextView)findViewById(R.id.button4);
        final String str = buttonName.getText().toString();
        isRightAnswer(buttonName , button4);
    }

    public void myAnswer3(View view) {
        Button b = (Button) findViewById(R.id.button5);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        b.startAnimation(a);
        final Button button5 = (Button) findViewById(R.id.button5);
        TextView buttonName = (TextView)findViewById(R.id.button5);
        final String str = buttonName.getText().toString();
        isRightAnswer(buttonName , button5);
    }

    public void myAnswer4(View view) {
        Button b = (Button) findViewById(R.id.button6);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        b.startAnimation(a);
        final Button button6 = (Button) findViewById(R.id.button6);
        TextView buttonName = (TextView)findViewById(R.id.button6);
        final String str = buttonName.getText().toString();
        isRightAnswer(buttonName , button6);
    }

    public void showQuestion(){
        myRef = database.getReference().child("questions");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (numOfQuestion <= dataSnapshot.getChildrenCount()) {
                    List<String> list = new ArrayList<String>();
                    list.add(dataSnapshot.child(numOfQuestion.toString()).child("answer").getValue().toString());
                    list.add(dataSnapshot.child(numOfQuestion.toString()).child("option1").getValue().toString());
                    list.add(dataSnapshot.child(numOfQuestion.toString()).child("option2").getValue().toString());
                    list.add(dataSnapshot.child(numOfQuestion.toString()).child("option3").getValue().toString());

                    String questionStr = dataSnapshot.child(numOfQuestion.toString()).child("question").getValue().toString();

                    list = getRandomElement(list , list.size());
                    questionTextView.setText(questionStr);
                    Button answerButton = (Button) findViewById(R.id.button2);
                    answerButton.setText(list.get(0));
                    Button op1Button = (Button) findViewById(R.id.button4);
                    op1Button.setText(list.get(1));
                    Button op2Button = (Button) findViewById(R.id.button5);
                    op2Button.setText(list.get(2));
                    Button op3Button = (Button) findViewById(R.id.button6);
                    op3Button.setText(list.get(3));

                }
                else
                {
                    Integer total = numOfQuestion - 1;
                    final Intent summeryScreen = new Intent(Main3Activity.this , Main6Activity.class);
                    summeryScreen.putExtra("correct", correctAnswers.toString());
                    summeryScreen.putExtra("total", total.toString());
                    startActivity(summeryScreen);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static List<String> getRandomElement(List<String> list,int totalItems) {
        Random rand = new Random();
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            newList.add(list.get(randomIndex));
            list.remove(randomIndex);
        }
        return newList;
    }

    public void isRightAnswer(final TextView bt ,final Button b){
        DatabaseReference tempRef;
        tempRef = database.getReference().child("questions").child(numOfQuestion.toString()).child("answer");
        final String btName = bt.getText().toString();
        final boolean[] temp = new boolean[1];
        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String str2 = dataSnapshot.getValue().toString();
                if(btName.equals(str2)) {
                    Drawable d = getResources().getDrawable(R.drawable.correct);
                    b.setBackgroundDrawable(d);
                    b.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Drawable d = getResources().getDrawable(R.drawable.gray_button);
                            b.setBackgroundDrawable(d);
                            numOfQuestion++;
                            correctAnswers++;
                            showQuestion();
                        }
                    }, 500);
                }
                else {
                    Drawable d = getResources().getDrawable(R.drawable.incorrect);
                    b.setBackgroundDrawable(d);
                    b.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Drawable d = getResources().getDrawable(R.drawable.gray_button);
                            b.setBackgroundDrawable(d);
                            numOfQuestion++;
                            showQuestion();
                        }
                    }, 500);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
