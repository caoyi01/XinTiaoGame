package com.cy.xintiao.game.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cy.xintiao.game.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.cy.xintiao.game.ui.adapter
 * <p/>
 * 作者：CaoYi on 2016/2/1 12:47
 * <p/>
 * 描述：
 */
public class ClassifyAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> infos = new ArrayList<>();
    private LayoutInflater inflater;

    public ClassifyAdapter(List<String> infos, Context mContext) {
        this.infos = infos;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public String getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_classify_view,
                    parent, false);
            holder = new ViewHolder();
            holder.tagTv = (TextView) convertView
                    .findViewById(R.id.tagTv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tagTv.setText(infos.get(position));
        return convertView;
    }

    public class ViewHolder {
        TextView tagTv;
    }
}
