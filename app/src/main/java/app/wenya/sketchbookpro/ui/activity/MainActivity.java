package app.wenya.sketchbookpro.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.base.Constant;
import app.wenya.sketchbookpro.utils.Instance.MyOnPageChangeListener;
import app.wenya.sketchbookpro.utils.util.BitMapStoreUtil;
import app.wenya.sketchbookpro.utils.util.ImageLoadUtil;
import app.wenya.sketchbookpro.utils.util.ImageStorageUtil;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.LoopBaseAdapter;
import app.wenya.sketchbookpro.ui.base.ViewHolder;
import app.wenya.sketchbookpro.ui.view.looppager.IconPageIndicator;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private ViewHolder mViewHolder;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mBroadside;
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
        mViewHolder = new ViewHolder(this, findViewById(R.id.mDrawerLayout), this);
        mDrawerLayout = mViewHolder.getView(R.id.mDrawerLayout);
        mViewPager = mViewHolder.getView(R.id.mViewPager);
        mBroadside = mViewHolder.getView(R.id.mBroadside);
        mIconPageIndicator = mViewHolder.getView(R.id.mIconPageIndicator);
        mViewPager.addOnPageChangeListener(new PagerChangeListener());
        mViewHolder.setOnClickListener(R.id.tvTitle);
        mViewHolder.setOnClickListener(R.id.fab);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTitle:
                mDrawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.fab:
                startActivityForResult(new Intent(MainActivity.this, SketchBookProActivity.class).putExtra(Constant.ARG1, new DrawingImage("默认", String.valueOf(System.currentTimeMillis()) + ".jpg")), Constant.SKETCHBOOKPRO);
                break;
        }
    }

    @Override
    protected void loadData() {
        if (mDrawingImages == null) {
            mDrawingImages = new ArrayList<>();
        }
        mDrawingImages.clear();
        mDrawingImages.addAll(ImageStorageUtil.instance().getAllDrawingImage(this, "默认"));
        setAdapter();
    }

    private void setAdapter() {
        if (mDrawingImages.size() == 0) return;
        mViewPager.setAdapter(new LoopBaseAdapter<DrawingImage>(this, mDrawingImages, R.layout.activity_adapter_item) {
            @Override
            public void createView(ViewHolder mViewHolder, DrawingImage item, List<DrawingImage> mDatas, int position) {
                ImageLoadUtil.instance().loadImageAutoSize(MainActivity.this, BitMapStoreUtil.instance().getBitMap(MainActivity.this, item.getFolder(), item.getName()), (ImageView) mViewHolder.getView(R.id.mSketchImageView));
            }

            @Override
            public void onClickItem(View view, DrawingImage item, List<DrawingImage> mDatas, int position) {
                startActivityForResult(new Intent(MainActivity.this, SketchBookProActivity.class).putExtra(Constant.ARG1, item), Constant.SKETCHBOOKPRO);
            }
        });
        mIconPageIndicator.setPadding(10, 0, 10, 0);
        mIconPageIndicator.setViewPager(mViewPager);
        mIconPageIndicator.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == Constant.SKETCHBOOKPRO) {
            if (data.getSerializableExtra(Constant.ARG1) != null) {
                DrawingImage newItem = (DrawingImage) data.getSerializableExtra(Constant.ARG1);
                boolean isHas = false;
                for (DrawingImage Item : mDrawingImages) {
                    if (Item.getName().equals(newItem.getName())) {
                        isHas = true;
                    }
                    break;
                }
                if (!isHas) {
                    mDrawingImages.add(newItem);
                    ImageStorageUtil.instance().setAllDrawingImage(this, mDrawingImages, "默认");
                }
                setAdapter();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class PagerChangeListener extends MyOnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

        }
    }
}
