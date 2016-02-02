package com.cy.xintiao.game.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.ui.BaseFragmentActivity;
import com.cy.xintiao.game.ui.adapter.MyFragmentPagerAdapter;
import com.cy.xintiao.game.ui.fragment.RankFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 排行
 */
@ContentView(R.layout.activity_rank)
public class RankActivity extends BaseFragmentActivity {
    @Event(R.id.backLl)
    private void onBackClick(View v) {
        finish();
    }

    @ViewInject(R.id.rankLl)
    private LinearLayout rankLl;
    @ViewInject(R.id.rankVp)
    private ViewPager rankVp;

    private List<HashMap<String, Object>> channels = new ArrayList<>();

    private ArrayList<Fragment> rankFragments = new ArrayList<>();

    private String selectTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("排行");
        InitChannelData();
        InitRankTitleView();
        InitRankFragment();
        setSelect("下载榜");
    }

    private void InitChannelData() {
        channels.clear();
        channels.add(addChannel(R.drawable.icon_download_click, R.drawable.icon_download_normal, "下载榜"));
        channels.add(addChannel(R.drawable.icon_newgame_click, R.drawable.icon_newgame_normal, "新游榜"));
        channels.add(addChannel(R.drawable.icon_consolegame_click, R.drawable.icon_consolegame_normal, "单机榜"));
        channels.add(addChannel(R.drawable.icon_man_click, R.drawable.icon_man_normal, "男生榜"));
        channels.add(addChannel(R.drawable.icon_woman_click, R.drawable.icon_woman_normal, "女生榜"));
    }

    private HashMap<String, Object> addChannel(int selectImageId, int unSelectImageId, String titleText) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("selectImgId", selectImageId);
        hashMap.put("unSelectImgId", unSelectImageId);
        hashMap.put("titleText", titleText);
        if (titleText.equals(selectTitle)) {
            hashMap.put("isSelect", true);
        } else {
            hashMap.put("isSelect", false);
        }
        return hashMap;
    }

    private void InitRankTitleView() {
        rankLl.removeAllViews();
        for (HashMap<String, Object> channel :
                channels) {
            int selectImageId = (int) channel.get("selectImgId");
            int unSelectImageId = (int) channel.get("unSelectImgId");
            String titleText = (String) channel.get("titleText");
            boolean isSelect = (boolean) channel.get("isSelect");
            rankLl.addView(createRankTitleView(selectImageId, unSelectImageId, titleText, isSelect));
        }
    }

    private View createRankTitleView(int selectImageId, int unSelectImageId, final String titleText, boolean isSelect) {
        View titleView = LayoutInflater.from(this).inflate(R.layout.item_rank_title_view, null);
        ImageView rankTitleIv = (ImageView) titleView.findViewById(R.id.rankTitleIv);
        TextView rankTitleTv = (TextView) titleView.findViewById(R.id.rankTitleTv);
        rankTitleTv.setText(titleText);
        if (isSelect) {
            rankTitleIv.setBackgroundResource(selectImageId);
            rankTitleTv.setTextColor(Color.parseColor("#44A4F3"));
        } else {
            rankTitleIv.setBackgroundResource(unSelectImageId);
            rankTitleTv.setTextColor(Color.parseColor("#252525"));
        }
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect(titleText);
            }
        });
        return titleView;
    }

    private void setSelect(String title) {
        selectTitle = title;
        InitChannelData();
        InitRankTitleView();
    }

    private void InitRankFragment() {
        rankFragments.clear();// 清空
        int count = channels.size();
        for (int i = 0; i < count; i++) {
            RankFragment fragment = RankFragment.newInstance(channels.get(i).get("titleText")+"");
            rankFragments.add(fragment);
        }
        MyFragmentPagerAdapter mAdapetr = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mAdapetr.setFragments(rankFragments);
        // mViewPager.setOffscreenPageLimit(0);
        rankVp.setAdapter(mAdapetr);
        rankVp.setOnPageChangeListener(pageListener);
    }
    /**
     * ViewPager切换监听方法
     * */
    public ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            rankVp.setCurrentItem(position);
//            selectTab(position);
            String title = channels.get(position).get("titleText") + "";
            setSelect(title);
        }
    };
}