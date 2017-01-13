package app.wenya.sketchbookpro.ui.view;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.util.AttributeSet;

import com.rm.freedraw.FreeDrawView;

import java.lang.reflect.Field;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/13 14:15
 */

public class MyFreeDrawView extends FreeDrawView {

    public MyFreeDrawView(Context context) {
        super(context);
    }

    public MyFreeDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFreeDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBrush() {
        try {
            Field field = FreeDrawView.class.getDeclaredField("mFinishPath");
            field.setAccessible(true);
            field.setBoolean(this, true);
            invalidate();
            Field fieldPaint = FreeDrawView.class.getDeclaredField("mCurrentPaint");
            fieldPaint.setAccessible(true);
            Paint paint = (Paint) fieldPaint.get(this);
//            paint.setPathEffect(new DiscretePathEffect(9f, 22f));
            Path path = new Path();
            path.moveTo(-30, 0);
            path.lineTo(30, 0);
            path.lineTo(-18, 35);
            path.lineTo(0, -22);
            path.lineTo(18, 35);
            path.lineTo(-30, 0);
            path.close();
            paint.setPathEffect(new PathDashPathEffect(path, 80f, 80f, PathDashPathEffect.Style.MORPH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
