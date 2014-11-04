package app.sunshine.android.example.com.osufootprint20;
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

    public Person(){}
    public Person( String name, String password){
        //this._id = _id;
        this.name = name;
        this.password = password;
    }

//    public int get_id(){
//        return _id;
//    }
//    public void set_id(int _id){
//        this._id = _id;
//    }
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
    public List<Wishlists>show_mywishlists(){
        return wishlists;
    }

}
