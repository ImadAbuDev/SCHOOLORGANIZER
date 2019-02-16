package stqu.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "CLASS";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,CLASS TEXT)");
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }



    public boolean insertData(String name,String surname,String klasse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4, klasse);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);// Instanz der Daten wird festgelegt
        return res;

    }
    public Cursor getName(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select NAME from "+TABLE_NAME,null);// Instanz der Daten wird festgelegt
        return res;

    }
    public Cursor getSurname(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select SURNAME from "+TABLE_NAME,null);// Instanz der Daten wird festgelegt
        return res;

    }
    public Cursor getKlasse(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select CLASS from "+TABLE_NAME,null);// Instanz der Daten wird festgelegt
        return res;

    }


}
