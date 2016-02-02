package com.cy.xintiao.game.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 包名：com.cy.xintiao.game.entity
 * <p/>
 * 作者：CaoYi on 2016/1/27 18:27
 * <p/>
 * 描述：
 */
@Table(name = "download", onCreated = "CREATE UNIQUE INDEX index_name ON download(label,fileSavePath)")
public class DownloadInfo {

    public DownloadInfo() {
    }

    @Column(name = "id", isId = true)
    private long id;

    @Column(name = "state")
    private DownloadState state = DownloadState.LOADING;
    /**
     * 下载地址
     */
    @Column(name = "url")
    private String url;
    /**
     * 名称
     */
    @Column(name = "label")
    private String label;
    /**
     * 保存文件地址
     */
    @Column(name = "fileSavePath")
    private String fileSavePath;
    /**
     * 下载进度
     */
    @Column(name = "progress")
    private int progress;
    /**
     * 文件长度
     */
    @Column(name = "fileLength")
    private long fileLength;
    /**
     * 是否重新下载
     */
    @Column(name = "autoResume")
    private boolean autoResume;
    /**
     * 是否重新命名
     */
    @Column(name = "autoRename")
    private boolean autoRename;
    /**
     * 应用图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 包名
     */
    @Column(name = "packageName")
    private String packageName;
    /**
     * 应用下载次数单位（万）
     */
    @Column(name = "countdown")
    private String countdown;
    /**
     * 应用星评值
     */
    @Column(name = "staring")
    private String staring;
    /**
     * APK大小
     */
    @Column(name = "size")
    private String size;
    /**
     * 是否显示最新
     */
    @Column(name = "isDetails")
    private boolean isDetails;
    /**
     * 应用简介
     */
    @Column(name = "appsum")
    private String appsum;
    /**
     * 应用id序列
     */
    @Column(name = "appid")
    private String appid;
    /**
     * 应用版本名称
     */
    @Column(name = "versionname")
    private String versionname;
    /**
     * 应用版本号
     */
    @Column(name = "versioncode")
    private int versioncode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DownloadState getState() {
        return state;
    }

    public void setState(DownloadState state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFileSavePath() {
        return fileSavePath;
    }

    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public boolean isAutoResume() {
        return autoResume;
    }

    public void setAutoResume(boolean autoResume) {
        this.autoResume = autoResume;
    }

    public boolean isAutoRename() {
        return autoRename;
    }

    public void setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }

    public String getStaring() {
        return staring;
    }

    public void setStaring(String staring) {
        this.staring = staring;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isDetails() {
        return isDetails;
    }

    public void setIsDetails(boolean isDetails) {
        this.isDetails = isDetails;
    }

    public String getAppsum() {
        return appsum;
    }

    public void setAppsum(String appsum) {
        this.appsum = appsum;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DownloadInfo)) return false;

        DownloadInfo that = (DownloadInfo) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
