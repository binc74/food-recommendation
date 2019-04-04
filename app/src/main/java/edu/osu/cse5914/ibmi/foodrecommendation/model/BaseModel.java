package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.Exclude;

public class BaseModel {
    private String documentId;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
