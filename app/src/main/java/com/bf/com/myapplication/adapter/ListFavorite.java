package com.bf.com.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bf.com.myapplication.R;
import com.bf.com.myapplication.entity.DBfavorite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class ListFavorite extends BaseAdapter{
    private List<DBfavorite> datas;

    public ListFavorite(List<DBfavorite> datas) {

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
        ViewHolder hordler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, null);
            hordler = new  ViewHolder(convertView);
            convertView.setTag(hordler);
        } else {
            hordler = ( ViewHolder) convertView.getTag();
        }
        DBfavorite listBean = datas.get(position);
        hordler.tvItemFavorite.setText(listBean.getTitle());
        return hordler.convertView;
    }

    public  class ViewHolder {
        @BindView(R.id.tv_item_favorite)
        TextView tvItemFavorite;
        private View convertView;

        ViewHolder(View convertView) {
            this.convertView = convertView;
            ButterKnife.bind(this, convertView);
        }
    }
}
