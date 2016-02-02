package com.cy.xintiao.game.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cy.xintiao.game.R;
import com.cy.xintiao.game.utils.StringHelper;

import org.xutils.x;

/**
 * 包名：com.cy.xintiao.game.ui
 * <p/>
 * 作者：CaoYi on 2016/1/27 14:50
 * <p/>
 * 描述：
 */
public class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    protected void setToolbarTitle(String text) {
        ((TextView) findViewById(R.id.titleTv)).setText(text);
    }

    protected void showToast(String text) {
        if (!StringHelper.isEmpty(text))
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    protected void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    protected void goActivity(Class<?> c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    protected void goActivity(Class<?> c, String name, String value) {
        Intent intent = new Intent(this, c);
        intent.putExtra(name, value);
        startActivity(intent);
    }

    protected void goActivity(Class<?> c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
