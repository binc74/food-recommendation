package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button mUpdate;

    private User user;
    private String uid;

    private void updateText() {
        new UserService().processUserById(uid,
                task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot ds = task.getResult();
                        user = UserService.getUserFromDocument(ds);

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

        mUpdate = findViewById(R.id.edit_profile);
        mUpdate.setOnClickListener(this);

        mUsername.setText(uid);

        updateText();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_profile:
                Intent pref_intent = new Intent(this, PrefSetActivity.class); //link to preference view
                pref_intent.putExtra("uid", uid);
                pref_intent.putExtra("needExit", true);
                pref_intent.putExtra("gender", user.getGender());
                pref_intent.putExtra("weight", user.getWeight());
                pref_intent.putExtra("birthday", user.getBirthday());
                pref_intent.putExtra("pref", user.getHealthoption());
                pref_intent.putExtra("diet", user.getDietoption());

                startActivity(pref_intent);
                break;
        }
    }
}
