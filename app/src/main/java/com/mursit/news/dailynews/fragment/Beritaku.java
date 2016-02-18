package com.mursit.news.dailynews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.mursit.news.dailynews.R;

import java.util.ArrayList;

/**
 * Created by ima2 on 2/15/16.
 */
public class Beritaku extends Fragment{
    public ListView dataBeritaku;
    private AQuery aq;
    public Beritaku () {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beritaku, container, false);
    }
}

