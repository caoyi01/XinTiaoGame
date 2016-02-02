package com.cy.xintiao.game.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.entity.GameEntity;
import com.cy.xintiao.game.ui.BaseFragment;
import com.cy.xintiao.game.ui.activity.AppDetailActivity;
import com.cy.xintiao.game.ui.activity.ClassifyActivity;
import com.cy.xintiao.game.ui.activity.RankActivity;
import com.cy.xintiao.game.ui.adapter.GameAdapter;
import com.cy.xintiao.game.ui.view.refresh.PtrClassicFrameLayout;
import com.cy.xintiao.game.ui.view.refresh.PtrDefaultHandler;
import com.cy.xintiao.game.ui.view.refresh.PtrFrameLayout;
import com.cy.xintiao.game.ui.view.refresh.PtrHandler;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @ViewInject(R.id.searchRl)
    private RelativeLayout searchRl;
    @ViewInject(R.id.searchIconRl)
    private RelativeLayout searchIconRl;

    @Event(R.id.searchLl)
    private void onSearchClick(View v) {
        LogUtil.i("zoomState:" + zoomState);
        if (zoomState == 1 && !startZoomAnimation) {//缩小
            zoomState = 0;
            zoomAnimation(500, 1.0f, 0.1f, 0.0f);
        }
    }

    @Event(R.id.searchIconRl)
    private void onSearchIconClick(View v) {
        LogUtil.i("zoomState:" + zoomState);
        if (zoomState == 0 && !startZoomAnimation) {//放大
            zoomState = 1;
            searchRl.setVisibility(View.VISIBLE);
            searchIconRl.setVisibility(View.GONE);
            zoomAnimation(500, 0.1f, 1.0f, 0.0f);
        }
    }

    @Event(R.id.codeLl)
    private void onCodeClick(View v) {
        LogUtil.i("扫描二维码");
    }

    @ViewInject(R.id.gamePtrView)
    private PtrClassicFrameLayout mPtrFrame;
    @ViewInject(R.id.gameLv)
    private ListView gameLv;
    private GameAdapter gameAdapter;
    private List<GameEntity> infos = new ArrayList<>();
    // 加载中
    private boolean loading = false;
    /**
     * 搜索栏放大缩小状态 0为缩小；1为放大
     */
    private int zoomState = 1;
    private boolean startZoomAnimation = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        x.view().inject(this, view);
        initRefreshView();
        gameLv.addHeaderView(initHeadView());
        gameAdapter = new GameAdapter(infos, getContext());
        gameLv.setAdapter(gameAdapter);
        gameLv.setOnItemClickListener(this);
        addListViewData();
        gameAdapter.notifyDataSetChanged();
        return view;
    }

    /**
     * 缩放动画
     *
     * @param duration
     * @param fromX
     * @param toX
     * @param pivotXValue
     */
    private void zoomAnimation(int duration, float fromX, float toX, float pivotXValue) {
        /** 设置缩放动画 */
        /*float fromX 动画起始时 X坐标上的伸缩尺寸
        float toX 动画结束时 X坐标上的伸缩尺寸
        float fromY 动画起始时Y坐标上的伸缩尺寸
        float toY 动画结束时Y坐标上的伸缩尺寸
        int pivotXType 动画在X轴相对于物件位置类型
        float pivotXValue 动画相对于物件的X坐标的开始位置
        int pivotYType 动画在Y轴相对于物件位置类型
        float pivotYValue 动画相对于物件的Y坐标的开始位置*/
        ScaleAnimation animation = new ScaleAnimation(fromX, toX, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, pivotXValue, Animation.RELATIVE_TO_SELF,
                1.0f);
        animation.setDuration(duration);//设置动画持续时间
        /** 常用方法 */
        animation.setRepeatCount(0);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        animation.setStartOffset(0);//执行前的等待时间
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                LogUtil.i("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LogUtil.i("onAnimationEnd");
                searchRl.clearAnimation();
                if (zoomState == 0) {
                    searchRl.setVisibility(View.GONE);
                    searchIconRl.setVisibility(View.VISIBLE);
                }
                startZoomAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        searchRl.setAnimation(animation);
        startZoomAnimation = true;
        /** 开始动画 */
        animation.startNow();
    }

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

    private View initHeadView() {
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.head_game_view, null);
        LinearLayout rankLl = (LinearLayout) headView.findViewById(R.id.rankLl);
        rankLl.setOnClickListener(this);
        LinearLayout classifyLl = (LinearLayout) headView.findViewById(R.id.classifyLl);
        classifyLl.setOnClickListener(this);
        LinearLayout webGameLl = (LinearLayout) headView.findViewById(R.id.webGameLl);
        webGameLl.setOnClickListener(this);
        return headView;
    }

    private void addListViewData() {
        GameEntity gameEntity1 = new GameEntity();
        gameEntity1.setTagName("每日推荐");
        gameEntity1.setTagRight("换一换");
        gameEntity1.setType(0);
        infos.add(gameEntity1);
        GameEntity gameEntity11 = new GameEntity();
        gameEntity11.setType(1);
        gameEntity11.setAppsum("官方正版同名手游");
        gameEntity11.setCountdown("689万次下载");
        gameEntity11.setLabel("琅邪榜");
        gameEntity11.setSize("200M");
        gameEntity11.setHasLineView(false);
        infos.add(gameEntity11);

        GameEntity gameEntity21 = new GameEntity();
        gameEntity21.setTagName("抢先体验");
        gameEntity21.setTagRight("更多");
        gameEntity21.setType(0);
        infos.add(gameEntity21);

        for (int i = 0; i < 2; i++) {
            GameEntity gameEntity22 = new GameEntity();
            gameEntity22.setType(1);
            gameEntity22.setAppsum("官方正版同名手游");
            gameEntity22.setCountdown("689万次下载");
            gameEntity22.setLabel("琅邪榜");
            gameEntity22.setSize("200M");
            if (i == 2 - 1) {
                gameEntity22.setHasLineView(false);
            } else {
                gameEntity22.setHasLineView(true);
            }

            infos.add(gameEntity22);
        }


        GameEntity gameEntity31 = new GameEntity();
        gameEntity31.setTagName("热门游戏");
        gameEntity31.setTagRight("更多");
        gameEntity31.setType(0);
        infos.add(gameEntity31);

        for (int i = 0; i < 10; i++) {
            GameEntity gameEntity32 = new GameEntity();
            gameEntity32.setType(1);
            gameEntity32.setAppsum("官方正版同名手游");
            gameEntity32.setCountdown("689万次下载");
            gameEntity32.setLabel("琅邪榜");
            gameEntity32.setSize("200M");
            if (i == 10 - 1) {
                gameEntity32.setHasLineView(false);
            } else {
                gameEntity32.setHasLineView(true);
            }

            infos.add(gameEntity32);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rankLl://排行榜
                goActivity(RankActivity.class);
                break;
            case R.id.classifyLl://分类
                goActivity(ClassifyActivity.class);
                break;
            case R.id.webGameLl://页游
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        goActivity(AppDetailActivity.class);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
