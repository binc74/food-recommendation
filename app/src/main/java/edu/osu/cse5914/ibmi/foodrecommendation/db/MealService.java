package edu.osu.cse5914.ibmi.foodrecommendation.db;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;

public class MealService extends BaseFirestoreService {
    private String TAG = "UserService";
    private String COLLECTION = "Meal";

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected String getCollection() {
        return COLLECTION;
    }

    public static Meal getMealFromDocument(DocumentSnapshot ds) {
        return ds.toObject(Meal.class);
    }

    public void processMeal(Meal meal, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getDocument(meal, onCompleteListener);
    }

    public void processMealById(String uid, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getDocument(uid, onCompleteListener);
    }

    public void addNewMeal(Meal meal) {
        addDocumentWithoutId(meal, aVoid -> {
            Log.d(TAG, meal.getDocumentId() + " successfully written!");
        });
    }

    public void addNewMeal(Meal meal, OnSuccessListener<DocumentReference> onSuccessListener) {
        addDocumentWithoutId(meal, onSuccessListener);
    }

    public void updateFood(String food) {
        dr.update("history", FieldValue.arrayUnion(food));
    }
}
