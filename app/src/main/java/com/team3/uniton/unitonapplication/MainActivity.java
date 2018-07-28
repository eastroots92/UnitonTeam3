package com.team3.uniton.unitonapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rd.PageIndicatorView;

public class MainActivity extends AppCompatActivity {

    int MAX_PAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

//        ViewPager viewPager = findViewById( R.id.viewPager );
//        viewPager.setAdapter( new MainActivity.adapter(getSupportFragmentManager()) );
//        PageIndicatorView pageIndicatorView = findViewById( R.id.pageIndicatorView );
//        pageIndicatorView.setCount( 3 );
//        pageIndicatorView.setSelection( 1 );

        showTutorial();

//        Intent intent = new Intent( this, LoginActivity.class );
//        startActivity( intent );
    }

    public void showTutorial() {
        ViewPager viewPager = findViewById( R.id.viewPager );
        viewPager.setAdapter( new MainActivity.adapter(getSupportFragmentManager()) );
        PageIndicatorView pageIndicatorView = findViewById( R.id.pageIndicatorView );
        pageIndicatorView.setCount( 3 );
        pageIndicatorView.setSelection( 1 );
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
