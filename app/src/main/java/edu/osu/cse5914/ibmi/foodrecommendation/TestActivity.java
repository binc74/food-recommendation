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
    private TextView mTextView;
    private TextView mDiscoveryView;
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

        mDiscoveryView = findViewById(R.id.textView8);
        mDiscoveryView.setVisibility(View.GONE);
        mImage = findViewById(R.id.image);
        mTextView = findViewById( R.id.tv_visual_rec );
        mDiscoveryButton = findViewById(R.id.DiscoveryButton);
        mDiscoveryButton.setOnClickListener(this);
        mDiscoveryButton.setEnabled(false);
        mReport = findViewById(R.id.report);
        mReport.setOnClickListener(this);
        Bitmap image = BitmapFactory.decodeFile(filePath);
        Log.d(TAG, "file size : " + image.getByteCount());
        mImage.setImageBitmap(image);

        // Compress bitmap
        Bitmap compressedImage = Bitmap.createScaledBitmap(image, 300, 300,true);
        Log.d(TAG, "" + image.getByteCount() + " : " + compressedImage.getByteCount());


        new VisualRecTask(mTextView,mDiscoveryButton,mDiscoveryView).execute(filePath);
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
                i.setData(Uri.parse(mDiscoveryView.getText().toString()));
                startActivity(i);
                break;

        }

    }
}
