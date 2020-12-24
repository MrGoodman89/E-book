package com.example.finallibrary;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private String[] text;
    private Context context;

    public PagerAdapter(Context context, String Text, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
        text = Text.split("&");
    }

    public int NumberOfPages() {
        return text.length;
    }

    @Override
    public int getCount() {
        return (NumberOfPages());
    }

    @Override
    public String getPageTitle(int position)
    {
        return (Pager.getTitle(position));
    }

    @Override
    public Fragment getItem(int position) {
        return (Pager.Page(text[position]));
    }
}
