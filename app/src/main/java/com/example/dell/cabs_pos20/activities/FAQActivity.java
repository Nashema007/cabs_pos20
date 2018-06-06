package com.example.dell.cabs_pos20.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.adapters.FAQAdapter;
import com.example.dell.cabs_pos20.utilities.FAQ;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FAQActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private FAQAdapter adapter;
    @NonNull
    private List<FAQ> list = new ArrayList<>();
    @NonNull
    private int[] questions = {R.string.Q_charger_not_working, R.string.Q_Temper,R.string.Q_why_it_freezes, R.string.Q_ecocash};
    @NonNull
    private int[] answers = {R.string.A_charger_not_working, R.string.A_Temper, R.string.A_why_it_freezes, R.string.A_ecocash};
    @Nullable
    @BindView(R.id.faq_RecyclerView)
    RecyclerView faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ButterKnife.bind(this);



        layoutManager = new LinearLayoutManager(this);
        faq.setLayoutManager(layoutManager);
        faq.setHasFixedSize(true);

        int i = 0;
        for(int question: questions){

            list.add(new FAQ(question, answers[i]));
            i++;
        }

        adapter = new FAQAdapter(this, list);
        faq.setAdapter(adapter);

    }
}
