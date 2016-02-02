package com.cy.xintiao.game.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.entity.DownloadInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.cy.xintiao.game.ui.adapter
 * <p/>
 * 作者：CaoYi on 2016/1/28 17:38
 * <p/>
 * 描述：
 */
public class RankAdapter extends BaseAdapter {

    private Context mContext;
    private List<DownloadInfo> infos = new ArrayList<>();
    private LayoutInflater inflater;

    public RankAdapter(List<DownloadInfo> infos, Context mContext) {
        this.infos = infos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public DownloadInfo getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int type = getItemViewType(position);
        DownloadInfo info = getItem(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_rank_view,
                    parent, false);
            holder = new ViewHolder();
            holder.desTv = (TextView) convertView.findViewById(R.id.desTv);
            holder.downloadNumTv = (TextView) convertView.findViewById(R.id.downloadNumTv);
            holder.downloadTv = (TextView) convertView.findViewById(R.id.downloadTv);
            holder.iconIv = (ImageView) convertView.findViewById(R.id.iconIv);
            holder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
            holder.sizeTv = (TextView) convertView.findViewById(R.id.sizeTv);
            holder.lineView = convertView.findViewById(R.id.lineView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.desTv.setText(info.getAppsum());
        holder.sizeTv.setText(info.getSize());
        holder.downloadNumTv.setText(info.getCountdown());
        holder.nameTv.setText(info.getLabel());
        return convertView;
    }

    public class ViewHolder {
        ImageView iconIv;
        TextView nameTv;
        TextView downloadNumTv;
        TextView sizeTv;
        TextView desTv;
        TextView downloadTv;
        View lineView;
    }
}
