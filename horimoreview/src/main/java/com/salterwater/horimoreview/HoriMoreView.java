package com.salterwater.horimoreview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.salterwater.horimoreview.dragview.BezierFooterDrawer;
import com.salterwater.horimoreview.dragview.IDragChecker;
import com.salterwater.horimoreview.dragview.DragContainer;
import com.salterwater.horimoreview.recyclerview.HRecyclerView;

public class HoriMoreView extends DragContainer {
    HRecyclerView recyclerView;
    Context context;
    boolean candrag = false;

    public HoriMoreView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public HoriMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public HoriMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        recyclerView = new HRecyclerView(context, attrs, defStyleAttr);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(recyclerView);
        this.setFooterDrawer(new BezierFooterDrawer.Builder(context,
                Color.parseColor("#EEEEEE"))
                .setIconDrawable(null)
                .setTextColor(Color.parseColor("#999999"))
                .setTextSize(10)
                .setRectFooterThick(0)
                .setBezierDragThreshold(80)
                .setNormalString("More")
                .setEventString("Release")
                .build());
        setIDragChecker(new IDragChecker() {
            @Override
            public boolean canDrag(View childView) {
                return recyclerBottom((RecyclerView) childView) && getDragListener() != null;
            }
        });
    }

    public void setHoriMoreViewDelegate(HRecyclerView.HoriMoreViewDelegate horiMoreViewDelegate) {
        recyclerView.setHoriMoreViewDelegate(horiMoreViewDelegate);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setContentView(recyclerView);
    }

    private boolean recyclerBottom(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            return false;
        }

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int lastCompletelyVisibleItemPosition;
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof LinearLayoutManager) {
            lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(into);
            int max = into[0];
            for (int value : into) {
                if (value > max) {
                    max = value;
                }
            }
            lastCompletelyVisibleItemPosition = max;
        } else {
            throw new IllegalArgumentException("still not support other LayoutManager");
        }

        return lastCompletelyVisibleItemPosition == adapter.getItemCount() - 1;
    }
}
