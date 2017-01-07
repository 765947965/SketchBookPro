package app.wenya.sketchbookpro.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xw.repo.BubbleSeekBar;

import java.lang.reflect.Field;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/7 14:25
 */

public class MyBubbleSeekBar extends BubbleSeekBar {

    private MySelProgressChangeListnener mMySelProgressChangeListnener;


    public MyBubbleSeekBar(Context context) {
        super(context);
        setSelfProgressChangeListnener();
    }

    public MyBubbleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSelfProgressChangeListnener();
    }

    public MyBubbleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSelfProgressChangeListnener();
    }

    private void setSelfProgressChangeListnener() {
        setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            public void onProgressChanged(int progress) {
                if (mMySelProgressChangeListnener != null) {
                    mMySelProgressChangeListnener.onProgressChanged(MyBubbleSeekBar.this, progress);
                }
            }
        });
    }

    public void setmMySelProgressChangeListnener(MySelProgressChangeListnener mMySelProgressChangeListnener) {
        this.mMySelProgressChangeListnener = mMySelProgressChangeListnener;
    }

    public interface MySelProgressChangeListnener {
        void onProgressChanged(View view, int progress);
    }

    public void setThumbColor(int color) {
        try {
            Field field = BubbleSeekBar.class.getDeclaredField("mThumbColor");
            field.setAccessible(true);
            field.setInt(this, color);
            postInvalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
