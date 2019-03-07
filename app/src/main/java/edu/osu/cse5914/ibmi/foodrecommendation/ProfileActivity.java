package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Const;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mGender;
    private EditText mWeight;
    private EditText mBirthday;
    private EditText mPref;
    private EditText mDiet;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uid = getIntent().getExtras().getString("uid");

        mUsername = findViewById(R.id.username);
        mGender = findViewById(R.id.gender);
        mWeight = findViewById(R.id.weight);
        mBirthday = findViewById(R.id.birthday);
        mPref = findViewById(R.id.preference);
        mDiet = findViewById(R.id.diet_opt);

        mUsername.setText(uid);

        new UserService().processUserById(uid,
                task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        User user = UserService.getUserFromDocument(ds);

                        if (user == null) {
                            Toast.makeText(this, "User not exists!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            int gender = user.getGender();
                            if (gender > 0) {
                                mGender.setText(Const.stype[gender]);
                            }

                            float weight = user.getWeight();
                            if (weight > 0) {
                                mWeight.setText(Float.toString(weight));
                            }

                            String birthday = user.getBirthday();
                            if (!birthday.equals("")) {
                                mBirthday.setText(birthday);
                            }

                            int pref = user.getHealthoption();
                            if (pref > 0) {
                                mPref.setText(Const.dtype[pref]);
                            }

                            int diet = user.getDietoption();
                            if (diet > 0) {
                                mDiet.setText(Const.ptype[diet]);
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
