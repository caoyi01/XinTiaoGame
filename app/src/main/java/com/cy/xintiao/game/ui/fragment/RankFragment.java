package com.cy.xintiao.game.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.entity.DownloadInfo;
import com.cy.xintiao.game.ui.BaseFragment;
import com.cy.xintiao.game.ui.activity.AppDetailActivity;
import com.cy.xintiao.game.ui.adapter.RankAdapter;
import com.cy.xintiao.game.ui.view.refresh.PtrClassicFrameLayout;
import com.cy.xintiao.game.ui.view.refresh.PtrDefaultHandler;
import com.cy.xintiao.game.ui.view.refresh.PtrFrameLayout;
import com.cy.xintiao.game.ui.view.refresh.PtrHandler;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.cy.xintiao.game.ui.fragment
 * <p/>
 * 作者：CaoYi on 2016/2/1 19:01
 * <p/>
 * 描述：
 */
public class RankFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RankFragment newInstance(String param1) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @ViewInject(R.id.rankPtrView)
    private PtrClassicFrameLayout mPtrFrame;
    @ViewInject(R.id.rankLv)
    private ListView rankLv;
    private RankAdapter rankAdapter;
    private List<DownloadInfo> infos = new ArrayList<>();
    // 加载中
    private boolean loading = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        x.view().inject(this, view);
        initRefreshView();
        rankAdapter = new RankAdapter(infos, getContext());
        rankLv.setAdapter(rankAdapter);
        rankLv.setOnItemClickListener(this);
        addListViewData();
        rankAdapter.notifyDataSetChanged();
        return view;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mPtrFrame.refreshComplete();
                    break;
            }
        }
    };

    private void initRefreshView() {
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (loading) {//如果加载中，取消加载
                    frame.refreshComplete();
                    return;
                }
                mHandler.sendEmptyMessageDelayed(0, 1000 * 2);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
    }

    private void addListViewData() {
        for (int i = 0; i < 10; i++) {
            DownloadInfo downloadInfo = new DownloadInfo();
            downloadInfo.setAppsum("官方正版同名手游");
            downloadInfo.setCountdown("689万次下载");
            downloadInfo.setLabel(mParam1 + i);
            downloadInfo.setSize("200M");
            infos.add(downloadInfo);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goActivity(AppDetailActivity.class);
    }
}
