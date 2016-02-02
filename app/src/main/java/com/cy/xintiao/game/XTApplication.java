package com.cy.xintiao.game;

import android.app.Application;

import org.xutils.x;

/**
 * 包名：com.cy.xintiao.game
 * <p/>
 * 作者：CaoYi on 2016/1/25 18:32
 * <p/>
 * 描述：
 */
public class XTApplication extends Application {

    private static XTApplication instance = null;

    public XTApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public static XTApplication getInstance() {
        return instance;
    }

    public static void setInstance(XTApplication instance) {
        XTApplication.instance = instance;
    }
}
