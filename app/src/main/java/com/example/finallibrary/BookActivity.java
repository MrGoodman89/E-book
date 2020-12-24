package com.example.finallibrary;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Book chosenBook = getIntent().getParcelableExtra("chosenBook");

        TextView bookName = findViewById(R.id.book_name);
        bookName.setText(chosenBook.name);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(this, chosenBook.text, getSupportFragmentManager()));
    }
}
