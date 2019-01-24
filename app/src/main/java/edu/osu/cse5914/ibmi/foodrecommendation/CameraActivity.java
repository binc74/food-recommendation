package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button recommButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        recommButton = findViewById(R.id.recommendation);
        recommButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recommendation:
                Intent pref_intent = new Intent(this, MainActivity.class); //link to preference view
                startActivity(pref_intent);
                break;
        }
    }
}
