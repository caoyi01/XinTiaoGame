package com.cy.xintiao.game.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 包名：com.cy.xintiao.game.ui.view
 * <p/>
 * 作者：CaoYi on 2016/2/1 12:30
 * <p/>
 * 描述：
 */
public class ClassifyTagGridLayout extends GridView {

    public ClassifyTagGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClassifyTagGridLayout(Context context) {
        super(context);
    }

    public ClassifyTagGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
