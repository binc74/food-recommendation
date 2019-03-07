package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;

import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestRestaurantTask;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestionTask;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.TranslationTask;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.SearchResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.net.Proxy.Type.HTTP;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";

    private Button getCal;
    private EditText mEditText;
    private TextView mTextView;

    private TextView tvRecepieJson;
    private Button btnFetchRecepie;
    private String text;


    private String food_category;

    private String maxCalAllowed;
    private String minCalAllowed;


    private LanguageTranslator translator;
    private TranslationTask transTask;



    private ListView lvRecepieJson;

    private ListView lvRestaurantJson;




    @Override

    protected void onCreate(Bundle savedInstanceState) {

        food_category= getIntent().getStringExtra("food_category");
        if (food_category.equals("Steak")||food_category.equals("Pizza")||food_category.equals("Hamburger")||food_category.equals("French Fries")) {
            minCalAllowed = "0.5";
            maxCalAllowed = "1.0";
        }
        else{
            minCalAllowed="4";
        maxCalAllowed = "8";}


        super.onCreate(savedInstanceState);
        text = getIntent().getStringExtra("id");
        setContentView(R.layout.activity_main);
//        mButton = findViewById(R.id.translate);
//        mButton.setOnClickListener(this);
//        mTextView = findViewById(R.id.text_trans);
//        mEditText = findViewById(R.id.password);

//        tvRecepieJson=findViewById(R.id.tv_recepie_json);
       // btnFetchRecepie=findViewById(R.id.btn_fetch_recepie);
      //  btnFetchRecepie.setOnClickListener(this);
        //getCal=findViewById(R.id.cal_feedback);
       // getCal.setOnClickListener(this);
        //mTextView = findViewById( R.id.cal_feedback );
        //mTextView.setMovementMethod(LinkMovementMethod.getInstance());

        lvRecepieJson= (ListView) findViewById(R.id.listView);
        lvRestaurantJson= (ListView) findViewById(R.id.listView1);


//        Recepie r1 = new Recepie("r1","1","2" );
//        Recepie r2 = new Recepie("r2","2", "3");
//
//
//        ArrayList<Recepie> recepieList = new ArrayList<>();
//        recepieList.add(r1);
//        recepieList.add(r2);
        new SuggestionTask(lvRecepieJson, getApplicationContext(),maxCalAllowed,minCalAllowed).execute();
        lvRecepieJson.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                TextView textView = view.findViewById(R.id.textView2);
                String foodType = textView.getText().toString();

                String testreg = "[^a-zA-Z\\s-]";
                Pattern matchsip = Pattern.compile(testreg);
                Matcher mp = matchsip.matcher(foodType);
                foodType = mp.replaceAll("");
                foodType = foodType.toLowerCase().replaceAll( "slow cooker |how to |cook |from the guys at robertas|make |easy athome | - ", "" );
                Intent sugg_intent = new Intent(MainActivity.this, SuggestionActivity.class); //link to preference view
                Log.d("Main", "1");
                sugg_intent.putExtra("foodType", foodType);
                Log.d("Main", "2");
                startActivity(sugg_intent);
                Log.d("Main", "3");
                //new SuggestRestaurantTask(lvRestaurantJson,getApplicationContext()).execute(foodType);
            }
        });
        Log.d(TAG, "Success Init");
    }

    @Override
    public void onClick(View v) {

    }
}
