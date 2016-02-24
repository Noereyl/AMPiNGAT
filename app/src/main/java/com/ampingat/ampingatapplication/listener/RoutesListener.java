package com.ampingat.ampingatapplication.listener;

import com.ampingat.ampingatapplication.models.ThirdFloorRoutes;

import java.util.ArrayList;

/**
 * Created by Joy Rivera on 2/21/2016.
 */
public interface RoutesListener {

    public void addRoutes(ThirdFloorRoutes thirdFloorRoutes);

    public ArrayList<ThirdFloorRoutes> getAllRoutes();

    public int getRoutesCount();
}
