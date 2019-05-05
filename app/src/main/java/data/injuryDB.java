package data;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import development.app.reza.myapplicationdevelopemntreza.Question;

import static data.InjuryContract.MovieEntry.KEY_ANSWER;
import static data.InjuryContract.MovieEntry.KEY_OPTB;
import static data.InjuryContract.MovieEntry.KEY_OPTC;
import static data.InjuryContract.MovieEntry.KEY_QUES;
import static data.InjuryContract.MovieEntry.TABLE_QUEST;
import static data.InjuryContract.MovieEntry.KEY_OPTA;
import static data.InjuryContract.MovieEntry.KEY_ID;

public class injuryDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //databace name
    private static final String DATABASE_NAME = "InjuryDetection";
    //tabel name
    private SQLiteDatabase dbase;
    public injuryDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
    }
    //injury questions
    private void addQuestions()
    {
        Question q1=new Question("Where dose it hurt","Arm", "Leg", "Neck", "Arm");
        this.addQuestion(q1);
        Question q2=new Question("What is the degree of the pain", "1-3", "3-5", "5-10", "3-5");
        this.addQuestion(q2);
        Question q3=new Question("How dose your body feel","Stiff", "Relaxed","Spassing", "Stiff" );
        this.addQuestion(q3);
        Question q4=new Question("Do you take any medication", "Yes", "NO", "Not shure","Not shure");
        this.addQuestion(q4);
        Question q5=new Question("How Bad is the bleeding","Not bleeding","Lots of bleeding","Modorate bleeding","Lots of bleeding");
        this.addQuestion(q5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}

