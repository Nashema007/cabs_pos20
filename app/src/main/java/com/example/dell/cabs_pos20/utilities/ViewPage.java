package com.example.dell.cabs_pos20.utilities;

import android.app.Fragment;

public class ViewPage {

    private String title;
    private Fragment frag;

    public ViewPage(String title, Fragment frag) {
        this.title = title;
        this.frag = frag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFrag() {
        return frag;
    }

    public void setFrag(Fragment frag) {
        this.frag = frag;
    }
}
