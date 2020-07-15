package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Main screen
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private String administrator = "albain85@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.userText);
        password = findViewById(R.id.passText);

    }

    public void func_SignIn(View textView) {

        Button b = (Button) findViewById(R.id.button);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        b.startAnimation(a);
        final Intent logInScreen = new Intent(this , Main4Activity.class);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wiat...");

        String user = email.getText().toString();
        String pass = password.getText().toString();

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this,"Wrong email or password", Toast.LENGTH_SHORT).show();
            progressDialog.cancel();
            return;
        }
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(logInScreen);
                            overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_left);
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Login Successfuly.", Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                            progressDialog.cancel();
                        }
                        // ...
                    }
                });
    }

    private boolean isAdmin() {
        if(email.getText().toString().equals(administrator))
            return true;
        else
            return false;

    }


    public void regScreen(View view) {
        Button b = (Button) findViewById(R.id.button3);
        Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
        b.startAnimation(a);

        EditText user = (EditText) findViewById(R.id.userText);
        EditText pass = (EditText) findViewById(R.id.passText);

                Intent i = new Intent(this , Main2Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left , R.anim.slide_out_left);
    }
}
