package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrefSetActivity extends AppCompatActivity implements View.OnClickListener {

   // private TextView mTextView;
    private Button msubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_set);
        // = findViewById(R.id.text1);
        msubmitButton = findViewById(R.id.submit_pref);
        msubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_pref:
                Intent pref_intent = new Intent(this, LoginActivity.class); //link to preference view
                startActivity(pref_intent);
                break;


        }
    }
}
