package edu.osu.cse5914.ibmi.foodrecommendation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestRestaurantTask;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRestaurantButton;
    private Button mRecipeButton;
    private ListView lvJson;
    private String foodType;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_suggestion );

        foodType = getIntent().getStringExtra("foodType");
        Log.d("Suggest", foodType);

        mTextView = findViewById( R.id.kun_pao );
        Log.d("Suggest", "3");
        mRestaurantButton = findViewById( R.id.restaurant );
        mRestaurantButton.setOnClickListener(this);
        Log.d("Suggest", "4");
        mRecipeButton = findViewById( R.id.recipe );
        mRecipeButton.setOnClickListener(this);
        Log.d("Suggest", "5");
        lvJson = findViewById( R.id.listView1 );
        Log.d("Suggest", "6");


    }

    @Override
    public void onClick(View v) {
        Log.d("Suggest", "888");
        switch (v.getId()) {

            case R.id.restaurant:
                Log.d("Suggest", foodType);
                new SuggestRestaurantTask(lvJson,getApplicationContext()).execute(foodType);
                break;
            case R.id.recipe:
                //Intent i = new Intent(Intent.ACTION_VIEW);
                // i.setData( Uri.parse(mDiscoveryView.getText().toString()));
                // startActivity(i);
                break;

        }
    }

}
