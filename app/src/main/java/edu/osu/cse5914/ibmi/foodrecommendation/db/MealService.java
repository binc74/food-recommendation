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

    public void updateCalorie(float calorie)  {
        dr.update("calorie", calorie);
    }

    public void updateFat(float fat)  {
        dr.update("fat", fat);
    }

    public void updateCholesterol(float cholesterol)  {
        dr.update("cholesterol", cholesterol);
    }

    public void updateSodium(float sodium)  {
        dr.update("sodium", sodium);
    }

    public void updateProtein(float protein)  {
        dr.update("protein", protein);
    }



    public void setDocumentId(String uid) { dr.update("documentId", uid); }
}
