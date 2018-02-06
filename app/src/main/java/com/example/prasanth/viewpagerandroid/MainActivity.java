package com.example.prasanth.viewpagerandroid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView[] ivArrayDotsPager;
    private LinearLayout llPagerDots;
    private int[] images = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third
    };
    private TextView prevBtn, nextBtn;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llPagerDots = findViewById(R.id.dotLayout);

        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);

        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);

        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapterClass viewPagerAdapterClass = new ViewPagerAdapterClass(this, images);
        viewPager.setAdapter(viewPagerAdapterClass);
        
        setUpPagerIndicators();
        ivArrayDotsPager[0].setImageResource(R.drawable.selected_drawable);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == ivArrayDotsPager.length - 1)
                    nextBtn.setText("GOT IT");
                else
                    nextBtn.setText("NEXT");
                if (position != 0)
                    prevBtn.setVisibility(View.VISIBLE);
                else
                    prevBtn.setVisibility(View.INVISIBLE);

                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.unselected_drawable);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_drawable);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setUpPagerIndicators() {
        ivArrayDotsPager = new ImageView[3];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(layoutParams);
            ivArrayDotsPager[i].setImageResource(R.drawable.unselected_drawable);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //sets opacity
                    //1 means completely opaque.0 means transparent
                    view.setAlpha(1);
                }
            });
            llPagerDots.addView(ivArrayDotsPager[i]);
            llPagerDots.bringToFront();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if (viewPager.getCurrentItem() < ivArrayDotsPager.length - 1)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                break;
            case R.id.prevBtn:
                if (viewPager.getCurrentItem() != 0)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            default:
                break;
        }
    }
}
