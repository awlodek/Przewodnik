package com.example.malami.przewodnik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Logowanie extends AppCompatActivity {

    EditText email;
    EditText haslo;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        email=(findViewById(R.id.email));
        haslo = (findViewById(R.id.haslo));
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
    }

    public void logowanie(View view) {

        String semail = email.getText().toString().trim();
        String shaslo = haslo.getText().toString().trim();

        if(TextUtils.isEmpty((semail))&& TextUtils.isEmpty(shaslo))
        {
            Toast.makeText(this, "Podaj email i hasło", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(semail))
        {
            //email pusty
            Toast.makeText(this, "Podaj email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(shaslo))
        {
            //haslo puste
            Toast.makeText(this, "Podaj hasło", Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Logowanie, proszę czekać.. ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(semail,shaslo)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Logowanie.this, "Logowanie udało się", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(Logowanie.this, "Logowanie nie udało się", Toast.LENGTH_LONG).show();
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Logowanie.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}