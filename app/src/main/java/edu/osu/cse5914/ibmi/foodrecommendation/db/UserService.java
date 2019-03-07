package edu.osu.cse5914.ibmi.foodrecommendation.db;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import edu.osu.cse5914.ibmi.foodrecommendation.model.User;

public class UserService extends BaseFirestoreService {
    private String TAG = "UserService";
    private String COLLECTION = "User";

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    protected String getCollection() {
        return COLLECTION;
    }

    public static User getUserFromDocument(DocumentSnapshot ds) {
        return ds.toObject(User.class);
    }

    public void processUser(User user, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getDocument(user, onCompleteListener);
    }

    public void processUserById(String uid, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getDocument(uid, onCompleteListener);
    }

    public void addNewUser(User user) {
        addDocument(user, aVoid -> {
            Log.d(TAG, user.getDocumentId() + " successfully written!");
        });
    }

    public void addNewUser(User user, OnSuccessListener<Void> onSuccessListener) {
        addDocument(user, onSuccessListener);
    }

    public void updateNeedInit(boolean newNeedInit) {
        dr.update("needinit", newNeedInit);
    }

    public void updateBirthday(String birthday) {
        dr.update("birthday", birthday);
    }

    public void updateGender(int gender) {
        dr.update("gender", gender);
    }

    public void updateWeight(float weight) {
        dr.update("weight", weight);
    }

    public void updateDietOpt(int dietOpt) {
        dr.update("dietoption", dietOpt);
    }

    public void updateHealthOpt(int healthOpt) {
        dr.update("healthoption", healthOpt);

    }
}
