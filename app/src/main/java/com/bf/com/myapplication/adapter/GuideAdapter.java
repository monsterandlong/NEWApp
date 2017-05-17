package com.bf.com.myapplication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bf.com.myapplication.R;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class GuideAdapter extends PagerAdapter {
    int[] draw={R.mipmap.guide1,R.mipmap.guide2,R.mipmap.guide3};
    @Override
    public int getCount() {
        return draw.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.guideitem, null);
        ImageView img= (ImageView) view.findViewById(R.id.img_item_guide);
        img.setBackgroundResource(draw[position]);
        //将图片控件添加到引导界面
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
