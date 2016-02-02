package com.cy.xintiao.game.entity;

/**
 * 包名：com.cy.xintiao.game.entity
 * <p/>
 * 作者：CaoYi on 2016/1/28 17:58
 * <p/>
 * 描述：
 */
public class GameEntity extends DownloadInfo {
    private int type;//0为TAG，1为内容
    private String tagName;//
    private String tagRight;
    private boolean hasLineView;

    public GameEntity() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagRight() {
        return tagRight;
    }

    public void setTagRight(String tagRight) {
        this.tagRight = tagRight;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHasLineView() {
        return hasLineView;
    }

    public void setHasLineView(boolean hasLineView) {
        this.hasLineView = hasLineView;
    }
}
