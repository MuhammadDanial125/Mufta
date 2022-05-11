package com.example.mufta.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.example.mufta.ui.activities.openeducation;
import com.example.mufta.ui.activities.openfood;
import com.example.mufta.ui.models.educationClass;
import com.example.mufta.ui.models.homeClass;

import java.util.ArrayList;

public class educationsAdapter extends RecyclerView.Adapter<educationsAdapter.Viewholder> {
    private final Context context;
    private final ArrayList<educationClass> educationList;

    // Constructor
    public educationsAdapter(Context context, ArrayList<educationClass> educationList) {
        this.context = context;
        this.educationList = educationList;
    }

    @NonNull
    @Override
    public educationsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        educationClass eclass = educationList.get(position);
        holder.etitle.setText("" + eclass.getCoursetitle());
        holder.ecourse.setText("" + eclass.getDiscountdescription());
        holder.elanguage.setText("" + eclass.getLanguage());
        holder.ediscountedprice.setText("0.00$");
        holder.ewebsitename.setText("" + eclass.getWebsitename());
        holder.eorignalprice.setText("" + eclass.getOriginalprice()+".00$");
        holder.etime.setText("" + eclass.getTimeduration());
        holder.eexpires.setText("" + eclass.getExpirydate());
        holder.eorignalprice.setPaintFlags(holder.eorignalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (eclass.getWebsitename().equals("Udemy")) {
            Glide.with(context).load(R.drawable.udemy)
                    .into(holder.imagee);
        } else if (eclass.getWebsitename().equals("Udacity")) {
            Glide.with(context).load(R.drawable.udacity)
                    .into(holder.imagee);
        } else if (eclass.getWebsitename().equals("Coursera")) {
            Glide.with(context).load(R.drawable.courseera)
                    .into(holder.imagee);
        } else if (eclass.getWebsitename().equals("edX")) {
            Glide.with(context).load(R.drawable.edx)
                    .into(holder.imagee);
        } else if (eclass.getWebsitename().equals("Datacamp")) {
            Glide.with(context).load(R.drawable.datacamp)
                    .into(holder.imagee);
        }

        holder.cardViewd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, openeducation.class);
                intent.putExtra("title", eclass.getCoursetitle());
                intent.putExtra("website", eclass.getWebsitename());
                intent.putExtra("time", eclass.getTimeduration());
                intent.putExtra("oprice", eclass.getOriginalprice());
                intent.putExtra("dprice", eclass.getDiscountedprice());
                intent.putExtra("discount", eclass.getDiscountdescription());
                intent.putExtra("link", eclass.getLink());
                intent.putExtra("id", eclass.getRid());
                intent.putExtra("expiry", eclass.getExpirydate());
                intent.putExtra("details", eclass.getDetails());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return educationList.size();
    }

    // View holder class for initializing of
// your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView etitle;
        private final TextView ewebsitename;
        private final TextView elanguage;
        private final TextView ecourse;
        private final TextView ediscountedprice;
        private final TextView eorignalprice;
        private final TextView etime;
        private final TextView eexpires;
        private final ImageView imagee;

        CardView cardViewd;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            etitle = itemView.findViewById(R.id.titlee);
            ewebsitename = itemView.findViewById(R.id.websitename);
            elanguage = itemView.findViewById(R.id.language);
            ecourse = itemView.findViewById(R.id.course);
            ediscountedprice = itemView.findViewById(R.id.discountedprice);
            eorignalprice = itemView.findViewById(R.id.orignalprice);
            etime = itemView.findViewById(R.id.time);
            eexpires = itemView.findViewById(R.id.expires);
            imagee = itemView.findViewById(R.id.backgroundimage);
            cardViewd = itemView.findViewById(R.id.cardvieww);

        }
    }
}

