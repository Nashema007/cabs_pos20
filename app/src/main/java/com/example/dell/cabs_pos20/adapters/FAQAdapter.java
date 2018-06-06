package com.example.dell.cabs_pos20.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.utilities.FAQ;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.CustomViewHolder> {

    private View view;
    private Context context;
    private List<FAQ> list;

    public FAQAdapter(Context context, List<FAQ> list) {
        this.context = context;
        this.list = list;
    }

    public FAQAdapter() {
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new CustomViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.answer.setText(list.get(position).getAnswer());
        holder.question.setText(list.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{


        @Nullable
        @BindView(R.id.questionText)
        TextView question;

        @Nullable
        @BindView(R.id.answerText)
        TextView answer;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
