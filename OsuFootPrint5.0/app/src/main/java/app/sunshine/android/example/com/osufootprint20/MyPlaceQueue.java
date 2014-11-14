package app.sunshine.android.example.com.osufootprint20;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;
import java.util.PriorityQueue;


public class MyPlaceQueue {

    private static PriorityQueue<MyPlace> mySet;
    private Context mAppContext;

    public MyPlaceQueue(Context c) {
        mAppContext=c;
        mySet=new PriorityQueue();
        mySet.add(new MyPlace("Drease Lab",new LatLng(40.002257134, -83.015840873),5));
        mySet.add(new MyPlace("Thompson Library",new LatLng(39.999223928, -83.0149433389306),4));
        mySet.add(new MyPlace("OSU Stadium",new LatLng(40.002473382, -83.019639887),3));
        mySet.add(new MyPlace("Tuttle Park",new LatLng(40.012186920, -83.01550258),2));
        mySet.add(new MyPlace("OSU Medical Center",new LatLng(40.00099584, -83.03252656),2));
    }

    public PriorityQueue getMySet() {
        return mySet;
    }

    public void insert(MyPlace mp) {
        mySet.add(mp);
    }

    public int size() {
        return mySet.size();
    }

    public MyPlace getNext() {
        if (mySet.size()==0) {
            return null;
        } else {
            MyPlace o=(MyPlace)mySet.poll();
            return o;
        }
    }

    public void TimesChanged (String placeName) {

        for (MyPlace mp:mySet) {
            if (mp.getName().equals(placeName)) {
                mp.addTimes();
                return;
            }
        }
        MyPlace newPlace=new MyPlace(placeName,PlaceInfoQueue.get(mAppContext).findLocByName(placeName),1);
        insert(newPlace);
    }
}

