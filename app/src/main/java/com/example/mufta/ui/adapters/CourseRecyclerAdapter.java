package com.example.mufta.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mufta.databinding.ItemCardBinding;
import com.example.mufta.ui.CoursesItemClickListener;
import com.example.mufta.ui.activities.educationdiscounts;
import com.example.mufta.ui.activities.fooddiscounts;
import com.example.mufta.ui.models.educationClass;
import com.example.mufta.ui.models.homeClass;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter._ViewHolder> {

    Context mContext;
    private List<homeClass> mData;
    private CoursesItemClickListener coursesItemClickListener;

    public CourseRecyclerAdapter(Context mContext, List<homeClass> mData, CoursesItemClickListener listener) {
        this.mContext = mContext;
        this.mData = mData;
        this.coursesItemClickListener = listener;
    }

    @NonNull
    @Override
    public CourseRecyclerAdapter._ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_card,viewGroup,false);
//        return new _ViewHolder(view);

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemCardBinding itemCardBinding = ItemCardBinding.inflate(layoutInflater, viewGroup, false);
        return new _ViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final CourseRecyclerAdapter._ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {
//        viewHolder.mItem = mData.get(i);
        final int pos = viewHolder.getAdapterPosition();
        //Set ViewTag
        viewHolder.itemView.setTag(pos);

        viewHolder.setPostImage(mData.get(i));

//        viewHolder.itemCardBinding.stagItemCourse.setText(mData.get(i).getCourseTitle());
//        viewHolder.itemCardBinding.stagItemQuantityCourse.setText(mData.get(i).getQuantityCourses());

        //1st intent 2 methods
//        if (i%2==1){
//            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) viewHolder.card_item.getLayoutParams();
//            cardViewMarginParams.setMargins(dpToPx(8), dpToPx(16), 0, 0);
//            viewHolder.card_item.requestLayout();
//        }
//        if (i==1){
//            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) viewHolder.card_item.getLayoutParams();
//            cardViewMarginParams.setMargins(dpToPx(8), dpToPx(32), 0, 0);
//            viewHolder.card_item.requestLayout();
//        }

        //2nd intent card only bottom margin in xml  and only top margin in adapter- it works

//        if (i%2==1){
//
//            int dimenTopPixeles = getDimensionValuePixels(R.dimen.staggedmarginbottom);
//            int dimenleftPixeles = getDimensionValuePixels(R.dimen.horizontal_card);
//            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) viewHolder.itemCardBinding.cardItem.getLayoutParams();
//            cardViewMarginParams.setMargins(dimenleftPixeles, dimenTopPixeles, 0, 0);
//            viewHolder.itemCardBinding.cardItem.requestLayout();
//        }

//      viewHolder.card_item.setBackgroundColor(mContext.getResources().getColor(R.color.color1));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 1) {
                    Intent intent = new Intent(viewHolder.itemView.getContext(), fooddiscounts.class);
                    intent.putExtra("category", "food");
                    viewHolder.itemView.getContext().startActivity(intent);
                } else if (pos == 2) {
                    Intent intent = new Intent(viewHolder.itemView.getContext(), fooddiscounts.class);
                    intent.putExtra("category", "lifestyle");
                    viewHolder.itemView.getContext().startActivity(intent);
                } else if (pos == 3) {
                    Intent intent = new Intent(viewHolder.itemView.getContext(), fooddiscounts.class);
                    intent.putExtra("category", "clothing");
                    viewHolder.itemView.getContext().startActivity(intent);
                } else if (pos == 4) {
                    Intent intent = new Intent(viewHolder.itemView.getContext(), fooddiscounts.class);
                    intent.putExtra("category", "event");
                    viewHolder.itemView.getContext().startActivity(intent);
                } else if (pos == 5) {
                    Intent intent = new Intent(viewHolder.itemView.getContext(), fooddiscounts.class);
                    intent.putExtra("category", "travelling");
                    viewHolder.itemView.getContext().startActivity(intent);
                } else if (pos == 0) {
                    Intent i = new Intent(viewHolder.itemView.getContext(), educationdiscounts.class);
                    viewHolder.itemView.getContext().startActivity(i);
                }
            }
        });
    }

    public int getDimensionValuePixels(int dimension) {
        return (int) mContext.getResources().getDimension(dimension);
    }


    public int dpToPx(int dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @Override
    public long getItemId(int position) {
        homeClass homeClass = mData.get(position);
        return homeClass.getId();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class _ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView course;
//        TextView quantity_courses;
//        CardView card_item;
//        public CourseCard mItem;
//        public _ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            card_item = itemView.findViewById(R.id.card_item);
//            imageView = itemView.findViewById(R.id.card_view_image);
//            course = itemView.findViewById(R.id.stag_item_course);
//            quantity_courses = itemView.findViewById(R.id.stag_item_quantity_course);
//        }

        ItemCardBinding itemCardBinding;

        public _ViewHolder(@NonNull ItemCardBinding cardBinding) {
            super(cardBinding.getRoot());
            this.itemCardBinding = cardBinding;

            //this.itemRecyclerMealBinding.
        }

        void setPostImage(homeClass homeClass) {
            this.itemCardBinding.cardViewImage.setImageResource(homeClass.getImageCourse());
            this.itemCardBinding.stagItemCourse.setText(homeClass.getCourseTitle());
            this.itemCardBinding.stagItemQuantityCourse.setText(homeClass.getQuantityCourses());
        }

    }
}

