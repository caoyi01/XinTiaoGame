package com.cy.xintiao.game.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.ui.BaseFragmentActivity;
import com.cy.xintiao.game.ui.fragment.AssistFragment;
import com.cy.xintiao.game.ui.fragment.GameFragment;
import com.cy.xintiao.game.ui.fragment.SearchFragment;
import com.cy.xintiao.game.ui.fragment.UserFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {
    @ViewInject(R.id.gameIv)
    private ImageView gameIv;
    @ViewInject(R.id.gameTv)
    private TextView gameTv;

    @Event(R.id.gameLl)
    private void onGameClick(View v) {
        setTabSelection(0);
    }

    @ViewInject(R.id.searchIv)
    private ImageView searchIv;
    @ViewInject(R.id.searchTv)
    private TextView searchTv;

    @Event(R.id.searchLl)
    private void onSearchClick(View v) {
        setTabSelection(1);
    }

    @ViewInject(R.id.assistIv)
    private ImageView assistIv;
    @ViewInject(R.id.assistTv)
    private TextView assistTv;

    @Event(R.id.assistLl)
    private void onAssistClick(View v) {
        setTabSelection(2);
    }

    @ViewInject(R.id.userIv)
    private ImageView userIv;
    @ViewInject(R.id.userTv)
    private TextView userTv;

    @Event(R.id.userLl)
    private void onUserClick(View v) {
        setTabSelection(3);
    }

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    private GameFragment gameFragment;
    private SearchFragment searchFragment;
    private AssistFragment assistFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index 每个tab页对应的下标
     */
    private void setTabSelection(int index) {
        gameIv.setBackgroundResource(R.drawable.icon_game_normal);
        gameTv.setTextColor(getResources().getColor(R.color.main_tab_default));

        searchIv.setBackgroundResource(R.drawable.icon_search_normal);
        searchTv.setTextColor(getResources().getColor(R.color.main_tab_default));

        assistIv.setBackgroundResource(R.drawable.icon_assist_normal);
        assistTv.setTextColor(getResources().getColor(R.color.main_tab_default));

        userIv.setBackgroundResource(R.drawable.icon_me_normal);
        userTv.setTextColor(getResources().getColor(R.color.main_tab_default));

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:// 游戏
                gameIv.setBackgroundResource(R.drawable.icon_game_click);
                gameTv.setTextColor(getResources().getColor(R.color.main_tab_select));
                if (gameFragment == null) {
                    gameFragment = GameFragment.newInstance("", "");
                    transaction.add(R.id.main_content, gameFragment);
                } else {
                    transaction.show(gameFragment);
                }
                break;
            case 1:// 搜索
                searchIv.setBackgroundResource(R.drawable.icon_search_click);
                searchTv.setTextColor(getResources().getColor(R.color.main_tab_select));
                if (searchFragment == null) {
                    searchFragment = SearchFragment.newInstance("", "");
                    transaction.add(R.id.main_content, searchFragment);
                } else {
                    transaction.show(searchFragment);

                }
                break;
            case 2:// 辅助
                assistIv.setBackgroundResource(R.drawable.icon_assist_click);
                assistTv.setTextColor(getResources().getColor(R.color.main_tab_select));
                if (assistFragment == null) {
                    assistFragment = AssistFragment.newInstance("", "");
                    transaction.add(R.id.main_content, assistFragment);
                } else {
                    transaction.show(assistFragment);

                }
                break;
            case 3:// 用户
                userIv.setBackgroundResource(R.drawable.icon_me_click);
                userTv.setTextColor(getResources().getColor(R.color.main_tab_select));
                if (userFragment == null) {
                    userFragment = UserFragment.newInstance("", "");
                    transaction.add(R.id.main_content, userFragment);
                } else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (gameFragment != null) {
            transaction.hide(gameFragment);
        }
        if (searchFragment != null) {
            transaction.hide(searchFragment);
        }
        if (assistFragment != null) {
            transaction.hide(assistFragment);
        }
        if (userFragment != null) {
            transaction.hide(userFragment);
        }
    }

    private boolean isExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                showToast("再按一次退出程序");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        isExit = false;
                    }
                }, 1000);
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
