package com.example.finallibrary;

import android.content.Context;

import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class BookCollection {
    public ArrayList<Book> books;

    ArrayList<Book> initCollection(Context context){
        if (books == null || books.size()==0){
            String json_string = readRawResource(context, R.raw.books);
            Gson gson = new Gson();
            BookCollection movieCollection = gson.fromJson(json_string, BookCollection.class);
            books = movieCollection.books;
        }
        return books;
    }

    public static String readRawResource(Context context, int res) {
        return readStream(context.getResources().openRawResource(res));
    }

    private static String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
