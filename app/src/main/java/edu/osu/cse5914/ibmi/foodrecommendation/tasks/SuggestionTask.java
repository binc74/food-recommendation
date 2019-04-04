package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.adapters.RecepieListAdapter;

//referenced website: https://www.codexpedia.com/android/asynctask-and-httpurlconnection-sample-in-android/
public class SuggestionTask extends AsyncTask<ArrayList, Void, ArrayList> {
    protected TextView mText;
    protected ListView mList;
    private final Context mContext;
    protected String maxCal;
    protected String minCal;


    public SuggestionTask(ListView lv, Context ct, String maxcal,String mincal ){
        mList = lv;
        mContext = ct;
        maxCal=maxcal;
        minCal=mincal;


    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Recepie> doInBackground(ArrayList... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";
        ArrayList<Recepie> recepieList = new ArrayList<>();


        try {




            //remember to only search those with images
//            URL url = new URL("http://api.myjson.com/bins/xu8g0");
            URL url = new URL("http://api.yummly.com/v1/api/recipes?_app_id=1818c65c&_app_key=a20fe4e4dbb576f4e146ad953f72bacc&nutrition.ENERC_KCAL.max="+maxCal+"&nutrition.ENERC_KCAL.min="+minCal+"&allowedCourse[]=course^course-Main Dishes");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            recipieJsonStr = buffer.toString();
            JSONObject jsreader = new JSONObject(recipieJsonStr);
            JSONArray rcpArr = jsreader.getJSONArray("matches");


            for(int i =0 ;i <= rcpArr.length(); i++){
                JSONObject JO = (JSONObject) rcpArr.get(i);


//                single =  "Recepie Name:" + JO.get("recipeName") + "\n";
                String rating=JO.get("rating").toString();
                String name=JO.get("recipeName").toString();
                String prepTime=JO.get("totalTimeInSeconds").toString();
                int intSec=Integer.parseInt(prepTime);
                int intMin=intSec/60;
                prepTime=Integer.toString(intMin);
                String imgUrl= JO.getJSONArray("smallImageUrls").get(0).toString();


//                String rating= JO.get()
//                rcpParsed = rcpParsed + single +"\n" ;
                recepieList.add( new Recepie( name, rating, prepTime, imgUrl ) );//use rating=1 and prepTime=2 for now and change later


            }


        }
        catch (IOException e){
            e.printStackTrace();
            return null;

        }

        catch (JSONException e) {
            e.printStackTrace();
        }
        return recepieList;

    }

    @Override
    protected void onPostExecute(ArrayList o) {
        super.onPostExecute(o);

        RecepieListAdapter adapter = new RecepieListAdapter(mContext, R.layout.adapter_view_layout, o);
        mList.setAdapter(adapter);


    }




}