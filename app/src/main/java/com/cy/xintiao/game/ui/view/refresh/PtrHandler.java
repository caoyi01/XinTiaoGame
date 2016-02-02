package com.cy.xintiao.game.ui.view.refresh;

import android.view.View;

/**
 * 包名：com.cy.xintiao.game.ui.view.refresh
 * <p/>
 * 作者：CaoYi on 2016/1/28 19:24
 * <p/>
 * 描述：
 */
public interface PtrHandler {

    public boolean checkCanDoRefresh(final PtrFrameLayout frame, final View content, final View header);

    public void onRefreshBegin(final PtrFrameLayout frame);
}
