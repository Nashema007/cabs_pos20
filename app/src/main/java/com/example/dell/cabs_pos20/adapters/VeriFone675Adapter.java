package com.example.dell.cabs_pos20.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.cabs_pos20.R;
import com.example.dell.cabs_pos20.activities.SharedElementTutorialsActivity;
import com.example.dell.cabs_pos20.utilities.Constants;
import com.example.dell.cabs_pos20.utilities.ImageVideoRetrieval;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VeriFone675Adapter extends RecyclerView.Adapter<VeriFone675Adapter.CustomViewHolder>{


    private List<ImageVideoRetrieval> arrayList;
    private ViewPagerAdapter adapter;
    private View view;
    private Context mContext;

    public VeriFone675Adapter(List<ImageVideoRetrieval> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    public VeriFone675Adapter() {
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorial_item, parent, false);
        return new VeriFone675Adapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        holder.setIsRecyclable(false);
        holder.name.setText(arrayList.get(position).getDescription());
        holder.img.setImageResource(arrayList.get(position).getFilename());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), SharedElementTutorialsActivity.class);
                intent.putExtra(Constants.V675,arrayList.get(position).getErrors());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{


        @Nullable
        @BindView(R.id.tutorialDescript) TextView name;
        @Nullable
        @BindView(R.id.TutorialImg) ImageView img;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
