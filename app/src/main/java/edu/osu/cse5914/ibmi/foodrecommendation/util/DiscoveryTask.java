package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;

public class DiscoveryTask {
    protected TextView mText;
    protected Discovery mDiscovery;

    public DiscoveryTask(TextView tv){
        mText = tv;




    }

}
