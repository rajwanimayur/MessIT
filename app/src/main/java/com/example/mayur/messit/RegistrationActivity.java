package com.example.mayur.messit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "Register";

    private ProgressBar progressBar;
    private EditText editTextName;
    private EditText editTextRollNo;
    private EditText editTextEmail;
    private EditText editTextContact;
    private EditText editTextPassword;
    private Button regSubmitButton;
    private Button regCancelButton;

    private String foodPreference;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = (EditText) findViewById(R.id.reg_et_name);
        editTextRollNo = (EditText) findViewById(R.id.reg_et_rollNo);
        editTextEmail = (EditText) findViewById(R.id.reg_et_emailid);
        editTextContact = (EditText) findViewById(R.id.reg_et_contact);
        editTextPassword = (EditText) findViewById(R.id.reg_et_password);
        regSubmitButton = (Button) findViewById(R.id.reg_btn_submit);
        regCancelButton = (Button) findViewById(R.id.reg_btn_cancel);

        progressBar = (ProgressBar)findViewById(R.id.reg_progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        firebaseAuth = FirebaseAuth.getInstance();

        regSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                final String name = editTextName.getText().toString();
                if(!checkName(name)) return;

                final String rollNo = editTextRollNo.getText().toString();
                if (!checkRollNo(rollNo)) return;

                final String email = editTextEmail.getText().toString();
                if (!checkEmailId(email)) return;

                final String contact = editTextContact.getText().toString();
                if (!checkContact(contact)) return;

                final String passwd = editTextPassword.getText().toString();
                if (!checkPassword(passwd)) return;

                firebaseAuth.createUserWithEmailAndPassword(email, passwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.INVISIBLE);
                                regSubmitButton.setEnabled(false);
                                regCancelButton.setEnabled(false);
                                if(task.isSuccessful()){
                                    firebaseUser = firebaseAuth.getCurrentUser();
                                    addUserInfo();
                                    Toast.makeText(RegistrationActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), FoodMenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(RegistrationActivity.this, "An Error Occurred", Toast.LENGTH_SHORT).show();
                                    RegistrationActivity.super.recreate();
                                }
                            }
                        });
            }
        });

    }

    private void addUserInfo(){
        String name = editTextName.getText().toString();
        String phone = editTextContact.getText().toString();
        String email = editTextEmail.getText().toString();
        String rollNo = editTextRollNo.getText().toString();

        User aUser = new User(email, name, phone, rollNo, foodPreference);

        FirebaseDatabaseHelper databaseHelper = new FirebaseDatabaseHelper();
        databaseHelper.updateUserInfo(firebaseUser, aUser);
    }

    private boolean checkName(String name){
        if (name.isEmpty()) {
            editTextName.setError("Required Field");
            editTextName.requestFocus();
            return false;
        }
        return true;    //No error
    }

    private boolean checkRollNo (String rollno){
        if (rollno.isEmpty()) {
            editTextRollNo.setError("Required Field");
            editTextRollNo.requestFocus();
            return false;
        }
        return true;    //No Error
    }

    private boolean  checkEmailId (String emailId){
        if (emailId.isEmpty()) {
            editTextEmail.setError("Required Field");
            editTextEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            editTextEmail.setError("Enter Valid Email Address");
            editTextEmail.requestFocus();
            return false;
        }

        return true;    //No Error
    }

    private boolean checkPassword (String password){
        if (password.isEmpty()) {
            editTextPassword.setError("Required Field");
            editTextPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            editTextPassword.setError("Password must be at least 6 characters");
            editTextPassword.requestFocus();
            return false;
        }
        return true;    //No Error
    }

    private boolean checkContact (String contact){
        if(contact.isEmpty()){
            editTextContact.setError("Required Field");
            editTextContact.requestFocus();
            return false;
        }
        else if(!Pattern.matches("[789][0-9]{9}", contact)){
            editTextContact.setError("Enter Valid Phone Number");
            editTextContact.requestFocus();
            return false;
        }
        return true;    //No Error
    }

    public void onFoodPreferenceSelect(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.jain: {
                if (checked) {
                    foodPreference = ((RadioButton) view).getText().toString();
                }
                break;
            }
            case R.id.northIndian: {
                if (checked) {
                    foodPreference = ((RadioButton) view).getText().toString();
                }
                break;
            }

            case R.id.southIndian: {
                if(checked) {
                    foodPreference = ((RadioButton) view).getText().toString();
                }
                break;
            }
        }
    }

    public void onCancel(View v){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}
