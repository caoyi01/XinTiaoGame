package com.cy.xintiao.game.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.ui.BaseActivity;
import com.cy.xintiao.game.ui.view.fab.FloatingActionButton;
import com.cy.xintiao.game.ui.view.fab.FloatingActionsMenu;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * APP详情页面
 */
@ContentView(R.layout.activity_app_detail)
public class AppDetailActivity extends BaseActivity {
    @Event(R.id.backLl)
    private void onBackClick(View v) {
        finish();
    }

    @ViewInject(R.id.editFab)
    private FloatingActionsMenu editFab;
    //下载
    @ViewInject(R.id.downloadFab)
    private FloatingActionButton downloadFab;

    @Event(R.id.downloadFab)
    private void onDownloadFabClick(View v) {
        editFab.toggle();
    }

    //同步到电脑
    @ViewInject(R.id.syncFab)
    private FloatingActionButton syncFab;

    @Event(R.id.syncFab)
    private void onSyncFabClick(View v) {
        editFab.toggle();
    }

    //礼包
    @ViewInject(R.id.giftFab)
    private FloatingActionButton giftFab;

    @Event(R.id.giftFab)
    private void onGiftFabClick(View v) {
        editFab.toggle();
    }

    //简介
    @ViewInject(R.id.desFab)
    private FloatingActionButton desFab;

    @Event(R.id.desFab)
    private void onDexFabClick(View v) {
        editFab.toggle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("详情");
    }

}
