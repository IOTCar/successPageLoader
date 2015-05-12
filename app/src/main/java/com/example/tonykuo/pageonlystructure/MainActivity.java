package com.example.tonykuo.pageonlystructure;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ViewPager viewPage;
    private MyPageAdapter adapter;
    private ArrayList<View> views;
    private String[] titles = {"One", "Two", "Three", "Four", "Five", "Six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPage = (ViewPager)findViewById(R.id.viewpage);
        views = new ArrayList<View>();
        int len = titles.length;

        //設置 ViewPager 顯示的頁面內容
        LayoutInflater inflater = getLayoutInflater();
        View view = null;
        TextView tv = null;
        for (int i = 0; i < len; i++){
            view = inflater.inflate(R.layout.pager, null);
            tv = (TextView)view.findViewById(R.id.textView1);
            tv.setText(titles[i]);
            views.add(view);
        }

        //建立一個 PagerAdapter
        adapter = new MyPageAdapter(titles, views);
        viewPage.setAdapter(adapter);


        //設定你的 PagerSlidingTabStrip
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        //將你的 ViewPager 傳遞給 PagerSlidingTabStrip
        tabs.setViewPager(viewPage);
        // 以下為選擇性的
        // 如果你需要設置 OnPageChangeListener，
        // 請用 tabs.setOnPageChangeListener(...)
        // 而不是 viewPage.setOnPageChangeListener(...)
//		tabs.setOnPageChangeListener(mPageChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyPageAdapter extends PagerAdapter {
        String[] titles;
        List<View> views;

        public MyPageAdapter(String[] titles, List<View> views){
            this.titles = titles;
            this.views = views;
        }

        @Override
        public int getCount(){
            return views == null ? 0 : views.size();
        }

        /**
         * 必要
         * PagerSlidingTabStrip 透過它來取得顯示在 Tabs 上的標題
         */
        @Override
        public CharSequence getPageTitle(int position){
            return titles[position]+"label";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position){
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView(views.get(position));
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1){
            return arg0 == arg1;
        }
    }
}
