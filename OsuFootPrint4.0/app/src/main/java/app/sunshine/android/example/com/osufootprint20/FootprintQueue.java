package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.UUID;


public class FootprintQueue {

    private PriorityQueue<Footprint> mySet;


    private static FootprintQueue mFootprintQueue;
    public FootprintQueue() {
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
        mySet.add(new Footprint("OSU Stadium","2014-10-25","S1","10"));
        mySet.add(new Footprint("OSU Stadium","2014-10-09","S2","11"));
        mySet.add(new Footprint("OSU Stadium","2014-10-04","S3","12"));
        mySet.add(new Footprint("Tuttle Park","2014-10-18","T1","13"));
        mySet.add(new Footprint("Tuttle Park","2014-10-02","T2","14"));
        mySet.add(new Footprint("OSU Medical Center","2014-10-28","M1","15"));
        mySet.add(new Footprint("OSU Medical Center","2014-9-20","M2","16"));
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

