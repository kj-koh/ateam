package com.example.myfishingnote.ui.tide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfishingnote.R;

public class TideFragment extends Fragment {

    private TideViewModel tideViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tideViewModel =
                ViewModelProviders.of(this).get(TideViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tide, container, false);
        final TextView textView = root.findViewById(R.id.text_tide);
        tideViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}