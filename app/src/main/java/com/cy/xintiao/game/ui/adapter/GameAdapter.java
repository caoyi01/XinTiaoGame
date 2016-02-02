package com.cy.xintiao.game.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.cy.xintiao.game.ui.adapter
 * <p/>
 * 作者：CaoYi on 2016/1/28 17:38
 * <p/>
 * 描述：
 */
public class GameAdapter extends BaseAdapter {
    final int VIEW_TYPE = 2;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;

    private Context mContext;
    private List<GameEntity> infos = new ArrayList<>();
    private LayoutInflater inflater;

    public GameAdapter(List<GameEntity> infos, Context mContext) {
        this.infos = infos;
        this.mContext = mContext;
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        switch (infos.get(position).getType()) {
            case 0:
                return TYPE_1;
            case 1:
                return TYPE_2;
        }
        return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public GameEntity getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        int type = getItemViewType(position);
        GameEntity entity = getItem(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:
                    convertView = inflater.inflate(R.layout.item_tag_view,
                            parent, false);
                    holder1 = new ViewHolder1();
                    holder1.nameTagTv = (TextView) convertView
                            .findViewById(R.id.nameTagTv);
                    holder1.rightTagTv = (TextView) convertView
                            .findViewById(R.id.rightTagTv);
                    holder1.lineView = convertView.findViewById(R.id.lineView1);
                    convertView.setTag(holder1);
                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.item_game_view,
                            parent, false);
                    holder2 = new ViewHolder2();
                    holder2.desTv = (TextView) convertView.findViewById(R.id.desTv);
                    holder2.downloadNumTv = (TextView) convertView.findViewById(R.id.downloadNumTv);
                    holder2.downloadTv = (TextView) convertView.findViewById(R.id.downloadTv);
                    holder2.iconIv = (ImageView) convertView.findViewById(R.id.iconIv);
                    holder2.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
                    holder2.sizeTv = (TextView) convertView.findViewById(R.id.sizeTv);
                    holder2.lineView = convertView.findViewById(R.id.lineView);
                    convertView.setTag(holder2);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
            }
        }
        // 设置资源
        switch (type) {
            case TYPE_1:
                holder1.nameTagTv.setText(entity.getTagName());
                holder1.rightTagTv.setText(entity.getTagRight());
                if (entity.isHasLineView())
                    holder1.lineView.setVisibility(View.VISIBLE);
                else
                    holder1.lineView.setVisibility(View.INVISIBLE);
                break;
            case TYPE_2:
                holder2.desTv.setText(entity.getAppsum());
                holder2.sizeTv.setText(entity.getSize());
                holder2.downloadNumTv.setText(entity.getCountdown());
                holder2.nameTv.setText(entity.getLabel());
                if (entity.isHasLineView())
                    holder2.lineView.setVisibility(View.VISIBLE);
                else
                    holder2.lineView.setVisibility(View.INVISIBLE);
                break;
        }

        return convertView;
    }

    public class ViewHolder1 {
        TextView nameTagTv;
        TextView rightTagTv;
        View lineView;
    }

    public class ViewHolder2 {
        ImageView iconIv;
        TextView nameTv;
        TextView downloadNumTv;
        TextView sizeTv;
        TextView desTv;
        TextView downloadTv;
        View lineView;
    }
}
