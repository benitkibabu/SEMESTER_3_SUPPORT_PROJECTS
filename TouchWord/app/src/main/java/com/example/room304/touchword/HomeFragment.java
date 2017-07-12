package com.example.room304.touchword;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(String content, int color){
        HomeFragment home = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("content", content);
        args.putInt("color", color);
        home.setArguments(args);

        return home;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home_fragment);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.container);

        layout.setBackgroundColor(getArguments().getInt("color"));

        TextView tv = (TextView) view.findViewById(R.id.tv);
        String content = getArguments().getString("content");
        tv.setText(content);

        return view;
    }
}
