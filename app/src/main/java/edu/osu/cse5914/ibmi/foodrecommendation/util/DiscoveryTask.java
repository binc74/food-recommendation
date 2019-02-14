package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import org.json.JSONArray;
import org.json.JSONObject;



public class DiscoveryTask extends AsyncTask<String, Void, String> {
    protected TextView mText;
    protected String foodCal;
    protected Discovery mDiscovery;
    protected String environmentId;
    protected String collectionId;

    public DiscoveryTask(String food, TextView tv) {
        mText = tv;
        foodCal=food;
        IamOptions options = new IamOptions.Builder()
                .apiKey("1YJgxYDcHqAH75k-Q3Z1_LKMegZa30d4gKAGEr_hiKKA")
                .build();
        mDiscovery = new Discovery("2018-12-03",options);
        mDiscovery.setEndPoint("https://gateway.watsonplatform.net/discovery/api");

        mDiscovery.setIamCredentials(options);
        environmentId = "system";
        collectionId = "news-en";

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... objects) {
        QueryOptions.Builder builder = new QueryOptions.Builder(environmentId, collectionId);
        builder.query(foodCal+" unhealthy");
        QueryResponse result = mDiscovery.query(builder.build()).execute();
        String str = result.getResults().get(0).get("title").toString();
            Log.d("DiscoveryTask",str);
        return str;
    }
    @Override
    protected void onProgressUpdate(Void[] values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        mText.setText(o);
    }

}
