package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.Coordinates;
import com.yelp.fusion.client.models.SearchResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.adapters.RestaurantListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;
import retrofit2.Call;
import retrofit2.Response;

public class SuggestRestaurantTask extends AsyncTask<String, Void, ArrayList> {
    protected TextView mText;
    protected ListView mList;
    private final Context mContext;
   // private String foodType;


    public SuggestRestaurantTask(ListView lv,Context ct){
        mList = lv;
        mContext = ct;
       // foodType=foodType;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected ArrayList<Restaurant> doInBackground(String... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";
        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        try {
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(ProjectApi.YELP_API);

            Map<String, String> params = new HashMap<>();
            Map<String, String> params2 = new HashMap<>();

// general params
            Log.d("RestaurantTask", "Current File: " + objects[0]);
            params.put("term", objects[0]);
            // Log.d("RestaurantTask", "Current File: " + foodType);
            params.put("latitude", "40.002230");
            params.put("longitude", "-83.015695");


            // Log.d("RestaurantTask", "Current File: " + foodType);
            params2.put("latitude", "40.002230");
            params2.put("longitude", "-83.015695");

            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            Response<SearchResponse> response = call.execute();
            if(response.body().getBusinesses().size() <= 0){
                params2.put("term", objects[0].substring( 0,10 ));
                call = yelpFusionApi.getBusinessSearch(params2);
                response = call.execute();
            }
            ArrayList<Business> business=response.body().getBusinesses();
            String a="s";
            for (int x=0; x<business.size(); x++){
                Log.d("RestaurantTask", "Current File: " +"123");
                Business b=business.get(x);
                String name=b.getName();
                Log.d("RestaurantTask", "Current File: " +name);
                String rating=Double.toString(b.getRating());
                String displayedPhone=b.getDisplayPhone();
                String imgUrl=b.getImageUrl();
                Coordinates coord=b.getCoordinates();
                restaurantList.add(new Restaurant(name,rating, imgUrl,displayedPhone,coord));//use rating=1 and prepTime=2 for now and change later

            }



        }
        catch (IOException e){
            e.printStackTrace();

        }


        return restaurantList;

    }

    @Override
    protected void onPostExecute(ArrayList o) {
        super.onPostExecute(o);

        RestaurantListAdapter adapter = new RestaurantListAdapter(mContext, R.layout.adapter_view_layout, o);
        mList.setAdapter(adapter);


    }



}
