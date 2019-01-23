package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mTextView = findViewById(R.id.text1);
        mButton = findViewById(R.id.take_picture);
        mButton.setOnClickListener(this);

        Button btnNext = findViewById(R.id.submit_photo);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.take_picture:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.submit_photo:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
