package com.example.myfishingnote.ui.note;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myfishingnote.R;
import com.github.channguyen.rsv.RangeSliderView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteAddFragment extends Fragment {
    Context context;
    OnTabItemSelectedListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteAddFragment newInstance(String param1, String param2) {
        NoteAddFragment fragment = new NoteAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }//if
    }//onAttach

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
        }//if
    }//onDetach

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_note_add, container, false);

        initUI(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }//onCreateView

    private void initUI(ViewGroup rootView) {
        Button saveButton = rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }//if
            }
        });

        Button deleteButton = rootView.findViewById(R.id.deleteButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }//if
            }
        });

        Button closeButton = rootView.findViewById(R.id.closeButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTabSelected(0);
                }//if
            }
        });

        RangeSliderView sliderView = rootView.findViewById(R.id.sliderView);
        sliderView.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                Toast.makeText(context, "moodIndex changed to " + index, Toast.LENGTH_SHORT).show();
            }
        });

        sliderView.setInitialIndex(2);





    }//initUI



}