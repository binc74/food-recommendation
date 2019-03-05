package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "LoginActivity";

    private TextView mPassword;
    private AutoCompleteTextView mEmail;
    private Button mloginButton;
    private Button msignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mloginButton = findViewById(R.id.email_sign_in_button);
        mloginButton.setOnClickListener(this);

        msignupButton = findViewById(R.id.email_sign_up);
        msignupButton.setOnClickListener(this);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                Intent pref_intent = new Intent(this, PrefSetActivity.class); //link to preference view
                startActivity(pref_intent);
                break;

            case R.id.email_sign_up:
                Intent signup_intent = new Intent(this, SignUpActivity.class); //link to signup view
                startActivity(signup_intent);
                break;
        }
    }
}
