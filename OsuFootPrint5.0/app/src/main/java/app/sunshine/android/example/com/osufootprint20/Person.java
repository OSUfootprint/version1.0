package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 14/11/3.
 */
public class Person {
    private int _id;
    private String name;
    private String password;
    private FootprintQueue mFootprintQueue;
    private MyPlaceQueue mPlaceQueue;
    public Context mAppContext;

    private static Person mPerson;

    public FootprintQueue getFootprint() {
        if (mFootprintQueue == null) {
            mFootprintQueue = new FootprintQueue(mAppContext);
        }
        return mFootprintQueue;
    }

    public MyPlaceQueue getMyPlace() {
        if (mPlaceQueue == null) {
            mPlaceQueue = new MyPlaceQueue(mAppContext);
        }
        return mPlaceQueue;
    }

    public static Person getPerson(Context c) {
        if (mPerson == null) {
            mPerson = new Person(c.getApplicationContext());
        }
        return mPerson;
    }

    public Person(Context c){
        mAppContext=c;
        this.name = "";
        this.password = "";
        mFootprintQueue=new FootprintQueue(mAppContext);
        mPlaceQueue=new MyPlaceQueue(mAppContext);
    }
    public Person(Context c,String name, String password){
        //this._id = _id;
        mAppContext=c;
        this.name = name;
        this.password = password;
        mFootprintQueue=new FootprintQueue(mAppContext);
        mPlaceQueue=new MyPlaceQueue(mAppContext);
    }

    public int get_id(){
        return _id;
    }
    public void set_id(int _id){
        this._id = _id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
