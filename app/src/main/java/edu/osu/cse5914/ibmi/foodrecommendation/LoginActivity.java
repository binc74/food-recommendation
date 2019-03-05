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
    UserService userService;

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

        userService = new UserService();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                Intent pref_intent = new Intent(this, PrefSetActivity.class); //link to preference view
                startActivity(pref_intent);
                break;

            case R.id.email_sign_up:
                //Intent signup_intent = new Intent(this, PrefSetActivity.class); //link to signup view
                //startActivity(signup_intent);
                User user = new User(mEmail.getText().toString().trim(), mPassword.getText().toString().trim());
                userService.checkNewUser(user,
                        task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "User already exists");
                                    mEmail.setError(getString(R.string.err_user_exist));
                                } else {
                                    userService.addNewUser(user);
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        });
                break;
        }
    }
}
