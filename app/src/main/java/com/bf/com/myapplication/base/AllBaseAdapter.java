package com.bf.com.myapplication.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public abstract class AllBaseAdapter<T> extends RecyclerView.Adapter<AllBaseAdapter.MyViewHolder> {
    private List<T> data;
    private onItemClickListaner listaner;

    public AllBaseAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //查找对应Item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AllBaseAdapter.MyViewHolder holder, final int position) {
        //设置数据
        mset(holder,data.get(position));
        //item设置的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaner!=null){
                    listaner.onItemClick(v,position);
                }
            }
        });
    }
    //传过来的数据个数
    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public abstract int getLayoutId() ;


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
        //将ID的强转写在这里
        protected <E extends View> E getViewById(@IdRes int viewId) {
            return (E) itemView.findViewById(viewId);
        }
        //设置数据
        public void setText(@IdRes int id,String context){
            TextView textView= getViewById(id);
            textView.setText(context);
        }
        public void setIoce(@IdRes int id, Drawable ioce){
            ImageView imageView=getViewById(id);
            imageView.setImageDrawable(ioce);
        }
        //。。。。。可以设置所有的控件，用时直接点
    }
    //viewHolder重写承接,setData代表数组中的东西
    public abstract void mset(MyViewHolder holder,Object setData);

    //自己定义一个点击监听的接口
    public interface onItemClickListaner{
        void onItemClick(View itemView,int position);
    }
    //设置监听，用传进来的listaner代替它的listaner
    public void setOnItemClick(onItemClickListaner listaner){
        this.listaner = listaner;
    }

}
