package com.example.mufta.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.mufta.R;
import com.example.mufta.databinding.FragmentHomeBinding;
import com.example.mufta.ui.CoursesItemClickListener;
import com.example.mufta.ui.GridSpacingItemDecoration;
import com.example.mufta.ui.adapters.CourseRecyclerAdapter;
import com.example.mufta.ui.models.homeClass;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements CoursesItemClickListener {

    private FragmentHomeBinding binding;
    private Context mcontext;
    private ArrayList<homeClass> homeClasses;
    private CourseRecyclerAdapter adapter;
    String s1;
    int spinnerLastPosition;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mcontext = this.getContext();
        View view = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        SharedPreferences reader = getActivity().getSharedPreferences("city", MODE_PRIVATE);
        spinnerLastPosition = reader.getInt("spinnerLastPositionSaved",1);
        binding.edtSearch.setSelection(spinnerLastPosition);
        SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("city", MODE_PRIVATE).edit();
        binding.edtSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1 = binding.edtSearch.getSelectedItem().toString();
                spinnerLastPosition = binding.edtSearch.getSelectedItemPosition();
                Toast.makeText(mcontext, "City is Changed", Toast.LENGTH_SHORT).show();
                editor.putString("resId", s1);
                editor.putInt("spinnerLastPositionSaved",spinnerLastPosition);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        binding.rvCourses.setLayoutManager(
                layoutManager
        );
        binding.rvCourses.setClipToPadding(false);
        binding.rvCourses.setHasFixedSize(true);

//        binding.rvCourses.addItemDecoration(
//                new HorizontalMarginItemDecoration(
//                        mcontext,
//                        R.dimen.top_text_subtitle_card,
//                        R.dimen.top_text_subtitle_card
//                )
//        );

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.horizontal_card);
        binding.rvCourses.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true, 0));

        homeClasses = new ArrayList<>();

        homeClasses.add(new homeClass(1, R.drawable.education, "Education discounts", "19 discounts"));
        homeClasses.add(new homeClass(2, R.drawable.food, "Food discounts", "14 discounts"));
        homeClasses.add(new homeClass(3, R.drawable.lifestyle, "Lifestyle discounts", "24 discounts"));
        homeClasses.add(new homeClass(4, R.drawable.clothing, "Clothing discounts", "18 discounts"));
        homeClasses.add(new homeClass(5, R.drawable.events, "Event Discounts", "21 discounts"));
        homeClasses.add(new homeClass(6, R.drawable.travelling, "Travelling discounts", "10 discounts"));

        adapter = new CourseRecyclerAdapter(mcontext, homeClasses, this);

//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.card_margin);
//        binding.rvCourses.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        binding.rvCourses.setAdapter(adapter);
        return view;
    }

    private void performSearch() {
        binding.edtSearch.clearFocus();
        InputMethodManager in = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(binding.edtSearch.getWindowToken(), 0);
        //...perform search
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDashboardCourseClick(homeClass homeClass, ImageView imageView) {

    }
}