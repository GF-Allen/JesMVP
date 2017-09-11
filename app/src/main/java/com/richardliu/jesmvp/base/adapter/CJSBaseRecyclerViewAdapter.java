package com.richardliu.jesmvp.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Created by allen on 2017/4/11.
 */

public abstract class CJSBaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> datas;
    protected OnItemClickListener<T> onItemClickListener;
    protected final Context mContext;
    protected final LayoutInflater mLayoutInflater;

    public CJSBaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.datas = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onObjectItemClicked(T t, int position);
    }

    public List<T> getDatas() {
        return datas;
    }

    public void clear() {
        if (datas != null && datas.size() > 0) {
            datas.clear();
            this.notifyDataSetChanged();
        }
    }

    public void setDatas(List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.clear();
        this.datas.addAll(datas);
//        this.notifyDataSetChanged();
    }

    public void addData(List<T> datas) {
        this.validateUsersCollection(datas);
        this.datas.addAll(datas);
//        this.notifyDataSetChanged();
    }

    private void validateUsersCollection(Collection<T> collection) {
        if (collection == null) {
            throw new RuntimeException("must set data");
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return doOnCreateViewHolder(parent, viewType);
    }

    protected abstract VH doOnCreateViewHolder(ViewGroup parent, int viewType);

    protected abstract void doOnBindViewHolder(VH holder, int position);

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        doOnBindViewHolder(holder, position);
        final T t = datas.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onObjectItemClicked(t, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.datas != null) ? this.datas.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
