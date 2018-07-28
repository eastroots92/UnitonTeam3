package com.team3.uniton.unitonapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TutorialThreeFragment extends Fragment{
    private View view;
    private Button m_btn_start;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_tutorial_three, container, false );
        m_btn_start = view.findViewById(R.id.btn_start);
        startLoginActivity();

        return view;
    }


    private void startLoginActivity() {
        m_btn_start.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( getActivity(), LoginActivity.class ));
                getActivity().finish();
            }
        });
    }
}
