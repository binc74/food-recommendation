package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PrefSetActivity extends AppCompatActivity implements View.OnClickListener {

   // private TextView mTextView;
    private Button msubmitButton;
    private Spinner mdietSpinner;
    private Spinner mprefSpinner;
    private Spinner msexSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_set);
        // = findViewById(R.id.text1);
        msubmitButton = findViewById(R.id.submit_pref);
        msubmitButton.setOnClickListener(this);

        String[] dtype = new String[]{"Lose Weight", "Gain Weight", "Keep Healthy", "Build Muscle"};
        String[] ptype = new String[]{"Vegetarian", "Vegan", "Nondairy", "None"};
        String[] stype = new String[]{"Male", "Female"};
        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dtype);
        ArrayAdapter<String> padapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ptype);
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stype);

        dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mprefSpinner = super.findViewById(R.id.spinner3);
        mprefSpinner.setAdapter(padapter);
        mdietSpinner = super.findViewById(R.id.spinner2);
        mdietSpinner.setAdapter(dadapter);
        msexSpinner = super.findViewById(R.id.spinner);
        msexSpinner.setAdapter(sadapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_pref:
                Intent pref_intent = new Intent(this, CameraActivity.class); //link to preference view
                startActivity(pref_intent);
                break;
        }
    }
}
