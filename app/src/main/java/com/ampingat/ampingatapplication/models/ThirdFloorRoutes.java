package com.ampingat.ampingatapplication.models;

/**
 * Created by Joy Rivera on 2/21/2016.
 */
public class ThirdFloorRoutes {
    private int id;
    private String room;
    private String shortRoute;
    private String secondRoute;
    private String thirdRoute;

    public ThirdFloorRoutes() {
    }

    public ThirdFloorRoutes(String room, String shortR, String secondR, String thirdR) {
        this.room = room;
        this.shortRoute = shortR;
        this.secondRoute = secondR;
        this.thirdRoute = thirdR;
    }

    public ThirdFloorRoutes(int id, String room, String shortR, String secondR, String thirdR) {
        this.id = id;
        this.room = room;
        this.shortRoute = shortR;
        this.secondRoute = secondR;
        this.thirdRoute = thirdR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room= room;
    }

    public String getShortRoute() {
        return shortRoute;
    }

    public void setShortRoute(String shortRoute) {
        this.shortRoute = shortRoute;
    }

    public String getSecondRoute() {
        return secondRoute;
    }

    public void setSecondRoute(String secondRoute) {
        this.secondRoute = secondRoute;
    }

    public String getThirdRoute() {
        return thirdRoute;
    }

    public void setThirdRoute(String thirdRoute) {
        this.thirdRoute = thirdRoute;
    }



}
