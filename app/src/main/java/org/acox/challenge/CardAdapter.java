package org.acox.challenge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<ContentModel> items;
    private List<String> mData;

    public CardAdapter(ArrayList<ContentModel> items) {
        this.items = items;
        Log.d("check", items.toString());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_title);
            //imageView = (ImageView) itemView.findViewById(R.id.list_image);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ContentModel mContentModel = items.get(position);

        //holder.textView.setText(mData.get(position));
        //holder.imageView.setImageDrawable(LoadImageFromWebOperations(mContentModel.getImg()));
        holder.textView.setText(mContentModel.getTitle());
    }

    public int getItemCount(){
        return items.size();
    }


    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}