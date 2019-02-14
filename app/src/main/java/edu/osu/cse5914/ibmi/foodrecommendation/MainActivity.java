package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.util.ArrayList;
import java.util.HashMap;

import edu.osu.cse5914.ibmi.foodrecommendation.util.DiscoveryTask;
import edu.osu.cse5914.ibmi.foodrecommendation.util.SuggestionTask;
import edu.osu.cse5914.ibmi.foodrecommendation.util.TranslationTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";

    private Button getCal;
    private EditText mEditText;
<<<<<<< HEAD
    private TextView mTextView;

    private TextView tvRecepieJson;
    private Button btnFetchRecepie;
    private String text;
=======

    private String food_category;
    private TextView tvRecepieJson;
    private Button btnFetchRecepie;
    private String maxCalAllowed;

>>>>>>> bda08a5605137df9a90f6af57a5424be5952f1ed
    private LanguageTranslator translator;
    private TranslationTask transTask;



    private ListView lvRecepieJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        food_category= getIntent().getStringExtra("food_category");
        if (food_category.equals("Steak"))
            maxCalAllowed="0.5";
        if (food_category.equals("Pizza"))
            maxCalAllowed="0.6";
        if (food_category.equals("Hamburger"))
            maxCalAllowed="1.5";
        if (food_category.equals("French Fries"))
            maxCalAllowed="2";
        else
            maxCalAllowed="4";





        super.onCreate(savedInstanceState);
        text = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_main);
//        mButton = findViewById(R.id.translate);
//        mButton.setOnClickListener(this);
//        mTextView = findViewById(R.id.text_trans);
//        mEditText = findViewById(R.id.password);

//        tvRecepieJson=findViewById(R.id.tv_recepie_json);
        btnFetchRecepie=findViewById(R.id.btn_fetch_recepie);
        btnFetchRecepie.setOnClickListener(this);
        getCal=findViewById(R.id.cal_feedback);
        getCal.setOnClickListener(this);
        mTextView = findViewById( R.id.cal_feedback );
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());

        lvRecepieJson= (ListView) findViewById(R.id.listView);

//        Recepie r1 = new Recepie("r1","1","2" );
//        Recepie r2 = new Recepie("r2","2", "3");
//
//
//        ArrayList<Recepie> recepieList = new ArrayList<>();
//        recepieList.add(r1);
//        recepieList.add(r2);





        Log.d(TAG, "Success Init");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cal_feedback:
                new DiscoveryTask(text,mTextView).execute();
                break;

            case R.id.btn_fetch_recepie:
                new SuggestionTask(lvRecepieJson, getApplicationContext(),maxCalAllowed).execute();
                break;

        }
    }
}
