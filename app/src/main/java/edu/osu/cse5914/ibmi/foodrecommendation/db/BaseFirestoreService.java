package edu.osu.cse5914.ibmi.foodrecommendation.db;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import edu.osu.cse5914.ibmi.foodrecommendation.model.BaseModel;

public abstract class BaseFirestoreService {
    protected FirebaseFirestore db;

    public BaseFirestoreService() {
        db = FirebaseFirestore.getInstance();
    }

    protected abstract String getTAG();

    protected abstract String getCollection();

    protected OnSuccessListener<Void> getDefaultOnSuccessListener() {
        return new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(getTAG(), "DocumentSnapshot successfully handled");
            }
        };
    }

    protected OnSuccessListener<DocumentReference> getDefaultDocRefOnSuccessListener() {
        return new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(getTAG(), "DocumentSnapshot added with ID: " + documentReference.getId());
            }
        };
    }

    protected OnFailureListener getDefaultOnFailureListener() {
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(getTAG(), "Error when interacting with Firestore", e);
            }
        };
    }

    protected void addDocument(BaseModel data,
                               OnSuccessListener<Void> onSuccessListener,
                               OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .set(data)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void addDocument(BaseModel data,
                               OnSuccessListener<Void> onSuccessListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .set(data)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(getDefaultOnFailureListener());
    }

    protected void addDocument(BaseModel data) {
        addDocument(data, getDefaultOnSuccessListener(), getDefaultOnFailureListener());
    }

    protected void setDocument(BaseModel data, OnSuccessListener<Void> onSuccessListener,
                               OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .set(data)
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void setDocument(BaseModel data) {
        setDocument(data, getDefaultOnSuccessListener(), getDefaultOnFailureListener());
    }

    protected void getDocument(BaseModel data,
                               OnCompleteListener<DocumentSnapshot> onCompleteListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .get()
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(getDefaultOnFailureListener());
    }

    protected void getDocument(BaseModel data,
                               OnCompleteListener<DocumentSnapshot> onCompleteListener,
                               OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .get()
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void getDocument(String id,
                               OnCompleteListener<DocumentSnapshot> onCompleteListener,
                               OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(id)
                .get()
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void getAllDocuments(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        getAllDocuments(onCompleteListener, getDefaultOnFailureListener());
    }

    protected void getAllDocuments(OnCompleteListener<QuerySnapshot> onCompleteListener,
                                   OnFailureListener onFailureListener) {
        db.collection(getCollection())
                .get()
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void deleteDocument(BaseModel data) {
        deleteDocument(data, getDefaultOnSuccessListener(), getDefaultOnFailureListener());
    }

    protected void deleteDocument(BaseModel data, OnSuccessListener<Void> onSuccessListener,
                                  OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(data.getDocumentId())
                .delete()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void deleteDocumentById(String id, OnSuccessListener<Void> onSuccessListener,
                                      OnFailureListener onFailureListener) {
        db.collection(getCollection()).document(id)
                .delete()
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);
    }

    protected void deleteDocumentById(String id) {
        deleteDocumentById(id, getDefaultOnSuccessListener(), getDefaultOnFailureListener());
    }
}
