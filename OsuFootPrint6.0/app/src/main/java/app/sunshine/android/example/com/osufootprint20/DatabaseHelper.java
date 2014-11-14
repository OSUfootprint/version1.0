package app.sunshine.android.example.com.osufootprint20;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 14/11/1.
 */
public class DatabaseHelper {
    private static final String DATABASE_NAME = "Footprint.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Account";
    private Context context;
    private SQLiteDatabase db;
    //private SQLiteStatement insertStmt;
    //private static final String INSERT = "insert into " + TABLE_NAME + "(name, password) values (?, ?)" ;
    //private static  final String DICTIONARY_TABLE_CREATE = "CREATE TABLE" + TABLE_NAME + " (" + KEY_WORD +
    //        "TEXT, " + KEY_DEFINITION + " TEXT);";

    public DatabaseHelper(Context context){
        this.context = context;
        FootprintOpenHelper openHelper = new FootprintOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        //this.insertStmt = this.db.compileStatement(INSERT);

    }

//    public long insertLogInfo(String name, String password){
//        this.insertStmt.bindString(1, name);
//        this.insertStmt.bindString(2, password);
//        return this.insertStmt.executeInsert();
//    }
    public void add(Person person){
        db.execSQL("insert into Account VALUES (null, ?, ?,null,null,null,null)",new Object[]{person.getName(), person.getPassword()});
    }
    public void delete(String name){
        db.execSQL("delete from Account where name=?", new Object[]{name});
    }
    public Person queryOne(String name){
        Person person = new Person(context.getApplicationContext());
        Cursor c = db.rawQuery("select * from Account where name=?", new String[] { name + "" });
        while (c.moveToNext()) {
            person.set_id(c.getInt(c.getColumnIndex("_id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setPassword(c.getString(c.getColumnIndex("password")));
            //person.setFootprints(c.g(c.getColumnIndex("footprints"));
        }
        c.close();
        return person;
    }
//    public List<Person> queryMany() {
//        ArrayList<Person> persons = new ArrayList<Person>();
//        Cursor c = db.rawQuery("select * from Account", null);
//        while (c.moveToNext()) {
//            Person person = new Person(context.getApplicationContext());
//            //person.set_id(c.getInt(c.getColumnIndex("_id")));
//            person.setName(c.getString(c.getColumnIndex("name")));
//            person.setPassword(c.getString(c.getColumnIndex("password")));
//            persons.add(person);
//        }
//        c.close();
//        return persons;
//    }


    public void deleteAll(){
        this.db.delete(TABLE_NAME,null, null);
    }
    public List<String> selectAll(String username, String password) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] { "name", "password" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    public void savePhotp( Bitmap image) {
        if (image == null) return;
        String name = Person.getPerson(context.getApplicationContext()).getName();
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, os);
        ContentValues values = new ContentValues();
        values.put("img",os.toByteArray());
        db.update(TABLE_NAME,values,"name=?",new String[]{name});
    }

    public Bitmap getPhoto(String name){
        byte[] blob = new byte[0];
        Cursor c = db.rawQuery("select * from Account where name=?", new String[] { name + "" });
        while(c.moveToNext()){
            blob = c.getBlob(c.getColumnIndex("img"));
        }
        Bitmap result = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        return result;
    }

    public void setFootprints() {
        ArrayList<Footprint> footprints=new ArrayList<Footprint>(Person.getPerson(context.getApplicationContext()).getFootprint().getMySet());
        String name = Person.getPerson(context.getApplicationContext()).getName();
        Gson gson=new Gson();
        String footprint_string = gson.toJson(footprints,new TypeToken<ArrayList<Footprint>>(){}.getType());
        ContentValues values = new ContentValues();
        values.put("footprint",footprint_string);
        db.update(TABLE_NAME,values,"name=?",new String[]{name});
    }

    public void getFootprints(){
        String footprint_string = null;
        String name = Person.getPerson(context.getApplicationContext()).getName();
        Cursor c = db.rawQuery("select * from Account where name=?", new String[] { name + "" });
        while (c.moveToNext()) {
            footprint_string = c.getString(c.getColumnIndex("footprint"));
        }
        c.close();
        Gson gson=new Gson();
        ArrayList<Footprint> footprints=gson.fromJson(footprint_string,new TypeToken<ArrayList<Footprint>>(){}.getType());
        Person.getPerson(context.getApplicationContext()).getFootprint().setMySet(footprints);
    }

    //public setWishlists(String name, )

    //public getWishlists(string name, )

    private static class FootprintOpenHelper extends SQLiteOpenHelper{
        FootprintOpenHelper(Context context){
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            String createtbl = "CREATE TABLE Account (_id integer primary key autoincrement, " +
                    "name text, password text, img BLOB, footprint text, wishlist text, place text";
            db.execSQL(createtbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}

