package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by 陈英硕 on 2014/11/11.
 */
public class PlaceInfoQueue {

    private ArrayList<PlaceInfo> mySet;

    private static PlaceInfoQueue mPlaceInfoQueue;

    public Context mAppContext;

    public static PlaceInfoQueue get(Context c) {
        if (mPlaceInfoQueue == null) {
            mPlaceInfoQueue = new PlaceInfoQueue(c.getApplicationContext());
        }
        return mPlaceInfoQueue;
    }

    public ArrayList getMySet() {
        return mySet;
    }

    public int size() {
        return mySet.size();
    }

    public PlaceInfoQueue(Context c)
    {
        mySet=new ArrayList<PlaceInfo>();
        mySet.add(new PlaceInfo("Drease Lab",new LatLng(40.002257134, -83.015840873)));
        mySet.add(new PlaceInfo("Thompson Library",new LatLng(39.999223928, -83.0149433389306)));
        mySet.add(new PlaceInfo("OSU Stadium",new LatLng(40.002473382, -83.019639887)));
        mySet.add(new PlaceInfo("Tuttle Park",new LatLng(40.012186920, -83.01550258)));
        mySet.add(new PlaceInfo("OSU Medical Center",new LatLng(40.00099584, -83.03252656)));
    }

    public LatLng findLocByName(String placeName) {

        for (PlaceInfo pi:mySet) {
            if (pi.getName().equals(placeName))
                return pi.getLocation();
        }
        return null;
    }
}
