package com.example.mufta.ui.adapters;

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
import com.example.mufta.R;
import com.example.mufta.ui.activities.openfood;
import com.example.mufta.ui.models.foodmodelclass;

import java.util.ArrayList;

public class foodadapter extends RecyclerView.Adapter<foodadapter.Viewholder> {

    private final Context context;
    private final ArrayList<foodmodelclass> foodArrayList;

    public foodadapter(Context context, ArrayList<foodmodelclass> foodArrayList) {
        this.context = context;
        this.foodArrayList = foodArrayList;
    }

    @NonNull
    @Override
    public foodadapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_discount, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        foodmodelclass model = foodArrayList.get(position);
        holder.ftitle.setText("" + model.getTitle());
        holder.flocation.setText("" + model.getLocation());
        holder.fdescription.setText("" + model.getDescription());
        holder.fenddate.setText("" + model.getEndingDate());
        holder.upvote.setText("" + model.getUpvote());
        Glide.with(context).load(model.getImage())
                .placeholder(R.drawable.avatar)
                .into(holder.image);

        holder.foodcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(context, openfood.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("Title", model.getTitle());
                intent.putExtra("Description", model.getDescription());
                intent.putExtra("Category", model.getCategory());
                intent.putExtra("StartingDate", model.getStartingDate());
                intent.putExtra("EndingDate", model.getEndingDate());
                intent.putExtra("Discount", model.getDiscount());
                intent.putExtra("Location", model.getLocation());
                intent.putExtra("Upvote", model.getUpvote());
                intent.putExtra("Link", model.getLink());
                intent.putExtra("Id", model.getId());
                intent.putExtra("Contact", model.getContact());
                intent.putExtra("Image", model.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView ftitle, flocation, fdescription, fenddate, upvote;
        ImageView image;
        CardView foodcv;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            foodcv = itemView.findViewById(R.id.food_itemcv);
            ftitle = itemView.findViewById(R.id.ftitle);
            flocation = itemView.findViewById(R.id.flocation);
            fdescription = itemView.findViewById(R.id.fdescription);
            fenddate = itemView.findViewById(R.id.fenddate);
            image = itemView.findViewById(R.id.imageexc);
            upvote = itemView.findViewById(R.id.ratting);
        }
    }
}
