package com.starm.dap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.starm.dap.R;
import com.starm.dap.models.TraingCenter;

import java.util.ArrayList;

public class TraingCenterAdpater extends RecyclerView.Adapter<TraingCenterAdpater.TraingCenterViewHolder>{
    private ArrayList<TraingCenter> mExampleList;
    private Context context;

    public TraingCenterAdpater(Context context, ArrayList<TraingCenter> exampleList) {
        this.context=context;
        mExampleList = exampleList;
    }
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDropDownClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class TraingCenterViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewName,mTextViewLocation;
        // public TextView mTextView2;

        public TraingCenterViewHolder(View itemView, final OnItemClickListener listener) {

            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView_dropdown);
            mTextViewName = itemView.findViewById(R.id.textView_center_name);
            mTextViewLocation = itemView.findViewById(R.id.textView_center_location);
            //  mTextView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDropDownClick(position);
                        }
                    }
                }
            });
        }
    }


    @Override
    public TraingCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_training_center, parent, false);
        TraingCenterViewHolder evh = new TraingCenterViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(TraingCenterViewHolder holder, int position) {
        TraingCenter currentItem = mExampleList.get(position);


        holder.mTextViewName.setText(currentItem.getName());
        holder.mTextViewLocation.setText(currentItem.getLocation());
        //holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    }


