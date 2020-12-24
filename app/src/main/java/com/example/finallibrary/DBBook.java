package com.example.finallibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBBook extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";

    private static final String DATABASE_NAME = "LibraryWithBook";
    public static final String TABLE = "Books";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String IMAGE = "image";
    public static final String TEXT = "text";

    BookCollection bookCollection;

    public DBBook(@Nullable Context context){
        super(context, DATABASE_NAME, null, 2);
        bookCollection = new BookCollection();
        bookCollection.initCollection(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- OnCreate DataBase ---");
        db.execSQL(
                "create table books (" +
                        ID + " integer primary key," + NAME + " text,"
                        + AUTHOR + " text,"
                        + IMAGE + " text,"
                        + TEXT + " text" + ")"
        );

        for (int i = 0; i < bookCollection.books.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAME, bookCollection.books.get(i).name);
            contentValues.put(AUTHOR, bookCollection.books.get(i).author);
            contentValues.put(IMAGE, bookCollection.books.get(i).image);
            contentValues.put(TEXT, bookCollection.books.get(i).text);
            db.insert(TABLE, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLE);
        onCreate(db);
    }
}
