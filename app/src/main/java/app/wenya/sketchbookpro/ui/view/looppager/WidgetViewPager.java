/*
 * Copyright (C) 2013 Leszek Mzyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.wenya.sketchbookpro.ui.view.looppager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义不需要滚动的viewpager,重写其高度，使其高度不会铺满整个布局
 */
public class WidgetViewPager extends android.support.v4.view.ViewPager {
    private OnPageChangeListener mOuterPageChangeListener;

    public WidgetViewPager(Context context) {
        super(context);
        initView();
    }

    private void initView() {
//        super.addOnPageChangeListener(onPageChangeListener);
    }

    public WidgetViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

//    @Override
//    public void addOnPageChangeListener(OnPageChangeListener listener) {
//        mOuterPageChangeListener = listener;
//    }

    private int toRealPosition(int position) {
        int realCount = getAdapter().getCount();
        if (realCount == 0)
            return 0;
        int realPosition = (position - 1) % realCount;
        if (realPosition < 0)
            realPosition += realCount;

        return realPosition;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
//        private float mPreviousOffset = -1;
//        private float mPreviousPosition = -1;
//
//        @Override
//        public void onPageSelected(int position) {
//
//            int realPosition = toRealPosition(position);
//            if (mPreviousPosition != realPosition) {
//                mPreviousPosition = realPosition;
//                if (mOuterPageChangeListener != null) {
//                    mOuterPageChangeListener.onPageSelected(realPosition);
//                }
//            }
//
//        }
//
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            int realPosition = position;
//            if (getAdapter() != null) {
//                realPosition = toRealPosition(position);
//
//                if (positionOffset == 0 && mPreviousOffset == 0 && (position == 0 || position == getAdapter().getCount() - 1)) {
//                    setCurrentItem(realPosition, false);
//                }
//            }
//
//            mPreviousOffset = positionOffset;
//            if (mOuterPageChangeListener != null) {
//                if (realPosition != getAdapter().getCount() - 1) {
//                    mOuterPageChangeListener.onPageScrolled(realPosition, positionOffset, positionOffsetPixels);
//                } else {
//                    if (positionOffset > .5) {
//                        mOuterPageChangeListener.onPageScrolled(0, 0, 0);
//                    } else {
//                        mOuterPageChangeListener.onPageScrolled(realPosition, 0, 0);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//            if (getAdapter() != null) {
//                int position = getCurrentItem();
//                int realPosition = toRealPosition(position);
//                if (state == ViewPager.SCROLL_STATE_IDLE && (position == 0 || position == getAdapter().getCount() - 1)) {
//                    setCurrentItem(realPosition, false);
//                }
//            }
//            if (mOuterPageChangeListener != null) {
//                mOuterPageChangeListener.onPageScrollStateChanged(state);
//            }
//        }
//    };
}
