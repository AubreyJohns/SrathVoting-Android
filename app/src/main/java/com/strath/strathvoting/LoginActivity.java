package com.strath.strathvoting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password ;
    String EmailHolder, PasswordHolder;
    Button Login;
    TextView SignUP;
    Boolean EditTextEmptyCheck;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        Login = (Button)findViewById(R.id.btn_login);
        SignUP = (TextView)findViewById(R.id.link_signup);
        progressDialog =  new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        firebaseAuth = FirebaseAuth.getInstance();
        // Start: Check if user already logged in before and not logged out properly.
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                // If  EditTextEmptyCheck == true
                if(EditTextEmptyCheck)
                {
                    LoginFunction();
                }
                else {
                    // If  EditTextEmptyCheck == false then toast display on screen.
                    Toast.makeText(LoginActivity.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot(){
        EmailHolder = email.getText().toString().trim();
        PasswordHolder = password.getText().toString().trim();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            EditTextEmptyCheck = false;
        }
        else {
            EditTextEmptyCheck = true ;
        }
    }

    public void LoginFunction(){
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait, authenticating");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Email or Password Not found, Please Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
