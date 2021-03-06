package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yelp.fusion.client.models.Coordinates;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestRecipeTask;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.SuggestRestaurantTask;

public class SuggestionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mRestaurantButton;
    private Button mRecipeButton;
    private Button mBack;
   // private Button mHint;
    private ListView lvJson;
    private String foodType;
    private TextView mTextView;
    private TextView mTextHint;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_suggestion );

        foodType = getIntent().getStringExtra("foodType");
        Log.d("Suggest", foodType);

        mTextView = findViewById( R.id.kun_pao );
        mTextHint = findViewById( R.id.hint_text );
        Log.d("Suggest", "3");
        mRestaurantButton = findViewById( R.id.restaurant );
        mRestaurantButton.setOnClickListener(this);
        mRestaurantButton.setEnabled(false);
        Log.d("Suggest", "4");
        mRecipeButton = findViewById( R.id.recipe );
        mRecipeButton.setOnClickListener(this);
        Log.d("Suggest", "5");
        lvJson = findViewById( R.id.listView1 );
        Log.d("Suggest", "6");

        Log.d("Suggest", "566");

        mBack = findViewById( R.id.back );
        mBack.setOnClickListener(this);

        //mHint = findViewById( R.id.hint );
        //mHint.setOnClickListener(this);

        new SuggestRestaurantTask(lvJson,getApplicationContext()).execute(foodType);
        mTextView.setText("Restaurants Serve the Food");
        mTextHint.setText("Click Items above for More Information!");
        setRestaurantOnClick();
        Toast.makeText( this,"Press the RESTAURANT button to find the restaurants serve the food! Press the RECIPE button to find the recipes for the food!", Toast.LENGTH_LONG ).show();

    }

    private void setRecipeOnClick() {
        lvJson.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                Log.d("Suggest", "233");
                /*TextView textView = view.findViewById(R.id.textView3);
                String url = textView.getText().toString().substring( 8 );*/
                Restaurant res = (Restaurant) parent.getItemAtPosition(position);
                String url = res.getDisplayPhone();
                if(url.toLowerCase().contains("http")) {
                    Log.d( "Suggest", url );
                    Intent i = new Intent( Intent.ACTION_VIEW );
                    i.setData( Uri.parse( url ) );
                    startActivity( i );
                }
                //new SuggestRestaurantTask(lvRestaurantJson,getApplicationContext()).execute(foodType);
            }
        });
    }

    private void setRestaurantOnClick() {
        lvJson.setOnItemClickListener(((parent, view, position, id) -> {
            Restaurant res = (Restaurant) parent.getItemAtPosition(position);
            Coordinates coord = res.getCoord();
            String name = res.getName();

            Intent map_intent = new Intent(this, MapsActivity.class); //link to signup view

            map_intent.putExtra("lat", coord.getLatitude());
            map_intent.putExtra("lng", coord.getLongitude());
            map_intent.putExtra("name", name);

            startActivity(map_intent);
        }));
    }


    @Override
    public void onClick(View v) {
        Log.d("Suggest", "888");
        switch (v.getId()) {

            case R.id.restaurant:
                flag = false;
                Log.d("Suggest", foodType);
                new SuggestRestaurantTask(lvJson,getApplicationContext()).execute(foodType);
                mTextView.setText("Restaurants Serve the Food");
                setRestaurantOnClick();
                mRestaurantButton.setEnabled( false );
                mRecipeButton.setEnabled( true );
                break;
            case R.id.recipe:
                flag = true;
                Log.d("Suggest", foodType);
                new SuggestRecipeTask(lvJson,getApplicationContext()).execute(foodType);
                mTextView.setText("Recipe for the Food");
                setRecipeOnClick();
                mRestaurantButton.setEnabled( true );
                mRecipeButton.setEnabled( false );
                break;
            case R.id.back:
                finish();
                break;
            /*case R.id.hint:
                Toast.makeText( this,"Press the RESTAURANT button to find the restaurants serve the food! Press the RECIPE button to find the recipes for the food!", Toast.LENGTH_LONG ).show();
                break;
*/
        }
    }

}
