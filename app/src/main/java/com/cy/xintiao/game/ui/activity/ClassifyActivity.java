package com.cy.xintiao.game.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.ui.BaseActivity;
import com.cy.xintiao.game.ui.adapter.ClassifyAdapter;
import com.cy.xintiao.game.ui.view.ClassifyTagGridLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类
 */
@ContentView(R.layout.activity_classify)
public class ClassifyActivity extends BaseActivity {
    @Event(R.id.backLl)
    private void onBackClick(View v) {
        finish();
    }

    @ViewInject(R.id.networkGv)
    private ClassifyTagGridLayout networkGv;
    private ClassifyAdapter networkAdapter;
    private List<String> networkInfos = new ArrayList<>();

    @ViewInject(R.id.consoleGv)
    private ClassifyTagGridLayout consoleGv;
    private ClassifyAdapter consoleAdapter;
    private List<String> consoleInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("分类");
        networkInfos.add("策略养成");
        networkInfos.add("角色冒险");
        networkInfos.add("卡牌游戏");
        networkInfos.add("益智休闲");
        networkAdapter = new ClassifyAdapter(networkInfos, this);
        networkGv.setAdapter(networkAdapter);

        consoleInfos.add("动作冒险");
        consoleInfos.add("益智休闲");
        consoleInfos.add("体育竞速");
        consoleInfos.add("飞行射击");
        consoleInfos.add("经营策略");
        consoleInfos.add("棋牌天地");
        consoleInfos.add("角色扮演");
        consoleInfos.add("塔防游戏");
        consoleAdapter = new ClassifyAdapter(consoleInfos,this);
        consoleGv.setAdapter(consoleAdapter);
    }

}
