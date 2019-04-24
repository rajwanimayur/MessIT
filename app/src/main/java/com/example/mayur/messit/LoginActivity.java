package com.example.mayur.messit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailId;
    private EditText password;
    private Button loginBtn;
    private int attemptLeft = 5;

    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailId = (EditText) findViewById(R.id.login_et_emailid);
        password = (EditText) findViewById(R.id.login_et_password);
        loginBtn = (Button) findViewById(R.id.login_btn_submit);
        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String EMAIL = emailId.getText().toString();
                final String PASSWORD = password.getText().toString();

                if (TextUtils.isEmpty(EMAIL)) {
                    emailId.setError("Email Required");
                    emailId.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
                    emailId.setError("Please Enter a Valid Email");
                    emailId.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(PASSWORD)) {
                    password.setError("Password Required");
                    password.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //Signin with credentials provided
                firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), FoodMenuActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this,
                                            getString(R.string.login_err_auth_error, --attemptLeft),
                                            Toast.LENGTH_LONG).show();
                                    if (attemptLeft == 0)
                                        loginBtn.setEnabled(false);
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAndRemoveTask();
    }

    public void onSignUp(View v){
        Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
        startActivity(intent);
    }
}

