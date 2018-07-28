package com.team3.uniton.unitonapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TutorialThreeFragment extends Fragment {

//    Button button = getView().findViewById( R.id.startBtn );

    public TutorialThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

//        Button button = getView().findViewById( R.id.startBtn );
//        button.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent( getActivity(), LoginActivity.class );
//                startActivity( intent );
//            }
//        } );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Button button;
        View view = inflater.inflate( R.layout.fragment_tutorial_three, container, false );

        button = view.findViewById( R.id.startBtn );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), LoginActivity.class );
                startActivity( intent );
            }
        } );
        return view;
    }
}
