package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "SignUpActivity";

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.submit:
            if (EditTextUtil.warnIfEmpty(mUsername) || EditTextUtil.warnIfEmpty(mPassword) || EditTextUtil.warnIfEmpty(mReenterPassword)) {
                return;
            }

            if (!EditTextUtil.isEqual(mPassword, mReenterPassword)) {
                EditTextUtil.setWarning(mReenterPassword, "Not match to password you enter");
            }
            else {
                User user = new User(EditTextUtil.getString(mUsername), EditTextUtil.getString(mPassword));
                userService.processUser(user,
                        task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Log.d(TAG, "User already exists");
                                    EditTextUtil.setWarning(mUsername, getString(R.string.err_user_exist));
                                } else {
                                    userService.addNewUser(user, aVoid -> {
                                        Toast.makeText(this, "User " + EditTextUtil.getString(mUsername) + " successfully created!", Toast.LENGTH_LONG).show();
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
