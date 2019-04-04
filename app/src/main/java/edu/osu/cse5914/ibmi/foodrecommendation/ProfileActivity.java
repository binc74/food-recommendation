package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Const;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private String NOT_SET = "Not Set Yet!";

    private TextView mUsername;
    private TextView mGender;
    private TextView mWeight;
    private TextView mBirthday;
    private TextView mPref;
    private TextView mDiet;
    private TextView mAllergies;

    private Button mUpdate;
    private Button mBack;

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
                                mGender.setText(Const.genders[gender]);
                                mGender.setTextColor(Color.GRAY);
                            }
                            else {
                                mGender.setText(NOT_SET);
                                mGender.setTextColor(Color.RED);
                            }

                            float weight = user.getWeight();
                            if (weight > 0) {
                                mWeight.setText(Float.toString(weight));
                                mWeight.setTextColor(Color.GRAY);
                            }
                            else {
                                mWeight.setText(NOT_SET);
                                mWeight.setTextColor(Color.RED);
                            }

                            String birthday = user.getBirthday();
                            if (!birthday.equals("")) {
                                mBirthday.setText(birthday);
                                mBirthday.setTextColor(Color.GRAY);
                            }
                            else {
                                mBirthday.setText(NOT_SET);
                                mBirthday.setTextColor(Color.RED);
                            }

                            int pref = user.getHealthoption();
                            if (pref > 0) {
                                mPref.setText(Const.prefs[pref]);
                                mPref.setTextColor(Color.GRAY);
                            }
                            else {
                                mPref.setText(NOT_SET);
                                mPref.setTextColor(Color.RED);
                            }

                            int diet = user.getDietoption();
                            if (diet > 0) {
                                mDiet.setText(Const.diets[diet]);
                                mDiet.setTextColor(Color.GRAY);
                            }
                            else {
                                mDiet.setText(NOT_SET);
                                mDiet.setTextColor(Color.RED);
                            }

                            ArrayList<String> as = user.getAllergies();
                            if (as.size() > 0) {
                                String str = as.get(0);
                                for (int i = 1; i < as.size(); ++i) {
                                    str += ", " + as.get(i);
                                }
                                mAllergies.setText(str);
                                mAllergies.setTextColor(Color.GRAY);
                            }
                            else {
                                mAllergies.setText(NOT_SET);
                                mAllergies.setTextColor(Color.RED);
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
        mAllergies = findViewById(R.id.allergies);

        mUpdate = findViewById(R.id.edit_profile);
        mUpdate.setOnClickListener(this);

        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);

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
                pref_intent.putStringArrayListExtra("allergies", user.getAllergies());

                startActivity(pref_intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
