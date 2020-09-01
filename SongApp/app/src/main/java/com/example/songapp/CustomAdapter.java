package com.example.songapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, name, category, artist, rating, critic, duration, link;

    CustomAdapter(Activity activity, Context context,  ArrayList id, ArrayList name, ArrayList category, ArrayList artist, ArrayList rating, ArrayList critic, ArrayList duration, ArrayList link ) {

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.category = category;
        this.artist = artist;
        this.rating = rating;
        this.critic = critic;
        this.duration =duration;
        this.link = link;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.category_txt.setText(String.valueOf(category.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("category", String.valueOf(category.get(position)));
                intent.putExtra("artist", String.valueOf(artist.get(position)));
                intent.putExtra("rating", String.valueOf(rating.get(position)));
                intent.putExtra("critic", String.valueOf(critic.get(position)));
                intent.putExtra("duration", String.valueOf(duration.get(position)));
                intent.putExtra("link", String.valueOf(link.get(position)));
                activity.startActivityForResult(intent, 1);




            }
        });



    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, name_txt, category_txt;
        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            category_txt = itemView.findViewById(R.id.category_txt);
            mainLayout  = itemView.findViewById(R.id.mainLayout);


        }
    }
}
