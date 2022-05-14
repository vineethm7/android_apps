package com.example.project3a2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class attr_web extends Fragment {

    private WebView open = null;
    private int curr_index = -1;
    private int array_len ;
    private ListViewModel model;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.attraction_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        array_len = AttrActivity.attr_web_arr.length;
        model = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        model.getSelectedItem().observe(getViewLifecycleOwner(), item->{
            if(item < 0 || item >= array_len)
                return;
            curr_index = item;
            open.loadUrl(AttrActivity.attr_web_arr[curr_index]);

        });
        open = getActivity().findViewById(R.id.web);
    }
}