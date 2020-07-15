package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main6Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        String correctAnswers = getIntent().getStringExtra("correct");
        String totalQuestion = getIntent().getStringExtra("total");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("results");

        TextView t1 = (TextView)findViewById(R.id.textView6);
        t1.setText("Total questions is " + totalQuestion);
        TextView t2 = (TextView)findViewById(R.id.textView7);
        t2.setText("Correct answers is " + correctAnswers);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("results");
        Result result = new Result(correctAnswers);
        String userId = user.getUid();
        database.child(userId).setValue(result);
    }

}
