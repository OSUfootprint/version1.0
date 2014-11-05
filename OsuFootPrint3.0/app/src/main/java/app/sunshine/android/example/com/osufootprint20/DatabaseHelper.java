package app.sunshine.android.example.com.osufootprint20;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 14/11/1.
 */
public class DatabaseHelper {
    private static final String DATABASE_NAME = "Footprint.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_footprint";
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
        db.execSQL("insert into tbl_footprint(null, ?, ?)",new Object[]{person.getName(), person.getPassword()});
    }
    public void delete(String name){
        db.execSQL("delete from tbl_footprint where name=?", new Object[]{name});
    }
    public Person queryOne(String name){
        Person person = new Person();
        Cursor c = db.rawQuery("select * from tbl_person where name=?", new String[] { name + "" });
        while (c.moveToNext()) {
            //person.set_id(c.getInt(c.getColumnIndex("_id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setPassword(c.getString(c.getColumnIndex("password")));
            //person.setFootprints(c.g(c.getColumnIndex("footprints"));
        }
        c.close();
        return person;
    }
    public List<Person> queryMany() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Cursor c = db.rawQuery("select * from tbl_person", null);
        while (c.moveToNext()) {
            Person person = new Person();
            //person.set_id(c.getInt(c.getColumnIndex("_id")));
            person.setName(c.getString(c.getColumnIndex("name")));
            person.setPassword(c.getString(c.getColumnIndex("password")));
            persons.add(person);
        }
        c.close();
        return persons;
    }


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



    private static class FootprintOpenHelper extends SQLiteOpenHelper{
        FootprintOpenHelper(Context context){
            super(context,DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE" + TABLE_NAME + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}

