package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestRestaurantTask;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRestaurantButton;
    private Button mRecipeButton;
    private ListView lvJson;
    private String foodType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_suggestion );
        mRestaurantButton = findViewById( R.id.restaurant );
        mRecipeButton = findViewById( R.id.recipe );
        lvJson = findViewById( R.id.listView );
        foodType = getIntent().getStringExtra("foodType");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restaurant:
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
