package app.wenya.sketchbookpro.ui.base;

import android.support.v7.app.AppCompatActivity;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 14:20
 */

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isLoadData;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isLoadData) {
            isLoadData = true;
            loadData();
        }
    }

    protected abstract void loadData();
}
