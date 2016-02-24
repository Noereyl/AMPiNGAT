package com.ampingat.ampingatapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ampingat.ampingatapplication.listener.RoutesListener;
import com.ampingat.ampingatapplication.models.ThirdFloorRoutes;

import java.util.ArrayList;

/**
 * Created by Joy Rivera on 2/21/2016.
 */
public class DBHandler extends SQLiteOpenHelper implements RoutesListener {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "RoutesDatabase.db";
    private static final String TABLE_NAME = "thirdfloorroutes";
    private static final String KEY_ID = "_id";
    private static final String KEY_ROOM = "_room";
    private static final String KEY_SHORTEST_ROUTE = "_shortroute";
    private static final String KEY_SECOND_ROUTE = "_secondroute";
    private static final String KEY_THIRD_ROUTE = "_thirdroute";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+ KEY_ROOM +" TEXT,"+ KEY_SHORTEST_ROUTE +" TEXT,"+ KEY_SECOND_ROUTE +" TEXT,"+ KEY_THIRD_ROUTE +" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void addRoutes(ThirdFloorRoutes thirdFloorRoutes) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_ROOM, thirdFloorRoutes.getRoom());
            values.put(KEY_SHORTEST_ROUTE, thirdFloorRoutes.getShortRoute());
            values.put(KEY_SECOND_ROUTE,thirdFloorRoutes.getSecondRoute());
            values.put(KEY_THIRD_ROUTE, thirdFloorRoutes.getThirdRoute());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem", e + "");
        }
    }

    public String getRoute() {

        String routeresult = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT _shortroute, _secondroute, _thirdroute  FROM "+TABLE_NAME + "WHERE _room LIKE" + KEY_ROOM;
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                routeresult = cursor.getString(0) + cursor.getString(1) + cursor.getString(2) ;
            } while (cursor.moveToNext());
        }
        return routeresult;
    }


    @Override
    public ArrayList<ThirdFloorRoutes> getAllRoutes() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ThirdFloorRoutes> routesList = null;
        try{
            routesList = new ArrayList<ThirdFloorRoutes>();
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    ThirdFloorRoutes routes = new ThirdFloorRoutes();
                    routes.setId(cursor.getInt(0));
                    routes.setRoom(cursor.getString(1));
                    routes.setShortRoute(cursor.getString(2));
                    routes.setSecondRoute(cursor.getString(3));
                    routes.setThirdRoute(cursor.getString(4));
                    routesList.add(routes);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return routesList;
    }

    @Override
    public int getRoutesCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
