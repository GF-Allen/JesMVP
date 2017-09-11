package com.richardliu.jesmvp.widget.loadmore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.richardliu.jesmvp.R;


/**
 * Created by Allen on 2017/4/22.
 */

public class RefreshLoadMoreView extends LinearLayout {

    private SwipeRefreshLayout refreshLayout;
    private LoadMoreRecyclerView rvContent;

    public RefreshLoadMoreView(Context context) {
        this(context, null);
    }

    public RefreshLoadMoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLoadMoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.custom_refresh_load_more, this);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        rvContent = (LoadMoreRecyclerView) view.findViewById(R.id.rv_refresh_content);
        refreshLayout.setColorSchemeResources(R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
            }
        });
        rvContent.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.loadMoreListener();
                }
            }
        });
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        rvContent.setAdapter(adapter);
    }

    /**
     * 加载更多手动刷新数据
     */
    public void notifyData() {
        setRefreshing(false);
        rvContent.notifyData();
    }

    public void setRefreshing(boolean refreshing) {
        refreshLayout.setRefreshing(refreshing);
    }

    /**
     * 设置是否允许加载更多
     *
     * @param isEnable
     */
    public void setLoadMoreEnable(boolean isEnable) {
        rvContent.setLoadMoreEnable(isEnable);
    }

    public void isHasFooter(boolean hasFooter) {
        rvContent.isHasFooter(hasFooter);
    }

    private OnRefreshListener onRefreshListener;

    public void setLayoutManager(LinearLayoutManager linearLayoutManager) {
        rvContent.setLayoutManager(linearLayoutManager);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    private OnLoadMoreListener onLoadMoreListener;

    public interface OnLoadMoreListener {
        void loadMoreListener();//上拉刷新
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }
}
