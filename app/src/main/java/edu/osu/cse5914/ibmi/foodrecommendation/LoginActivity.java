package edu.osu.cse5914.ibmi.foodrecommendation;

//import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "LoginActivity";
    private static String ERROR_EMPTY = "Should not be empty";

    private EditText mPassword;
    private AutoCompleteTextView mEmail;
    private Button mloginButton;
    private Button msignupButton;

    private UserService userService;

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
    protected void onPause() {
        super.onPause();

        mEmail.setText("");
        mPassword.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                if (EditTextUtil.warnIfEmpty(mEmail) || EditTextUtil.warnIfEmpty(mPassword)) {
                    return;
                }

                userService.processUserById(mEmail.getText().toString().trim(),
                        task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot ds = task.getResult();
                                User user = UserService.getUserFromDocument(ds);

                                String currPw = EditTextUtil.getString(mPassword);

                                if (user == null || !currPw.equals(user.getPassword())) {
                                    Toast.makeText(this, "User ID or Password you enter is incorrect!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    if (user.getNeedinit()) {
                                        Intent pref_intent = new Intent(this, PrefSetActivity.class); //link to preference view
                                        pref_intent.putExtra("uid", user.getDocumentId());
                                        pref_intent.putExtra("needExit", false);
                                        startActivity(pref_intent);
                                    }
                                    else {
                                        Intent opt_intent = new Intent(this, OptionActivity.class); //link to preference view
                                        opt_intent.putExtra("uid", user.getDocumentId());
                                        startActivity(opt_intent);
                                    }
                                }
                            }
                        });
                break;

            case R.id.email_sign_up:
                Intent signup_intent = new Intent(this, SignUpActivity.class); //link to signup view
                startActivity(signup_intent);
                break;
        }
    }
}
