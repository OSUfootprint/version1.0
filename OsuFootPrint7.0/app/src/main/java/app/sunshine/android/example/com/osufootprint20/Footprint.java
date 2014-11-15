package app.sunshine.android.example.com.osufootprint20;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
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
    private byte[] mPhoto;

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

    public Bitmap getPhoto() {
        if(mPhoto==null) return null;
        Bitmap photo = BitmapFactory.decodeByteArray(mPhoto, 0, mPhoto.length);
        return photo;
    }

    public void setActivity(String activity) { mActivity = activity; }
    public void setDate(Date date) {
        mDate = date;
    }
    public void setPlace(String place) {
        mPlace = place;
    }
    public void setComment(String comment) {
        mComment = comment;
    }

    public void setPhoto(Bitmap photo) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, os);
        mPhoto=os.toByteArray();
    }

    @Override
    public int compareTo(Object s) {
        Footprint fp=(Footprint) s;
        return -mDate.compareTo(fp.getDate());
    }

    @Override
    public String toString() {
        return mActivity;
    }
}
