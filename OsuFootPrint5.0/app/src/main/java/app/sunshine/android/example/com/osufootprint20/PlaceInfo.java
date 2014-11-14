package app.sunshine.android.example.com.osufootprint20;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 陈英硕 on 2014/11/11.
 */
public class PlaceInfo {
    private String name;
    private LatLng location;

    public PlaceInfo(String placeName, LatLng placeLocation) {
        name=placeName;
        location=placeLocation;
    }

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }
}
