package com.geektech.notesapp.presentation.intro;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.badoualy.stepperindicator.StepperIndicator;
import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.main.MainActivity;

public class IntroActivity extends AppCompatActivity
    implements View.OnClickListener {

    private IntroPagerAdapter mIntroAdapter;
    private ViewPager mViewPager;
    private StepperIndicator mStepper;

    private TextView mNextBtn;
    private Button mSkipBtn;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, IntroActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        init();
    }

    //region Init

    private void init() {
        initViewPager();

        mNextBtn = findViewById(R.id.intro_next_btn);
        mNextBtn.setOnClickListener(this);
        mSkipBtn = findViewById(R.id.skip_btn);
        mSkipBtn.setOnClickListener(this);

    }

    private void initViewPager() {
        mIntroAdapter = new IntroPagerAdapter(getSupportFragmentManager());

        mStepper = findViewById(R.id.intro_stepper);
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mIntroAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                onPageChanged(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        mStepper.setViewPager(mViewPager, mIntroAdapter.getCount());
    }

    //endregion

    private void onPageChanged(int position) {
        String btnText = "Next";
        if (position == 2) {
            btnText = "Finish";
        }
        mNextBtn.setText(btnText);
    }

    private void onNextClick() {
        if (mViewPager.getCurrentItem() == mIntroAdapter.getCount() - 1) {
            MainActivity.start(this);
            finish();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intro_next_btn:
                onNextClick();
                break;
            case R.id.skip_btn:
                MainActivity.start(this);
                finish();
                break;
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        private final int PAGES_COUNT = 3;

        public IntroPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //TODO: Return IntroFragment instance with target image url and title string id
        @Override
        public Fragment getItem(int position) {
            int index = position;
            int imageRes;
            int textRes;
            if (index == 0) {
                imageRes = R.drawable.fragment_first;
                textRes = R.string.fragment_first;
            } else if (index==1) {
                imageRes = R.drawable.fragment_second;
                textRes = R.string.fragment_second;
            } else{
                imageRes=R.drawable.fragment_third;
                textRes = R.string.fragment_third;
            }

            return IntroFragment.newInstance(index, imageRes, textRes);
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }

    public static class IntroFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_IMAGE_RES_ID = "image_res_id";
        private static final String ARG_TEXT_RES_ID = "text_res_id";

        public IntroFragment() {
        }

        public static IntroFragment newInstance(
                int sectionNumber,
                int imageID,
                @StringRes int textID
        ) {
            IntroFragment fragment = new IntroFragment();

            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_IMAGE_RES_ID, imageID);
            args.putInt(ARG_TEXT_RES_ID, textID);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_intro, container, false);

            initView(view);

            return view;
        }

        //TODO: Initialize all data from arguments
        private void initView(View rootView) {
            TextView textView = rootView.findViewById(R.id.section_label);
            ImageView imageView = rootView.findViewById(R.id.section_image);
            imageView.setImageResource(getArguments().getInt(ARG_IMAGE_RES_ID));
            textView.setText(getArguments().getInt(ARG_TEXT_RES_ID));
        }
    }
}
