package com.salterwater.horimoreview.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salterwater.horimoreview.R;

public class HRecyclerView extends RecyclerView{

    private Context context;
    private LinearLayoutManager manager;
    private InnerAdapter adapter;
    private HoriMoreViewDelegate horiMoreViewDelegate;
    public HRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public HRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context){
        this.context=context;
        manager=new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.setLayoutManager(manager);

        adapter=new InnerAdapter();
        setAdapter(adapter);
    }

    public void setHoriMoreViewDelegate(HoriMoreViewDelegate horiMoreViewDelegate) {
        this.horiMoreViewDelegate = horiMoreViewDelegate;
    }

    private class InnerAdapter extends RecyclerView.Adapter<DefaultHolder>{
        @NonNull
        @Override
        public DefaultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(horiMoreViewDelegate!=null){
                View view = LayoutInflater.from(parent.getContext()).inflate(horiMoreViewDelegate.getView(parent,viewType), parent, false);
                return new DefaultHolder(view);
            }else{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hnv_empty, parent, false);
                return new DefaultHolder(view);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(@NonNull DefaultHolder holder, int position) {
            if(horiMoreViewDelegate!=null){
                horiMoreViewDelegate.bindView(holder.itemView,position);
            }
        }

        @Override
        public int getItemCount() {
            if(horiMoreViewDelegate!=null){
                return horiMoreViewDelegate.getCount();
            }
            return 0;
        }
    }
    public static class DefaultHolder extends RecyclerView.ViewHolder{

        public DefaultHolder(View itemView) {
            super(itemView);
        }
    }

    public interface HoriMoreViewDelegate{
        public int getCount();
        public int getView(@NonNull ViewGroup parent, int position);
        public void bindView(View view, int position);
    }
}
