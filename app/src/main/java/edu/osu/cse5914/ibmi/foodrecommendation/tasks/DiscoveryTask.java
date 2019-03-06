package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.util.List;

import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;


public class DiscoveryTask extends AsyncTask<String, Void, String> {
    protected Button mText;
    protected String foodCal;
    protected Discovery mDiscovery;
    protected String environmentId;
    protected String collectionId;
    protected TextView mDiscoveryView;

    public DiscoveryTask(String food, Button tv,TextView mDiscoveryView) {
        mText = tv;
        this.mDiscoveryView = mDiscoveryView;
        foodCal=food;
        IamOptions options = new IamOptions.Builder()
                .apiKey(ProjectApi.DISCOVERY_API2)
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
        builder.query(foodCal+" suggestion");
        QueryResponse result = mDiscovery.query(builder.build()).execute();
        Log.d("Discovery", result.toString());
        List<QueryResult> resultList = result.getResults();
        String url = "https://www.google.com/search?q="+foodCal+"+food+suggestion&ei=zxqAXIvAIMfojgSD6I3YBA&start=10&sa=N&ved=0ahUKEwjL4-TIm-7gAhVHtIMKHQN0A0sQ8NMDCJ0C&biw=1322&bih=711";
        if (resultList.size()>0) {
            url = resultList.get(0).get("url").toString();
        }
        mText.setText("Discover it!");
        return url;
    }
    @Override
    protected void onProgressUpdate(Void[] values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        mText.setEnabled(true);
        mDiscoveryView.setText(o);
    }

}
