package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.yelp.fusion.client.models.Coordinates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.adapters.RestaurantListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;

public class SuggestRecipeTask extends AsyncTask<String, Void, ArrayList> {

    protected TextView mText;
    protected ListView mList;
    private final Context mContext;

    //private final Context mContext;
    private String foodType;

    public SuggestRecipeTask(ListView lv,Context ct){
        mList = lv;
        mContext = ct;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList doInBackground(String... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
/*
        String name1 = "Allrecipes";
        String rcp1 = "https://www.allrecipes.com/search/results/?wt="+objects[0]+"&sort=re";
        String url1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbL5iIx8SVc6ca4n_KysNrTFpBzkpROwoYPYIAAEUqrx8FD7NV";
        Coordinates cd1=new Coordinates();
        String name2 = "Foodnetwork";
        String rcp2 = "https://www.foodnetwork.com/search/"+objects[0]+"-";
        String url2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrS-WRZvXKZcV5Dgzt4Dm-bukxKVrG9yWcDV60gyWavn8e9RX0";
        Coordinates cd2=new Coordinates();
        String name3 = "Myrecipes";
        String rcp3 = "https://www.myrecipes.com/search?q="+objects[0];
        String url3 = "https://i.pinimg.com/280x280_RS/b3/bd/9b/b3bd9b67581d28049965b05b626c7034.jpg";
        Coordinates cd3=new Coordinates();
        String name4 = "Taste of Home";
        String rcp4 = "https://www.tasteofhome.com/search/index?search="+objects[0];
        String url4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcThkmvQ7wrdKbUuoSRhK3-rraKonqYNKLeGIqx-JJRuQghHWIL8Hw";
        Coordinates cd4=new Coordinates();
        String name5 = "Delish";
        String rcp5 = "https://www.delish.com/search/?q="+objects[0];
        String url5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_zsEMiSUNbi2DMLyBkH66i2VdEK0GsRZCCbxwfk13DY0NzRQt";
        Coordinates cd5=new Coordinates();
        String name6 = "The Kitchn";
        String rcp6 = "https://www.thekitchn.com/search?q="+objects[0]+"&filter=recipes";
        String url6 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiUDJMmhx57ggx8CQvqLx6Cl5GvrPF9qAKwsOTyFdI5IjWFS7C";
        Coordinates cd6=new Coordinates();
        String displayedPhone = "12345";
*/
        String url = "https://www.allrecipes.com/search/results/?wt=" + objects[0] + "&sort=re";
        String url1 = "https://www.foodnetwork.com/search/" + objects[0] + "-";
        String[] Name = new String[10];
        String[] Rcp = new String[10];
        String[] Url = new String[10];
        String[] Rate = new String[10];

        Log.d("Recipe", "Current File: 1" );
        try {
            Document document = Jsoup.connect(url).get();
            Elements recipe = document.select(".fixed-recipe-card");
            for (int i = 0; i < 8; i++){
                Name[i] = recipe.get(i).select(".fixed-recipe-card__title-link").get(0).text();
                Url[i] = recipe.get(i).select(".fixed-recipe-card__img").attr("data-original-src");
                String rate = recipe.get(i).select(".fixed-recipe-card__ratings").get(0).select("span").get(0).attr("data-ratingstars");
                if(rate.length()>4){rate = rate.substring(0,4);}
                Rate[i] = rate;
                Rcp[i] = recipe.get(i).select("a").get(0).attr("abs:href");
            }
            /*
            Log.d("Recipe", "Current File: 2" );
            Document document1 = Jsoup.connect(url1).get();
            Log.d("Recipe", "Current File: 3" );
            Elements recipe1 = document1.select(".o-SearchStatistics").get(0).select(".o-RecipeResult o-ResultCard");
            Log.d("Recipe", "Current File: 4" );
            for (int i = 0; i < 5; i++){
                Log.d("Recipe", recipe1.get(i).toString());
                Name[i+5] = recipe1.get(i).select(".m-MediaBlock__m-MediaWrap").get(0).select("a").get(0).attr("title");
                Log.d("Recipe", Name[i+5]);
                Url[i+5] = recipe1.get(i).select(".m-MediaBlock__m-MediaWrap").get(0).select("a").get(0).select("img").get(0).attr("src");
                Log.d("Recipe", Url[i+5]);
                String rate = recipe1.get(i).select(".m-MediaBlock__m-Rating ").get(0).select("a").get(0).select("span").get(0).attr("title");
                if(rate.length()>3){rate = rate.substring(0,3);}
                Rate[i+5] = rate;
                Log.d("Recipe", Rate[i+5]);
                Rcp[i+5] = recipe1.get(i).select(".m-MediaBlock__m-MediaWrap").get(0).select("a").get(0).attr("abs:href");
                Log.d("Recipe", Rcp[i+5]);
            }*/
            for (int i = 0; i < 8; i++){
                restaurantList.add( new Restaurant( Name[i], Rate[i], Url[i], Rcp[i], new Coordinates()) );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        restaurantList.add( new Restaurant( name1, rcp1.replaceAll( "\\s+","%20" ), url1, displayedPhone,cd1) );//use rating=1 and prepTime=2 for now and change later
        restaurantList.add( new Restaurant( name2, rcp2.replaceAll( "\\s+","%20" ), url2, displayedPhone ,cd2) );
        restaurantList.add( new Restaurant( name3, rcp3.replaceAll( "\\s+","%20" ), url3, displayedPhone,cd3 ) );
        restaurantList.add( new Restaurant( name4, rcp4.replaceAll( "\\s+","%20" ), url4, displayedPhone,cd4 ) );//use rating=1 and prepTime=2 for now and change later
        restaurantList.add( new Restaurant( name5, rcp5.replaceAll( "\\s+","%20" ), url5, displayedPhone ,cd5) );
        restaurantList.add( new Restaurant( name6, rcp6.replaceAll( "\\s+","%20" ), url6, displayedPhone,cd6 ) );
*/



        return restaurantList;

    }


    @Override
    protected void onPostExecute(ArrayList o) {
        super.onPostExecute(o);

        RestaurantListAdapter adapter = new RestaurantListAdapter(mContext, R.layout.adapter_view_layout, o);
        mList.setAdapter(adapter);


    }



}
