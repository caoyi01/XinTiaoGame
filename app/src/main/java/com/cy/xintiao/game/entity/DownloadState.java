package com.cy.xintiao.game.entity;

/**
 * 包名：com.cy.xintiao.game.entity
 * <p/>
 * 作者：CaoYi on 2016/1/27 18:28
 * <p/>
 * 描述：
 */
public enum DownloadState {
    WAITING(0)/**等待*/
    , STARTED(1)/**开始*/
    , FINISHED(2)/**完成*/
    , STOPPED(3)/**暂停*/
    , ERROR(4)/**错误*/
    , OPEN(5)/**打开*/
    , UPDATE(6)/**更新*/
    , LOADING(7)/**装载*/
    ;

    private final int value;

    DownloadState(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static DownloadState valueOf(int value) {
        switch (value) {
            case 0:
                return WAITING;
            case 1:
                return STARTED;
            case 2:
                return FINISHED;
            case 3:
                return STOPPED;
            case 4:
                return ERROR;
            case 5:
                return OPEN;
            case 6:
                return UPDATE;
            case 7:
                return LOADING;
            default:
                return LOADING;
        }
    }
}

