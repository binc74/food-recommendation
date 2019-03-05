package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "SignUpActivity";

    private static String ERROR_EMPTY = "Should not be empty";

    AutoCompleteTextView mUsername;
    AutoCompleteTextView mPassword;
    AutoCompleteTextView mReenterPassword;

    Button mSubmit;
    Button mBack;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mReenterPassword = findViewById(R.id.confirm_password);
        mSubmit = findViewById(R.id.submit);
        mBack = findViewById(R.id.back);

        mSubmit.setOnClickListener(this);
        mBack.setOnClickListener(this);

        userService = new UserService();
    }

    private String getString(AutoCompleteTextView tv) {
        return tv.getText().toString().trim();
    }

    private boolean isEmpty(AutoCompleteTextView tv) {
        return getString(tv).equals("");
    }

    private void setWarning(AutoCompleteTextView tv, String msg) {
        tv.setError(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.submit:
            if (isEmpty(mUsername)) {
                setWarning(mUsername, ERROR_EMPTY);
            }
            else if (isEmpty(mPassword)) {
                setWarning(mPassword, ERROR_EMPTY);
            }
            else if (isEmpty(mReenterPassword)) {
                setWarning(mReenterPassword, ERROR_EMPTY);
            }
            else if (!getString(mPassword).equals(getString(mReenterPassword))) {
                setWarning(mReenterPassword, "Not match to password you enter");
            }
            else {
                User user = new User(getString(mUsername), getString(mPassword));
                userService.processUser(user,
                        task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "User already exists");
                                    setWarning(mUsername, getString(R.string.err_user_exist));
                                } else {
                                    userService.addNewUser(user, aVoid -> {
                                        Toast.makeText(this, "User " + getString(mUsername) + " successfully created!", Toast.LENGTH_LONG).show();
                                        finish();
                                    });
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        });
            }
            break;

        case R.id.back:
            finish();
            break;
        }
    }
}
