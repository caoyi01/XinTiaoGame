package com.cy.xintiao.game.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.ui.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * APP详情页面
 */
@ContentView(R.layout.activity_app_detail)
public class AppDetailActivity extends BaseActivity {
    @Event(R.id.backLl)
    private void onBackClick(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("详情");
    }

}
