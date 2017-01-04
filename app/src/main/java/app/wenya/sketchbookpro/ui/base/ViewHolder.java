package app.wenya.sketchbookpro.ui.base;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/3/22 14:04
 */
public class ViewHolder {

    private Activity mActivity;
    private View mRootView;
    private SparseArray<View> viewmaps = new SparseArray<>();
    private View.OnClickListener mOnClickListener;

    public ViewHolder(Activity mActivity, int resource) {
        this.mActivity = mActivity;
        this.mRootView = LayoutInflater.from(mActivity).inflate(resource, null);
    }

    public ViewHolder(Activity mActivity, View mRootView) {
        this.mActivity = mActivity;
        this.mRootView = mRootView;
    }

    public ViewHolder(Activity mActivity, int resource, View.OnClickListener mOnClickListener) {
        this.mActivity = mActivity;
        this.mRootView = LayoutInflater.from(mActivity).inflate(resource, null);
        this.mOnClickListener = mOnClickListener;
    }

    public ViewHolder(Activity mActivity, View mRootView, View.OnClickListener mOnClickListener) {
        this.mActivity = mActivity;
        this.mRootView = mRootView;
        this.mOnClickListener = mOnClickListener;
    }

    public View getmRootView() {
        return mRootView;
    }

    public <T extends View> T getView(int resource) {
        View view = viewmaps.get(resource);
        if (view == null) {
            view = mRootView.findViewById(resource);
            viewmaps.put(resource, view);
        }
        return (T) view;
    }

    public TextView setText(int resource, CharSequence mCharSequence) {
        TextView mTextView = getView(resource);
        mTextView.setText(mCharSequence);
        return mTextView;
    }

    public <T extends View> T setOnClickListener(int resource) {
        View view = getView(resource);
        if (mOnClickListener != null) {
            view.setOnClickListener(mOnClickListener);
        }
        return (T) view;
    }

    public <T extends View> T setVisibility(int resource, int visibility) {
        View view = getView(resource);
        view.setVisibility(visibility);
        return (T) view;
    }
}
