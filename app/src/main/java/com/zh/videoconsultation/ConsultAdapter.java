package com.zh.videoconsultation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class ConsultAdapter extends RecyclerView.Adapter<ConsultAdapter.ViewHolder> {

    private Context mContext;
    private List<Consult> mConsultList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView)view;
            imageView = (ImageView) view.findViewById(R.id.fruit_image);
            textView = (TextView) view.findViewById(R.id.fruit_name);
        }
    }

    public ConsultAdapter(List<Consult> consultList){
        mConsultList = consultList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.consult_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
//                int pos = holder.getAbsoluteAdapterPosition();
                Consult consult = mConsultList.get(pos);
                Intent intent = new Intent(mContext, ConsultActivity.class);
                intent.putExtra(ConsultActivity.CONSULT_NAME, consult.getName());
                intent.putExtra(ConsultActivity.CONSULT_IMAGE_ID, consult.getImageId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Consult consult = mConsultList.get(position);
        holder.textView.setText(consult.getName());
        Glide.with(mContext).load(consult.getImageId()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mConsultList.size();
    }
}