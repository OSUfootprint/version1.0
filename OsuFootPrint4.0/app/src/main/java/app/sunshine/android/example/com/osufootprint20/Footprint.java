package app.sunshine.android.example.com.osufootprint20;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 陈英硕 on 2014/11/4.
 */
public class Footprint implements Comparable{
    private UUID mID;
    private String mPlace;
    private Date mDate;
    private String mActivity;
    private String mComment;

    public Footprint() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public Footprint(String place, String date, String Activity, String Comment) {
        mID = UUID.randomUUID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            mDate = sdf.parse(date);
        }
        catch(Exception e){ mDate= new Date();}
        mPlace=place;
        mActivity=Activity;
        mComment=Comment;
    }

    public UUID getID() {
        return mID;
    }

    public String getActivity() {
        return mActivity;
    }

    public Date getDate() {
        return mDate;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getComment() {
        return mComment;
    }

    public void setActivity(String activity) { mActivity = activity; }
    public void setDate(Date date) {
        mDate = date;
    }

    @Override
    public int compareTo(Object s) {
        Footprint fp=(Footprint) s;
        return mDate.compareTo(fp.getDate());
    }

    @Override
    public String toString() {
        return mActivity;
    }
}
