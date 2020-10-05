package com.example.myfishingnote.ui.note;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myfishingnote.R;

import lib.kingja.switchbutton.SwitchMultiButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteListFragment extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;

    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        if(context instanceof  OnTabItemSelectedListener) {
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoteListFragment newInstance(String param1, String param2) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_note_list, container, false);
        initUI(rootView);
        
        // Inflate the layout for this fragment
        return rootView;
    }//onCreateView

    private void initUI(ViewGroup rootView) {

        Button todayWriteButton = rootView.findViewById(R.id.todayWriteButton);
        todayWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchMultiButton = rootView.findViewById(R.id.switchButton);
        switchMultiButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener() {
            @Override
            public void onSwitch(int position, String tabText) {
                Toast.makeText(getContext(), tabText, Toast.LENGTH_SHORT).show();

                adapter.switchLayout(position);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();

        adapter.addItem(new NoteDTO(0,
                                0,
                                "test@test.com",
                                "0",
                                "광주시 서구 광천동",
                                "35",
                                "127",
                                "일",
                                "2020",
                                "10",
                                    "04",
                                "295",
                                "1014",
                                "25",
                                "20",
                                    "여수 출조 테스트 글입니다.",
                                    "0",
                                    "capture.jpg",
                                    "10월15일"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                NoteDTO item = adapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨 : " + item.getContents(), Toast.LENGTH_SHORT).show();
            }
        });



    }//initUI
}