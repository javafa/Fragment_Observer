package com.kodonho.android.fragment_observer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PagerFragment extends Fragment {
    public static ArrayList<MusicData> datas = new ArrayList<>();
    ViewPager pager;

    public PagerFragment() {
        datas = getDatas();
    }

    public ArrayList<MusicData> getDatas(){
        ArrayList<MusicData> datas = new ArrayList<>();
        for(int i=0;i<30;i++){
            MusicData data = new MusicData();
            data.title = "테스트" +i;
            data.artist ="가수이름"+i;
            datas.add(data);
        }
        return datas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pager, container, false);

        pager = (ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(inflater);
        pager.setAdapter(adapter);

        return view;
    }

    class ViewPagerAdapter extends PagerAdapter {
        private final static String TAG = "ViewPagerAdapter";
        LayoutInflater inflater;

        public ViewPagerAdapter(LayoutInflater inflater){
             this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // return super.instantiateItem(container, position);
            View view = inflater.inflate(R.layout.pager_item, null);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView signer = (TextView) view.findViewById(R.id.singer);
            ImageView image = (ImageView) view.findViewById(R.id.imageView);
            MusicData data = datas.get(position);
            Log.i(TAG,"data title="+data.title);
            title.setText(data.title);
            signer.setText(data.artist);
            // ViewPager를 만든 View에 추가
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object){
            container.removeView((View)object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}


