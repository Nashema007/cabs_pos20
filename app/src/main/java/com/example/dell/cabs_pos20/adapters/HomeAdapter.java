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
import com.example.dell.cabs_pos20.activities.VeriFone520;
import com.example.dell.cabs_pos20.activities.VeriFone675;
import com.example.dell.cabs_pos20.activities.VeriFone_e265;
import com.example.dell.cabs_pos20.utilities.Home;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> {


    private List<Home> arrayList =new ArrayList<>();
    private View view;
    @NonNull
    private List<Home> oList = new ArrayList<>();
    private Context mContext;

    public HomeAdapter(List<Home> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.oList = new ArrayList<>();
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new HomeAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        holder.name.setText(arrayList.get(position).getPosName());
        holder.img.setImageResource(arrayList.get(position).getImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Class[] list = new Class[]{VeriFone675.class, VeriFone520.class, VeriFone_e265.class};
                for (int i = 0; i < arrayList.size(); i++){

                    if (holder.name.getText().equals(arrayList.get(i).getPosName())){
                        Intent intent = new Intent(mContext, list[i]);
                        mContext.startActivity(intent);

                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        @Nullable
        @BindView(R.id.veriFoneTxt) TextView name;
        @Nullable
        @BindView(R.id.veriFoneImg) ImageView img;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
