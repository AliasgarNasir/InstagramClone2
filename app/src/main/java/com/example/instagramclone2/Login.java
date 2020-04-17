package com.example.instagramclone2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail,edtLoginPassword;
    private Button btnUserLogin,btnUserSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnUserSignUp = findViewById(R.id.btnUserSignup);

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        btnUserLogin.setOnClickListener(this);
        btnUserSignUp.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.btnUserLogin:
                if (edtLoginEmail.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")){
                    FancyToast.makeText(Login.this,"E-mail and Password are required.",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();

                }else {
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(Login.this, user.getUsername() + "  Login Successful.", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                                        transitionToSocialMediaActivity();
                                    } else {
                                        FancyToast.makeText(Login.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();

                                    }
                                }
                            });
                }
                break;
            case R.id.btnUserSignup:
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
                break;
        }


    }

    public void rootLayoutIsTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void transitionToSocialMediaActivity(){
        Intent intent = new Intent(Login.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
