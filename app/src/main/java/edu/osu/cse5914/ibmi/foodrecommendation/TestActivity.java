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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.osu.cse5914.ibmi.foodrecommendation.db.MealService;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.NutrionixTask;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.VisualRecTask;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "TestActivity";
    private ImageView mImage;
    private Button mReport;
    private Button mDiscoveryButton;
    private EditText mTextView;
    private TextView mDiscoveryView;
    // private VisualRecTask visualRecTask;
    private String filePath;
    private String uid;

    //private VisualRecognition VisualRecor;
    //private VisualRecTask visualRecTaskTask;

    private MealService mealService;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle extra = getIntent().getExtras();
        filePath = extra.getString("imagePath");
        uid = extra.getString("uid");

        mealService = new MealService();
        userService = new UserService();
        userService.getDocumentReference(uid);

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
        //Bitmap compressedImage = Bitmap.createScaledBitmap(image, 300, 300,true);
        //Log.d(TAG, "" + image.getByteCount() + " : " + compressedImage.getByteCount());


        new VisualRecTask(mTextView,mDiscoveryButton,mDiscoveryView).execute(filePath);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:
                String fc = EditTextUtil.getString(mTextView);
                Meal m = new Meal(fc);
                String cal="0",minCalAllowed,maxCalAllowed;
                new NutrionixTask(m,this).execute();



                float flt_cal=Float.parseFloat(cal);
                int a=2;
                if (flt_cal>800) {
                    minCalAllowed = "0.5";
                    maxCalAllowed = "1.0";
                }
                else if(200<flt_cal&&flt_cal<800){
                    minCalAllowed = "1.0";
                    maxCalAllowed = "4.0";
                }
                else{

                    minCalAllowed="4";
                    maxCalAllowed = "8";}
                ArrayList<String> min_max_cal=new ArrayList<String>();
                min_max_cal.add(minCalAllowed);
                min_max_cal.add(maxCalAllowed);


                mealService.addNewMeal(m, task -> {
                    String mid = task.getId();

                    userService.updateHistory(mid);
                });

//                pref_intent.putExtra("food_category", fc);
//                pref_intent.putStringArrayListExtra("min_max", min_max_cal);

                break;
            case R.id.DiscoveryButton:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mDiscoveryView.getText().toString()));
                startActivity(i);
                break;


        }

    }
}
