package com.club.club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText EmailField_s;
    private EditText PassField_s;
    private EditText NameField;
    private EditText usnField;
    private EditText PhoneNumberField;

    private ActionProcessButton btnSignup;
    private TextView AlreadyMember;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        EmailField_s = (EditText) findViewById(R.id.editText_email);
        PassField_s = (EditText) findViewById(R.id.editText_Password);
        NameField = (EditText) findViewById(R.id.editText_Name);
        PhoneNumberField = (EditText) findViewById(R.id.editText_PhoneNumber);
        usnField=(EditText)findViewById(R.id.editText_USN);

        btnSignup=(ActionProcessButton) findViewById(R.id.btnSignUp);

        AlreadyMember = (TextView) findViewById(R.id.AlreadyAccountText);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartSignup();
            }
        });

        AlreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // NOTE: this Activity should get open only when the user is not signed in, otherwise
                    // the user will receive another verification email.
                    sendVerificationEmail();
                }

            }
        };

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent

                            Toast.makeText(SignUp.this, "Email Verification sent",Toast.LENGTH_LONG).show();
                            // after email is sent, logout the user
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(SignUp.this, Login.class));
                            finish();
                        }
                        else
                        {

                        }
                    }
                });
    }

    private void StartSignup()
    {
        final String email = EmailField_s.getText().toString();
        final String pass = PassField_s.getText().toString();
        final String  phone_no = PhoneNumberField.getText().toString();
        final String name = NameField.getText().toString();
        final String usn = usnField.getText().toString();

        if(name.isEmpty())
        {
            NameField.setError("Name is Required");
            NameField.requestFocus();
            return;
        }
        if(email.isEmpty())
        {
            EmailField_s.setError("Email is Required");
            EmailField_s.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            EmailField_s.setError("Enter a valid mail");
            EmailField_s.requestFocus();
            return;
        }
        if(phone_no.isEmpty())
        {
            PhoneNumberField.setError("Phone Number is Required");
            PhoneNumberField.requestFocus();
            return;
        }
        if(phone_no.length()!=10)
        {
            PhoneNumberField.setError("Enter a valid Phone number");
            PhoneNumberField.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            PassField_s.setError("Password is Required");
            PassField_s.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            PassField_s.setError("Minimum Length of Password Should be 6");
            PassField_s.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    User user = new User(
                            email,name,phone_no,usn

                    );
                    FirebaseDatabase.getInstance().getReference("users").child(email.replace(".",","))
                            .setValue(user);


                    Toast.makeText(SignUp.this, "User Registered Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                       Toast.makeText(SignUp.this,"User with this email already exist.", Toast.LENGTH_SHORT).show();

                    }
                    else {
                      //  Toast.makeText(SignUp.this, "Some Error Occured", Toast.LENGTH_LONG).show();
                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                        Toast.makeText(SignUp.this, "Failed Registration: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
