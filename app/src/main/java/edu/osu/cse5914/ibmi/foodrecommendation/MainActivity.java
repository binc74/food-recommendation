package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.util.HashMap;

import edu.osu.cse5914.ibmi.foodrecommendation.util.TranslationTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";

    private TextView mTextView;
    private Button mButton;

    private LanguageTranslator translator;
    private TranslationTask transTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.translate);
        mButton.setOnClickListener(this);
        mTextView = findViewById(R.id.text_trans);

        transTask = new TranslationTask(mTextView);
        Log.d(TAG, "Success Init");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.translate:
                transTask.execute("Hello", "en-es");
                break;
        }
    }
}
