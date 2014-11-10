package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.UUID;


public class FootprintQueue {

    private PriorityQueue<Footprint> mySet;


    private static FootprintQueue mFootprintQueue;
    private Context mAppContext;
    private FootprintQueue(Context appContext) {
        mAppContext = appContext;
        mySet=new PriorityQueue<Footprint>();
        mySet.add(new Footprint("Drease Lab", "2014-11-02", "D1", "1"));
        mySet.add(new Footprint("Drease Lab","2014-10-31","D2","2"));
        mySet.add(new Footprint("Drease Lab","2014-10-27","D3","3"));
        mySet.add(new Footprint("Drease Lab","2014-10-15","D4","4"));
        mySet.add(new Footprint("Drease Lab","2014-10-14","D5","5"));
        mySet.add(new Footprint("Thompson Library","2014-10-26","T1","6"));
        mySet.add(new Footprint("Thompson Library","2014-10-22","T2","7"));
        mySet.add(new Footprint("Thompson Library","2014-10-13","T3","8"));
        mySet.add(new Footprint("Thompson Library","2014-10-10","T4","9"));
        mySet.add(new Footprint("OSU Stadium","2014-10-25","O1","10"));
        mySet.add(new Footprint("OSU Stadium","2014-10-09","O2","11"));
        mySet.add(new Footprint("OSU Stadium","2014-10-04","O3","12"));
    }
    public static FootprintQueue get(Context c) {
        if (mFootprintQueue == null) {
            mFootprintQueue = new FootprintQueue(c.getApplicationContext());
        }
        return mFootprintQueue;
    }

    public PriorityQueue getMySet() {
        return mySet;
    }
    public void insert(Footprint fp) {
        mySet.add(fp);
    }

    public int size() {
        return mySet.size();
    }

    public Footprint getNext() {
        if (mySet.size()==0) {
            return null;
        } else {
            Footprint o=(Footprint)mySet.poll();
            return o;
        }
    }

    public Footprint getByID(UUID id) {

        PriorityQueue<Footprint> pq=new PriorityQueue<Footprint>(mySet);
        for (Footprint fp:mySet) {
            if (fp.getID().equals(id))
                return fp;
        }
        return null;
    }
}

