package com.bf.com.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.entity.IntentEntiy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class XListAdapter extends BaseAdapter {
    private List<IntentEntiy> datas;

    public XListAdapter(List<IntentEntiy> datas) {

        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHordler hordler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, null);
            hordler = new ViewHordler(convertView);
            convertView.setTag(hordler);
        } else {
            hordler = (ViewHordler) convertView.getTag();
        }
        IntentEntiy listBean=datas.get(position);
        hordler.tvMainItemTitle.setText(listBean.getTitle());
        hordler.imgMainItem.setImageBitmap(listBean.getBitmap());
        hordler.tvMainItemTime.setText(listBean.getTime());
        return hordler.convertView;
    }

    public class ViewHordler {
        private View convertView;
        @BindView(R.id.img_main_item)
        ImageView imgMainItem;
        @BindView(R.id.tv_main_item_title)
        TextView tvMainItemTitle;
        @BindView(R.id.tv_main_item_time)
        TextView tvMainItemTime;

        public ViewHordler(View convertView) {
            ButterKnife.bind(this, convertView);
            this.convertView = convertView;
        }
    }
}
