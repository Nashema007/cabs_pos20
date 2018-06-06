package com.example.dell.cabs_pos20.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.adapters.VeriFone675Adapter;
import com.example.dell.cabs_pos20.utilities.Constants;
import com.example.dell.cabs_pos20.utilities.ImageVideoRetrieval;

import java.util.ArrayList;
import java.util.List;

public class VeriFone675_Frag extends Fragment {

    private View v;
    private int vid;
    private RecyclerView recyclerView;
    @Nullable
    private VeriFone675Adapter recycleAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @NonNull
    private List<ImageVideoRetrieval> arrayList = new ArrayList<>();
    @NonNull
    private int[] icons = {R.mipmap.ic_ce,R.mipmap.ic_printer, R.mipmap.ic_nd,R.mipmap.ic_clear_batch, R.mipmap.ic_settlement};
    @NonNull
    private int[] description = {R.string.error_ce, R.string.print_error,R.string.error_nd, R.string.error_batch, R.string.settlement};
    @NonNull
    private String[] errors = {Constants.CE, Constants.PRINTER_ERROR, Constants.ND, Constants.ERROR_BATCH, Constants.SETTLEMENT};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_verifone675, container, false);
        recyclerView = v.findViewById(R.id.recyclerview_675);
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recycleAdapter = new VeriFone675Adapter(arrayList, getContext());
        recyclerView.setAdapter(recycleAdapter);
        if(arrayList.size() < description.length ){
            recyclerViewManager();

        }
        return v;
    }

    private void recyclerViewManager() {
        recyclerView = v.findViewById(R.id.recyclerview_675);
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        int count = 0;

        for(int name: description) {
            arrayList.add(new ImageVideoRetrieval(name, icons[count], errors[count]));
            count++;
        }

        recycleAdapter = new VeriFone675Adapter(arrayList, getContext());
        recyclerView.setAdapter(recycleAdapter);
    }
    }
