package com.example.hasretsariyer.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class PageFragment extends Fragment {
    private int backgroundColor = 0;
    public PageFragment() {}

    public static PageFragment newInstance(int i) {
        PageFragment newFragment = new PageFragment();

        Bundle args = new Bundle();
        args.putInt("backgroundColor", i);
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundColor = getArguments().getInt("backgroundColor", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        FrameLayout layout = (FrameLayout) view.findViewById(R.id.pageFragmentRootLayout);
        layout.setBackgroundColor(backgroundColor);
        return view;
    }
}
