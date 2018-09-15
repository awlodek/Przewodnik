package com.example.malami.przewodnik;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener muAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button logowanie = (Button) findViewById(R.id.zaloguj);
        Button rejestrowanie =(Button) findViewById(R.id.rejestruj);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        muAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Log.d("Auth",user.getEmail());
                    Log.d("Auth", user.getUid());
                    //Toast.makeText(getApplicationContext(),"Zalogowano"+user.getUid(),Toast.LENGTH_LONG).show();
                }
            }
        };


    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(muAuthListener);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(muAuthListener);
    }

    public void Anonimowo(View view) {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.d("Auth", String.valueOf(task.getException()));
                }
            }
        });
    }


    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }





    public void login(View view) {
        Intent intent;
        intent= new Intent(this, Logowanie.class);
        startActivity(intent);
    }

    public void rejestruj(View view) {
        Intent intent;
        intent= new Intent(this, Rejestracja.class);
        startActivity(intent);
    }
}
