package com.team3.uniton.unitonapplication.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.team3.uniton.unitonapplication.R;
import com.team3.uniton.unitonapplication.ui.fragment.TutorialOneFragment;
import com.team3.uniton.unitonapplication.ui.fragment.TutorialThreeFragment;
import com.team3.uniton.unitonapplication.ui.fragment.TutorialTwoFragment;

public class TutorialActivity extends AppCompatActivity {

    int MAX_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tutorial );

        showTutorial();
    }

    public void showTutorial() {
        ViewPager viewPager = findViewById( R.id.viewPager );
        viewPager.setAdapter( new TutorialActivity.adapter(getSupportFragmentManager()) );
    }

    private class adapter extends FragmentPagerAdapter {
        public adapter(FragmentManager fm) {
            super( fm );
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TutorialOneFragment();
                case 1:
                    return new TutorialTwoFragment();
                case 2:
                    return new TutorialThreeFragment();
//                case 3:
//                    Intent intent = new Intent( getApplicationContext(), LoginActivity.class );
//                    startActivity( intent );
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

}
