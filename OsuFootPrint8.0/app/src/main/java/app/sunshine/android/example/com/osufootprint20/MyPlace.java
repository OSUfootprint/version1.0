package app.sunshine.android.example.com.osufootprint20;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 陈英硕 on 2014/11/4.
 */
public class MyPlace implements Comparable{

    private String name;
    private LatLng location;
    private int times;

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }

    public int getTimes() {
        return times;
    }
    public void addTimes() {times++;}

    public MyPlace(String n, LatLng l, int t) {
        name=n;
        location=l;
        times=t;
    }

    public MyPlace(String n, LatLng l) {
        name=n;
        location=l;
        times=0;
    }

    public MyPlace()
    {
        name=null;
        location=null;
        times=0;
    }

    @Override
    public int compareTo(Object s) {
        MyPlace mp=(MyPlace) s;
        if (times<mp.getTimes()) {
            return 1;
        } else if (times==mp.getTimes()) {
            return name.compareTo(mp.getName());
        } else {
            return -1;
        }
    }
}
