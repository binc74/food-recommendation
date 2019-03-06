package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity implements View.OnClickListener {
    Button mProfile;
    Button mTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        mProfile = findViewById(R.id.profile);
        mProfile.setOnClickListener(this);

        mTakePhoto = findViewById(R.id.photo);
        mTakePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile:
                break;

            case R.id.photo:
                break;
        }
    }
}
