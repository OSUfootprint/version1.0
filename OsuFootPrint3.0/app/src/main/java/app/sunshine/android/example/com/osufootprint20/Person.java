package app.sunshine.android.example.com.osufootprint20;
import android.support.annotation.NonNull;

import java.util.*;
/**
 * Created by Vincent on 14/11/3.
 */
public class Person {
    private int _id;
    private String name;
    private String password;
    private List<Footprints> footprints;
    private List<Wishlists> wishlists;

    public Person(){
        this.name = "";
        this.password = "";
        Footprints fp = new Footprints();
        Wishlists wl = new Wishlists();
        fp.date = "1991-11-10";
        fp.place = "dl";
        fp.done = "f";
        wl.done = "f";
        wl.place = "cl";
        List<Footprints> foot_list = new ArrayList<Footprints>();
        foot_list.add(fp);
        this.footprints = foot_list;
        List<Wishlists> w_list = new ArrayList<Wishlists>();
        w_list.add(wl);


    }
    public Person( String name, String password){
        //this._id = _id;
        this.name = name;
        this.password = password;
        Footprints fp = new Footprints();
        Wishlists wl = new Wishlists();
        fp.date = "1991-11-10";
        fp.place = "dl";
        fp.done = "f";
        wl.done = "f";
        wl.place = "cl";
        List<Footprints> foot_list = new ArrayList<Footprints>();
        foot_list.add(fp);
        this.footprints = foot_list;
        List<Wishlists> w_list = new ArrayList<Wishlists>();
        w_list.add(wl);

        this.wishlists = w_list;

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
    public List<Footprints>show_myfoorprints(){
        return footprints;
    }
    public void setFootprints(List<Footprints> footprints){ this.footprints = footprints;}
    public List<Wishlists>show_mywishlists(){
        return wishlists;
    }
    public void setWishlists(List<Wishlists> wishlists){ this.wishlists = wishlists;}

}
