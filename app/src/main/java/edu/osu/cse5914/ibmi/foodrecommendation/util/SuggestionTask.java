package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//referenced website: https://www.codexpedia.com/android/asynctask-and-httpurlconnection-sample-in-android/
public class SuggestionTask extends AsyncTask<String, Void, String> {
    protected TextView mText;

    public SuggestionTask(TextView tv){
        mText = tv;


    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";

        try {


            URL url = new URL("http://api.myjson.com/bins/xu8g0");
//           URL url = new URL("http://api.yummly.com/v1/api/recipes?_app_id=1818c65c&_app_key=a20fe4e4dbb576f4e146ad953f72bacc&nutrition.K.min=3&nutrition.K.max=3.5");

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

            for(int i =0 ;i <rcpArr.length(); i++){
                JSONObject JO = (JSONObject) rcpArr.get(i);


                single =  "Recepie Name:" + JO.get("recipeName") + "\n";


                rcpParsed = rcpParsed + single +"\n" ;


            }


        }
        catch (IOException e){
            e.printStackTrace();
            return null;

        }

        catch (JSONException e) {
            e.printStackTrace();
        }
        return rcpParsed;

    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        mText.setText(o);
        Log.i("json", o);
    }




}
