package app.wenya.sketchbookpro.ui.base;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.ui.view.looppager.IconPagerAdapter;

public abstract class LoopBaseAdapter<T> extends PagerAdapter implements IconPagerAdapter {
    private LayoutInflater mInflater;
    private List<T> mDatas;
    private Activity mActivity;
    private int mItemLayoutId;

    /**
     * @Description
     * @pamars
     */
    public LoopBaseAdapter(Activity mActivity, List<T> mDatas, int itemLayoutId) {
        // TODO Auto-generated constructor stub
        this.mActivity = mActivity;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mActivity);
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.mDatas.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(mItemLayoutId, container, false);
        createView(new ViewHolder(mActivity, view), getItem(position), mDatas, position);
        container.addView(view, 0);
        return view;
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getIconResId(int index) {
        // TODO Auto-generated method stub
        return R.drawable.invest_selector;
    }

    @Override
    public int getPagerCount() {
        return mDatas.size();
    }

    public abstract void createView(ViewHolder mViewHolder, T item, List<T> mDatas, int position);

}