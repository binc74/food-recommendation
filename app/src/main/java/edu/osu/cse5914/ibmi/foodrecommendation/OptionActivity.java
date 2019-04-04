package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {
    Button mProfile;
    Button mTakePhoto;
    Button mHistButton;
    Button mBack;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProfile = findViewById(R.id.profile);
        mProfile.setOnClickListener(this);

        mTakePhoto = findViewById(R.id.photo);
        mTakePhoto.setOnClickListener(this);

        mHistButton = findViewById(R.id.history);
        mHistButton.setOnClickListener(this);

        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);

        uid = getIntent().getExtras().getString("uid");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile:
                Intent profile_intent = new Intent(this, ProfileActivity.class); //link to preference view
                profile_intent.putExtra("uid", uid);
                startActivity(profile_intent);
                break;

            case R.id.photo:
                Intent cam_intent = new Intent(this, CameraActivity.class); //link to preference view
                cam_intent.putExtra("uid", uid);
                startActivity(cam_intent);
                break;

            case R.id.history:
                Intent hist_intent = new Intent(this, HistoryActivity.class); //link to preference view
                hist_intent.putExtra("uid", uid);
                startActivity(hist_intent);
                break;
            case R.id.back:
                Intent i = new Intent(this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }
}
