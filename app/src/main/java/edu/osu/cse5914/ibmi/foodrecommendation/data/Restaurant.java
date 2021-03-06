package edu.osu.cse5914.ibmi.foodrecommendation.data;

import com.yelp.fusion.client.models.Coordinates;

public class Restaurant {

    private String name;
    private String imgUrl;

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    private Coordinates coord;

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    private String displayPhone;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String url) {
        this.imgUrl = url;
    }

    public Restaurant(String name, String rating,  String imgUrl,String displayPhone,Coordinates coord) {
        this.name = name;
        this.imgUrl=imgUrl;
        this.rating=rating;
        this.displayPhone=displayPhone;
        this.coord=coord;
    }






    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImgurl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
