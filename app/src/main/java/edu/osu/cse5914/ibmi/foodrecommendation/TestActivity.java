package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;

import org.w3c.dom.Text;

import java.io.File;

import edu.osu.cse5914.ibmi.foodrecommendation.util.DiscoveryTask;
import edu.osu.cse5914.ibmi.foodrecommendation.util.VisualRecTask;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "TestActivity";

    private ImageView mImage;
    private Button mButton;
    private Button mReport;
    private TextView mTextView;
    private TextView mDiscovery;
    private VisualRecTask visualRecTask;
    private String filePath;

    //private VisualRecognition VisualRecor;
    //private VisualRecTask visualRecTaskTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        filePath = getIntent().getStringExtra("imagePath");
        Log.d(TAG, "get path1: " + filePath);


        mImage = findViewById(R.id.image);
        mTextView = findViewById( R.id.tv_visual_rec );
        mDiscovery = findViewById(R.id.tv_visual_rec2);
        mButton = findViewById( R.id.btn_visualrec );
        mButton.setOnClickListener(this);
        mReport = findViewById(R.id.report);
        mReport.setOnClickListener(this);

        Bitmap image = BitmapFactory.decodeFile(filePath);

        //new File(filePath).delete();

        Log.d(TAG, "file size : " + image.getByteCount());
        mImage.setImageBitmap(image);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:
                Intent pref_intent = new Intent(this, MainActivity.class);
                pref_intent.putExtra("id",mTextView.getText().toString());
                //link to preference view
                startActivity(pref_intent);
            case R.id.btn_visualrec:
                // String filePath = "file:" + getIntent().getStringExtra("imagePath");
                Log.d(TAG, "get path: " + filePath);
                new VisualRecTask( mTextView,mDiscovery ).execute(filePath);
               // new File(filePath).delete();
        }
    }
}
