package com.richardliu.jesmvp.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by allen on 2017/4/11.
 */

public abstract class CJSBaseAdapter<T> extends BaseAdapter {

    protected final LayoutInflater mInflater;
    protected final Context mContext;
    protected List<T> mDataList;

    public CJSBaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDataList = new ArrayList<>();
    }

    public void setDataList(List<T> datas) {
        if (datas != null) {
            this.mDataList.clear();
            this.mDataList.addAll(datas);
            this.notifyDataSetChanged();
        }
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

}