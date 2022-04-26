package com.example.mufta.ui;

import android.widget.ImageView;

import com.example.mufta.ui.models.homeClass;

public interface CoursesItemClickListener {

    void onDashboardCourseClick(homeClass homeClass, ImageView imageView); // Shoud use imageview to make the shared animation between the two activity

}
