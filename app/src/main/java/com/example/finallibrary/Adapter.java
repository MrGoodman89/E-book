package com.example.finallibrary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.BooksViewHolder> {

    public ArrayList<Book> bookArrayList;
    Context context;
    DBBook dbBook;

    public Adapter(Context mContext) {
        super();
        bookArrayList = new ArrayList<Book>();
        context = mContext;
        dbBook = new DBBook(context);
        reloadList();
    }

    public void reloadList() {
        Book book;
        bookArrayList.clear();

        SQLiteDatabase sqLiteDatabase = dbBook.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Books", null, null, null, null, null,null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(dbBook.ID);
            int nameIndex = cursor.getColumnIndex(dbBook.NAME);
            int authorIndex = cursor.getColumnIndex(dbBook.AUTHOR);
            int imageIndex = cursor.getColumnIndex(dbBook.IMAGE);
            int textIndex = cursor.getColumnIndex(dbBook.TEXT);

            do{

                book = new Book(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getString(authorIndex), cursor.getString(imageIndex), cursor.getString(textIndex));
                bookArrayList.add(book);

            }while (cursor.moveToNext());
        } else cursor.close();
    }

    public static class BooksViewHolder extends RecyclerView.ViewHolder {

        public View bookView;
        public TextView bookName, bookAuthor, bookText;
        public ImageView bookImage;
        public LinearLayout layout;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookView = itemView;
            layout = bookView.findViewById(R.id.layout);
            bookName = bookView.findViewById(R.id.name_textView);
            bookAuthor = bookView.findViewById(R.id.author_textView);
            bookImage = bookView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public Adapter.BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bookView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item_layout, parent, false);
        BooksViewHolder vh = new BooksViewHolder(bookView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.BooksViewHolder holder, final int position) {
        BooksViewHolder booksViewHolder = (BooksViewHolder)holder;
        booksViewHolder.bookName.setText(bookArrayList.get(position).name);
        booksViewHolder.bookAuthor.setText(bookArrayList.get(position).author);

        //NOT WORK
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(bookArrayList.get(position).image).into(booksViewHolder.bookImage);

        booksViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context, BookActivity.class);
                i.putExtra("chosenBook", bookArrayList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }
}
