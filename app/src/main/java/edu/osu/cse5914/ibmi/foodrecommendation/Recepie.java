package edu.osu.cse5914.ibmi.foodrecommendation;

public class Recepie {

    private String name;
    private String  rating;
    private String prepTime;
    private String imgUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public Recepie(String name, String rating, String prepTime, String imgUrl) {
        this.rating = rating;
        this.name = name;
        this.prepTime = prepTime;
        this.imgUrl=imgUrl;
    }



    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getPrepTime() {

        return prepTime;
    }


    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
