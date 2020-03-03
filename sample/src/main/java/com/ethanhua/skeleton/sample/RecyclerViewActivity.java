package com.ethanhua.skeleton.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.ethanhua.skeleton.sample.adapter.NewsAdapter;
import com.ethanhua.skeleton.sample.adapter.PersonAdapter;

/**
 * Created by ethanhua on 2017/7/27.
 */

public class RecyclerViewActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_LINEAR = "type_linear";
    public static final String TYPE_GRID = "type_grid";
    private String mType;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mType = getIntent().getStringExtra(PARAMS_TYPE);
        init();
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (TYPE_LINEAR.equals(mType)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            NewsAdapter adapter = new NewsAdapter();
            final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .shimmer(true)
                    .angle(20)
                    .frozen(false)
                    .duration(1200)
                    .count(10)
                    .load(R.layout.item_skeleton_news)
                    .show(); //default count is 10
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    skeletonScreen.hide();
                }
            }, 3000);
            return;
        }
        if (TYPE_GRID.equals(mType)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            PersonAdapter adapter = new PersonAdapter();
            final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .load(R.layout.item_skeleton_person)
                    .shimmer(false)
                    .show();
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    skeletonScreen.hide();
                }
            }, 3000);
        }
    }

}

