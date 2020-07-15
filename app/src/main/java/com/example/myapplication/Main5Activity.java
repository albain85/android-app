package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Edit screen
public class Main5Activity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public long s;
    private EditText question;
    private EditText answer;
    private EditText op1;
    private EditText op2;
    private EditText op3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("questions");

         question = findViewById(R.id.editText1);
         answer = findViewById(R.id.editText2);
         op1 = findViewById(R.id.editText3);
         op2 = findViewById(R.id.editText4);
         op3 = findViewById(R.id.editText5);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                s = dataSnapshot.getChildrenCount() + 1;
                dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addQuestion(View view) {

        String myQuestoin = question.getText().toString();
        String myAnswer = answer.getText().toString();
        String myOp1 = op1.getText().toString();
        String myOp2 = op2.getText().toString();
        String myOp3 = op3.getText().toString();

        Button b = (Button) findViewById(R.id.button9);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        b.startAnimation(a);

            if(TextUtils.isEmpty(myQuestoin) || TextUtils.isEmpty(myAnswer) || TextUtils.isEmpty(myOp1) || TextUtils.isEmpty(myOp2) || TextUtils.isEmpty(myOp3)) {
                Toast.makeText(Main5Activity.this , "One of the fields empty", Toast.LENGTH_SHORT).show();
            }
            else {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("questions");
                Questions question = new Questions(myQuestoin, myAnswer, myOp1, myOp2, myOp3);
                myRef.child(""+s).setValue(question);
                Toast.makeText(Main5Activity.this , "Question successfully added" , Toast.LENGTH_SHORT).show();

            }
        }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);
    }
    }

