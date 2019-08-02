package com.strath.strathvoting;

import android.content.Intent;
import android.app.ProgressDialog;
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

public class SignupActivity extends AppCompatActivity {
    EditText name, email, password ;
    Button SignUp;
    TextView Login;
    String EmailHolder, PasswordHolder ;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth ;
    Boolean EditTextStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=(EditText)findViewById(R.id.input_name);
        email = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        SignUp = (Button)findViewById(R.id.btn_signup);
        Login = (TextView) findViewById(R.id.link_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SignupActivity.this, R.style.AppTheme_Dark_Dialog);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                // If EditText is true then this block will execute.
                if(EditTextStatus){
                    UserRegistrationFunction();
                }
                // If EditText is false then this block will execute.
                else {
                    Toast.makeText(SignupActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void UserRegistrationFunction(){
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait, creating account");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(EmailHolder, PasswordHolder).
                addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            Intent intent = new Intent(SignupActivity.this, UserProfileActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SignupActivity.this,"Something Went Wrong.",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    public void CheckEditTextIsEmptyOrNot(){
        EmailHolder = email.getText().toString().trim();
        PasswordHolder = password.getText().toString().trim();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            EditTextStatus = false;
        }
        else {
            EditTextStatus = true ;
        }
    }
}