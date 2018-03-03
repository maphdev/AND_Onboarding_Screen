package com.example.manon.onboardingscreen;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;

    private TextView[] dots;

    private SliderAdapter sliderAdapter;

    private Button backButton;
    private Button nextButton;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideViewPager = findViewById(R.id.slideViewPager);
        dotsLayout = findViewById(R.id.dots);

        backButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        sliderAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage-1);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideViewPager.setCurrentItem(currentPage+1);
            }
        });
    }

    private void addDotsIndicator(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorGray));
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (currentPage == 0){
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);
                nextButton.setText(getResources().getText(R.string.next));
                backButton.setText(getResources().getText(R.string.nothing));
            } else if (position == dots.length -1) {
                nextButton.setEnabled(false);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText(getResources().getText(R.string.finish));
                backButton.setText(getResources().getText(R.string.back));
            } else  {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);
                nextButton.setText(getResources().getText(R.string.next));
                backButton.setText(getResources().getText(R.string.back));
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
