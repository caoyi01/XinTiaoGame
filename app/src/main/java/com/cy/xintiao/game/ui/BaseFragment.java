package com.cy.xintiao.game.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.cy.xintiao.game.utils.StringHelper;

/**
 * 包名：com.cy.xintiao.game.ui
 * <p/>
 * 作者：CaoYi on 2016/1/27 16:00
 * <p/>
 * 描述：
 */
public class BaseFragment extends Fragment {
    protected void showToast(String text) {
        if (!StringHelper.isEmpty(text))
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    protected void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    protected void goActivity(Class<?> c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
    }

    protected void goActivity(Class<?> c, String name, String value) {
        Intent intent = new Intent(getContext(), c);
        intent.putExtra(name, value);
        startActivity(intent);
    }

    protected void goActivity(Class<?> c, Bundle bundle) {
        Intent intent = new Intent(getContext(), c);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
