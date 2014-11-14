package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by 陈英硕 on 2014/11/11.
 */
public class WishQueue {
    private static ArrayList<MyPlace> mySet;
    private Context mAppContext;
    private static final int REQUEST_PLACE = 1;

    public WishQueue(Context c) {
        mAppContext=c;
        mySet=new ArrayList<MyPlace>();
        mySet.add(new MyPlace("Drease Lab",new LatLng(40.002257134, -83.015840873)));
        mySet.add(new MyPlace("Thompson Library",new LatLng(39.999223928, -83.0149433389306)));
        mySet.add(new MyPlace("OSU Stadium",new LatLng(40.002473382, -83.019639887)));
        mySet.add(new MyPlace("Tuttle Park",new LatLng(40.012186920, -83.01550258)));
        mySet.add(new MyPlace("OSU Medical Center",new LatLng(40.00099584, -83.03252656)));
    }

    public ArrayList getMySet() {
        return mySet;
    }

    public void insert(MyPlace mp) {
        mySet.add(0,mp);
    }

    public int size() {
        return mySet.size();
    }

    public MyPlace getByName(String placeName) {

        for (MyPlace mp:mySet) {
            if (mp.getName().equals(placeName))
                return mp;
        }
        return null;
    }

    public void setMySet(ArrayList wish) {
        mySet=new ArrayList<MyPlace>(wish);
    }

    public void deleteWish(String placeName) {

        int i=0;
        for (MyPlace mp:mySet) {
            if (mp.getName().equals(placeName)) {
                mySet.remove(i);
                return;
            }
            i++;
        }
    }

}
