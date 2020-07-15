package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicMarkableReference;

//Pick screen, start game or edit
public class Main4Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

    }

    public void Start_game(View view) {
        Button b = (Button) findViewById(R.id.button7);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        b.startAnimation(a);
        Intent startGame = new Intent(this, Main3Activity.class);
        startActivity(startGame);
        overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_left);
    }

    public void Edit(View view) {
        Button b = (Button) findViewById(R.id.button8);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        b.startAnimation(a);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference tempRef;
        database = FirebaseDatabase.getInstance();
        final Intent edidScreen = new Intent(Main4Activity.this, Main5Activity.class);
        tempRef = database.getReference().child("users").child(user.getUid());
        tempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isAdmin = (boolean)dataSnapshot.child("admin").getValue();
                if (isAdmin) {
                    startActivity(edidScreen);
                    overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_left);
                }
                else
                    Toast.makeText(Main4Activity.this, "Not Allow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);
    }
}

