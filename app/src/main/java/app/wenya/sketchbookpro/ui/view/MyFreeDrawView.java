package app.wenya.sketchbookpro.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.support.annotation.FloatRange;
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
    private boolean isStarBrush = false;

    public MyFreeDrawView(Context context) {
        super(context);
    }

    public MyFreeDrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFreeDrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStarBrush(boolean isBrush) {
        try {
            this.isStarBrush = isBrush;
            Field field = FreeDrawView.class.getDeclaredField("mFinishPath");
            field.setAccessible(true);
            field.setBoolean(this, true);
            invalidate();
            Field fieldPaint = FreeDrawView.class.getDeclaredField("mCurrentPaint");
            fieldPaint.setAccessible(true);
            Paint paint = (Paint) fieldPaint.get(this);
            if (isBrush) {
//            paint.setPathEffect(new DiscretePathEffect(9f, 22f));
                float scale = getPaintWidth() / 40f;
                Path path = new Path();
                path.moveTo(-29.75f * scale, 0);
                path.lineTo(29.5f * scale, 0);
                path.lineTo(-18.5f * scale, 35 * scale);
                path.lineTo(0, -21.5f * scale);
                path.lineTo(18.25f * scale, 35 * scale);
                path.lineTo(-29.75f * scale, 0);
                path.close();
                paint.setPathEffect(new PathDashPathEffect(path, 80f * scale, 80f * scale, PathDashPathEffect.Style.MORPH));
            } else {
                paint.setPathEffect(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStarBrush() {
        return isStarBrush;
    }

    @Override
    public void setPaintWidthPx(@FloatRange(from = 0) float widthPx) {
        super.setPaintWidthPx(widthPx);
        setStarBrush(isStarBrush);
    }

    @Override
    public void setPaintWidthDp(float dp) {
        super.setPaintWidthDp(dp);
        setStarBrush(isStarBrush);
    }

}
