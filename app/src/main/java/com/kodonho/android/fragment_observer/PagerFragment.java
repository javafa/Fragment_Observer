package com.kodonho.android.fragment_observer;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {
    ViewPager pager;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        pager = (ViewPager)view.findViewById(R.id.pager);
        return view;
    }
}

class ViewPagerAdapter extends PagerAdapter {
    Data data;
    Context context;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, Data data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.getCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // return super.instantiateItem(container, position);
        View view = inflater.inflate(R.layout.pager_item, container, false);
        TextView title = (TextView) view.findViewById(R.id.textView);
        title.setText("제목");
        //
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
