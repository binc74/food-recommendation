package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.osu.cse5914.ibmi.foodrecommendation.tasks.VisualRecTask;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "TestActivity";

    private ImageView mImage;
    private Button mReport;
    private Button mDiscoveryButton;
    private TextView mTextView ;
   // private VisualRecTask visualRecTask;
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
        mDiscoveryButton = findViewById(R.id.DiscoveryButton);
        mDiscoveryButton.setOnClickListener(this);
        mDiscoveryButton.setEnabled(false);
        //mDiscovery.setMovementMethod(new ScrollingMovementMethod());
        //mButton = findViewById( R.id.btn_visualrec );
        //mButton.setOnClickListener(this);
        mReport = findViewById(R.id.report);
        mReport.setOnClickListener(this);

        Bitmap image = BitmapFactory.decodeFile(filePath);

        //new File(filePath).delete();



        Log.d(TAG, "file size : " + image.getByteCount());
        mImage.setImageBitmap(image);
        new VisualRecTask( mTextView,mDiscoveryButton ).execute(filePath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:

                Intent pref_intent = new Intent(this, MainActivity.class); //link to preference view
                pref_intent.putExtra("food_category", mTextView.getText().toString());
                startActivity(pref_intent);
                break;
            case R.id.DiscoveryButton:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mDiscoveryButton.getText().toString()));
                startActivity(i);
                break;

        }

    }
}
