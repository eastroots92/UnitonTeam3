package com.team3.uniton.unitonapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_tutorial_three, container, false );
    }





}
