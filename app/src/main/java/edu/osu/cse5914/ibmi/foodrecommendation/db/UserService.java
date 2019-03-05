package edu.osu.cse5914.ibmi.foodrecommendation.db;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    public void processUser(User user, OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        getDocument(user, onCompleteListener);
    }

    public void addNewUser(User user) {
        addDocument(user, aVoid -> {
            Log.d(TAG, user.getDocumentId() + " successfully written!");
        });
    }

    public void addNewUser(User user, OnSuccessListener<Void> onSuccessListener) {
        addDocument(user, onSuccessListener);
    }
}
