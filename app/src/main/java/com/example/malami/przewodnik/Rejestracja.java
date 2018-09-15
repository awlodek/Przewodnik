package com.example.malami.przewodnik;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Rejestracja extends AppCompatActivity {

    private EditText email;
    private EditText haslo;
    private FirebaseAuth firebaseAuth;
    private Button rejestruj;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        email = findViewById(R.id.email);
        haslo = findViewById(R.id.haslo);
        firebaseAuth = FirebaseAuth.getInstance();
        rejestruj=findViewById(R.id.rejestruj);


        progressDialog  = new ProgressDialog(this);
    }


    public void rejestruj(View view) {
       /* final ProgressDialog progressDialog = ProgressDialog.show(Rejestracja.this,"Proszę czekać...",
                "", true);
        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),haslo.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (!task.isSuccessful()) {
                            Toast.makeText(Rejestracja.this, "Udało się zarejestrować", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Rejestracja.this, Logowanie.class);
                            startActivity(i);
                        }

                        else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Rejestracja.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }


                    }
                });*/
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



        progressDialog.setMessage("Rejestracja, proszę czekać.. ");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(semail,shaslo)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(Rejestracja.this, "Udało się zarejestrować", Toast.LENGTH_LONG).show();
                            

                            Intent i = new Intent(Rejestracja.this, Logowanie.class);
                            startActivity(i);
                        }

                        else {
                            Toast.makeText(Rejestracja.this, "Nie udało się zarejestrować", Toast.LENGTH_LONG).show();
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(Rejestracja.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}