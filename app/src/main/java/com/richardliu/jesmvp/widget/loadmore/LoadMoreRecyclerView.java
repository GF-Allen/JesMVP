package com.richardliu.jesmvp.widget.loadmore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.richardliu.jesmvp.R;


/**
 * Created by Allen on 2017/4/19.
 */

public class LoadMoreRecyclerView extends RecyclerView {

    private boolean isLoadingMore = false;//是否正在加载更多
    private OnLoadMoreListener loadMoreListener;//加载数据监听
    private boolean loadMoreEnable = false;//是否允许加载更多
    private boolean footer_visible = false;//脚部是否可以见
    private boolean hasFooter = false;

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == SCROLL_STATE_SETTLING)
                    if (getAdapter() != null && getLayoutManager() != null) {
                        int lastVisiblePosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                        int itemCount = getAdapter().getItemCount();
                        if (distanceY < 0 && itemCount != 0 && lastVisiblePosition >= itemCount - 1 && !isLoadingMore && loadMoreEnable) {
                            //正在加载更多
                            loading();
                            if (hasFooter) {//有脚布局
                                //显示脚布局
                                footer_visible = true;
                                getAdapter().notifyItemChanged(itemCount - 1);
                            }
                            if (loadMoreListener != null) {
                                loadMoreListener.loadMoreListener();
                            }
                        }
                    }
            }
        });
    }

    /**
     * 判断滑动方向
     */
    private float distanceY = 0;
    float startY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = y - startY;
                startY = y;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        AutoLoadAdapter autoLoadAdapter = new AutoLoadAdapter(adapter);
        super.setAdapter(autoLoadAdapter);
    }

    /**
     * 设置是否允许加载更多
     *
     * @param isEnable
     */
    public void setLoadMoreEnable(boolean isEnable) {
        this.loadMoreEnable = isEnable;
    }


    /**
     * 加载完成
     */
    private void loadMoreComplete() {
        this.isLoadingMore = false;
    }

    /**
     * 正在刷新
     */
    private void loading() {
        this.isLoadingMore = true;//设置正在刷新
    }

    /**
     * 加载更多数据回调
     *
     * @param listener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

    public void isHasFooter(boolean hasFooter) {
        this.hasFooter = hasFooter;
    }

    public interface OnLoadMoreListener {
        void loadMoreListener();//上拉刷新
    }


    /**
     * 刷新数据
     */
    public void notifyData() {
        if (getAdapter() != null) {
            loadMoreComplete();
            if (hasFooter && loadMoreEnable) {
                //隐藏脚布局
                footer_visible = false;
            }
            getAdapter().notifyDataSetChanged();

        }
    }

    public class AutoLoadAdapter extends Adapter<ViewHolder> {
        private Adapter dataAdapter;//数据adapter
        private final int TYPE_FOOTER = Integer.MAX_VALUE;//底部布局

        public AutoLoadAdapter(Adapter adapter) {
            this.dataAdapter = adapter;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1 && loadMoreEnable && hasFooter && footer_visible) {
                return TYPE_FOOTER;
            }
            if (dataAdapter.getItemViewType(position) == TYPE_FOOTER) {
                throw new RuntimeException("adapter中itemType不能为:" + Integer.MAX_VALUE);
            }
            return dataAdapter.getItemViewType(position);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = null;
            if (viewType == TYPE_FOOTER) {//脚部
                holder = new FooterViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_footer, parent, false));
            } else {//数据
                holder = dataAdapter.onCreateViewHolder(parent, viewType);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int itemViewType = getItemViewType(position);
            if (itemViewType != TYPE_FOOTER) {
                dataAdapter.onBindViewHolder(holder, position);
            } else {
                ((FooterViewHolder) holder).progressBar.setColorSchemeResources(R.color.google_blue,
                        R.color.google_green,
                        R.color.google_red,
                        R.color.google_yellow);
            }
        }

        @Override
        public int getItemCount() {
            if (dataAdapter.getItemCount() != 0) {
                int count = dataAdapter.getItemCount();
                if (loadMoreEnable && hasFooter && footer_visible) {
                    count++;
                }
                return count;
            }
            return 0;
        }

        public class FooterViewHolder extends ViewHolder {

            SwipeProgressBar progressBar;

            public FooterViewHolder(View itemView) {
                super(itemView);
                progressBar = (SwipeProgressBar) itemView.findViewById(R.id.progress);
            }
        }

    }
}
