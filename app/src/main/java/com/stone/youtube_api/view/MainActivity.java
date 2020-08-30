package com.stone.youtube_api.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.stone.youtube_api.R;
import com.stone.youtube_api.adapter.PageChangeAdapter;


public class MainActivity extends AppCompatActivity {
@BindView(R.id.toolbar)
Toolbar toolbar;
@BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
@BindView(R.id.viewpager)
    ViewPager pager;
PageChangeAdapter pageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        pageAdapter=new PageChangeAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new AllVideoFragment(),"Videos");
        pageAdapter.addFragment(new PlayListFragment(),"PlayList");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pageAdapter);
       // viewPager.setOffscreenPageLimit(6);

        // Give the TabLayout the ViewPager

        tabLayout.setupWithViewPager(viewPager);
    }


}
