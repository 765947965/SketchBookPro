package app.wenya.sketchbookpro.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.data.Instance.MyOnPageChangeListener;
import app.wenya.sketchbookpro.data.util.ImageLoadUtil;
import app.wenya.sketchbookpro.data.util.ImageStorageUtil;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.LoopBaseAdapter;
import app.wenya.sketchbookpro.ui.base.ViewHolder;
import app.wenya.sketchbookpro.ui.view.looppager.IconPageIndicator;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewHolder mViewHolder;
    private ViewPager mViewPager;
    private IconPageIndicator mIconPageIndicator;
    private List<DrawingImage> mDrawingImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewHolder = new ViewHolder(this, findViewById(R.id.RltMain), this);
        mViewPager = mViewHolder.getView(R.id.mViewPager);
        mIconPageIndicator = mViewHolder.getView(R.id.mIconPageIndicator);
        mViewPager.addOnPageChangeListener(new PagerChangeListener());
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void loadData() {
        if (mDrawingImages == null) {
            mDrawingImages = new ArrayList<>();
            mViewPager.setAdapter(new LoopBaseAdapter<DrawingImage>(this, mDrawingImages, R.layout.activity_adapter_item) {
                @Override
                public void createView(ViewHolder mViewHolder, DrawingImage item, List<DrawingImage> mDatas, int position) {
                    if (position == 0) {
                        mViewHolder.setVisibility(R.id.mSketchImageView, View.GONE);
                    } else {
                        mViewHolder.setVisibility(R.id.mSketchImageView, View.VISIBLE);
                        ImageLoadUtil.instance().loadImageAutoSize(MainActivity.this, item.getPath(), (ImageView) mViewHolder.getView(R.id.mSketchImageView));
                    }
                }
            });
            mIconPageIndicator.setPadding(10, 0, 10, 0);
            mIconPageIndicator.setViewPager(mViewPager);
        }
        mDrawingImages.clear();
        mDrawingImages.addAll(ImageStorageUtil.instance().getAllDrawingImage(this));
        mDrawingImages.add(0, new DrawingImage());
        mViewPager.getAdapter().notifyDataSetChanged();
        mIconPageIndicator.notifyDataSetChanged();
    }

    class PagerChangeListener extends MyOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

        }
    }
}
