package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mloginButton;
    private Button msingupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTextView = findViewById(R.id.text1);
        mloginButton = findViewById(R.id.email_sign_in_button);
        mloginButton.setOnClickListener(this);

        msingupButton = findViewById(R.id.email_sign_up);
        msingupButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                Intent pref_intent = new Intent(this, PrefSetActivity.class); //link to preference view
                startActivity(pref_intent);
                break;

            case R.id.email_sign_up:
                Intent signup_intent = new Intent(this, PrefSetActivity.class); //link to signup view
                startActivity(signup_intent);
                break;
        }
    }
}
