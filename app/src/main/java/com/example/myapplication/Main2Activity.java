package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Registration screen
public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email2;
    private EditText password2;
    private ProgressDialog progressDialog;
    private boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        email2 = findViewById(R.id.userText2);
        password2 = findViewById(R.id.passText2);


    }

        public void func_Register (View view){

            Button b = (Button) findViewById(R.id.button);
            Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein);
            b.startAnimation(a);

            final String user2 = email2.getText().toString();
            final String pass2 = password2.getText().toString();

            if(TextUtils.isEmpty(user2) || TextUtils.isEmpty(pass2)){
                Toast.makeText(Main2Activity.this,"Wrong email or password", Toast.LENGTH_SHORT).show();
                return;
            }

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registering...");
            progressDialog.show();



            Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email2.getText().toString(), password2.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(Main2Activity.this, "Successfuly Registered.", Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    // Name, email address, and profile photo Url
                                    String id = user.getUid();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("users");
                                    User u = new User(user2, pass2, id, admin);
                                    myRef.child(id).setValue(u);
                                }
                            } else
                            {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Main2Activity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                            }
                        }
                    });
        }

    public void isAdmin(View view) {
        Switch simpleSwitch = (Switch) findViewById(R.id.switch1);
        admin = simpleSwitch.isChecked();
    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_right);
    }
}

