package com.example.myfishingnote.ui.tide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfishingnote.MainActivity;
import com.example.myfishingnote.R;

public class TideFragment extends Fragment {

    //String tidedata = bundle.getString("data", "없음");

    private TideViewModel tideViewModel;

    private TextView textView;

    /*public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tideViewModel =
                ViewModelProviders.of(this).get(TideViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tide, container, false);
        final TextView textView = root.findViewById(R.id.textViewJson);
        tideViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tide, container, false);

        //액티비티 - 프래그먼트 데이터연동 bundle
        //메인액티비티값을 가져온다
        MainActivity activity = (MainActivity) getActivity();

        //번들처리
        Bundle bundle = new Bundle();       //번들 선언

        if(activity.tidebundle != null){    //번들값이 널이 아니면 가져온다.
            bundle = activity.tidebundle;
        }

        String tidedata = bundle.getString("data");

        textView = rootView.findViewById(R.id.textViewJson);
        //textView.setText(data);
        textView.setText(tidedata);

        return rootView;

    }//onCreateView
}//class