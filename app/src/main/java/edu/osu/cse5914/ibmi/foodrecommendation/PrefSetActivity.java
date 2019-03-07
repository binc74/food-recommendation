package edu.osu.cse5914.ibmi.foodrecommendation;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Const;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;
import edu.osu.cse5914.ibmi.foodrecommendation.util.SpinnerUtil;

public class PrefSetActivity extends AppCompatActivity implements View.OnClickListener {

   // private TextView mTextView;
    private Button msubmitButton;
    private Button mSkip;
    private Spinner mdietSpinner;
    private Spinner mprefSpinner;
    private Spinner msexSpinner;

    private EditText mBirthday;
    private EditText mWeight;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_set);
        // = findViewById(R.id.text1);
        msubmitButton = findViewById(R.id.submit_pref);
        msubmitButton.setOnClickListener(this);

        mBirthday = findViewById(R.id.editText3);
        mWeight = findViewById(R.id.editText2);

        mSkip = findViewById(R.id.skip_pref);
        mSkip.setOnClickListener(this);

        ArrayAdapter<String> dadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Const.dtype);
        ArrayAdapter<String> padapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Const.ptype);
        ArrayAdapter<String> sadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Const.stype);

        dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        padapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mprefSpinner = super.findViewById(R.id.spinner3);
        mprefSpinner.setAdapter(padapter);
        mdietSpinner = super.findViewById(R.id.spinner2);
        mdietSpinner.setAdapter(dadapter);
        msexSpinner = super.findViewById(R.id.spinner);
        msexSpinner.setAdapter(sadapter);

        uid = getIntent().getExtras().getString("uid");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_pref:
                UserService userService = new UserService();
                userService.getDocumentReference(uid);

                if (!EditTextUtil.isEmpty(mWeight)) {
                    userService.updateWeight(Float.parseFloat(EditTextUtil.getString(mWeight)));
                }

                if (!EditTextUtil.isEmpty(mBirthday)) {
                    userService.updateBirthday(EditTextUtil.getString(mWeight));
                }

                int genderPos = SpinnerUtil.getOption(msexSpinner),
                    prefPos = SpinnerUtil.getOption(mprefSpinner),
                    dietPos = SpinnerUtil.getOption(mdietSpinner);

                if (genderPos != 0) {
                    userService.updateGender(genderPos);
                }

                if (prefPos != 0) {
                    userService.updateHealthOpt(prefPos);
                }

                if (dietPos != 0) {
                    userService.updateDietOpt(dietPos);
                }

                userService.updateNeedInit(false);

            case R.id.skip_pref:
                Intent opt_intent = new Intent(this, OptionActivity.class); //link to preference view
                opt_intent.putExtra("uid", uid);
                startActivity(opt_intent);
                break;
        }
    }
}
