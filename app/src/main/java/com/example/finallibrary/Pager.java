package com.example.finallibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Pager extends Fragment {

    private String text;

    public static Pager Page(String Text)
    {
        Pager pager = new Pager(Text);
        return pager;
    }

    public Pager(String Text) {
        text = Text;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    static String getTitle(int position)
    {
        return "Страница " + (position + 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_fragment, viewGroup, false);
        TextView page = view.findViewById(R.id.pageWithText);
        page.setText(text);
        return view;
    }


}
