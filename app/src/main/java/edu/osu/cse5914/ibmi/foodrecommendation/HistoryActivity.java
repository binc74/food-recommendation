package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Bundle extra = getIntent().getExtras();
        uid = extra.getString("uid");
    }
}
