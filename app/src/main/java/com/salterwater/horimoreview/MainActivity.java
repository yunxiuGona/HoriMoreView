package com.salterwater.horimoreview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.salterwater.horimoreview.dragview.DragListener;
import com.salterwater.horimoreview.recyclerview.HRecyclerView;

public class MainActivity extends AppCompatActivity implements DragListener {

    HoriMoreView hmv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hmv=(HoriMoreView)findViewById(R.id.hmv);
        hmv.setDragListener(this);
        hmv.setHoriMoreViewDelegate(new HRecyclerView.HoriMoreViewDelegate() {
            @Override
            public int getCount() {
                return 6;
            }

            @Override
            public int getView(@NonNull ViewGroup parent, int position) {
                if(position==5) {
                    return R.layout.adp_rv1;
                }else{
                    return R.layout.adp_rv2;
                }
            }
            @Override
            public void bindView(View view, int position) {
                //view.setText  view.setOnClickListener  view.setVisivle  etc...
            }
        });
    }

    @Override
    public void onDragEvent() {
        Toast.makeText(this, "Drag Event", Toast.LENGTH_SHORT).show();
    }
}
