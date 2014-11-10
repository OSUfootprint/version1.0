package app.sunshine.android.example.com.osufootprint20;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class MyPlaceQueue {

    public PriorityQueue mySet;

    public MyPlaceQueue() {
        mySet=new PriorityQueue();
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
}

