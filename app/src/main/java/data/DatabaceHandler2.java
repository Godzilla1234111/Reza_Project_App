package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class DatabaceHandler2 extends SQLiteOpenHelper {

    private static final String TAG = "DatabaceHandler2";
    //database variables
    private static final String TABLE_NAME = "water_table";
    private static final String COL1 = "WaterAmount";

    public DatabaceHandler2(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creating a table fpr db
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //SQLite query to update table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //CREATE A NEW ONE
        onCreate(db);
    }
    //adds data to table
    public boolean addData(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        //logs wne item is added to table
        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //DELETE ITEMS
    public void DelData(){

        SQLiteDatabase dba = this.getReadableDatabase();

        dba.delete(TABLE_NAME, null,null);
        dba.execSQL("delete from "+ TABLE_NAME);
        dba.close();

    }

    public int totalWater() {
        int water = 0;
        SQLiteDatabase dba = this.getReadableDatabase();
        String query = "SELECT SUM( " + COL1 + " ) " +
                "FROM " + TABLE_NAME;

        Cursor cursor = dba.rawQuery(query, null);
        if (cursor.moveToFirst()) {

            water = cursor.getInt(0);
        }
        cursor.close();
        dba.close();
        return water;
    }
}
