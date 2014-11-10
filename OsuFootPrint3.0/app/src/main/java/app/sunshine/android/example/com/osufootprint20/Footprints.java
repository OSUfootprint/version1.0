package app.sunshine.android.example.com.osufootprint20;

import java.util.Date;

/**
 * Created by Vincent on 14/11/3.
 */
public class Footprints {
    String date;
    String place;
    String done;
    public Footprints(String date, String p, String d){
        this.date = date;
        this.place = p;
        this.done = d;
    }
    public Footprints(){
        this.date = "2010-1-1";
        this.place = "dl";
        this.done = "fuck";
    }

}
