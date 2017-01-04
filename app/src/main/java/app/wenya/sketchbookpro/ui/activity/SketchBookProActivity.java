package app.wenya.sketchbookpro.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rm.freedraw.FreeDrawView;
import com.rm.freedraw.HistoryPath;
import com.rm.freedraw.Point;

import java.lang.reflect.Field;
import java.util.ArrayList;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.base.Constant;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.ViewHolder;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/4 9:59
 */

public class SketchBookProActivity extends BaseActivity implements View.OnClickListener, FreeDrawView.DrawCreatorListener {
    private ViewHolder mViewHolder;
    private FreeDrawView mFreeDrawView;
    private DrawingImage mDrawingImage;
    private Bitmap draw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sketch_book_pro);
        initView();
    }

    private void initView() {
        mViewHolder = new ViewHolder(this, findViewById(R.id.RltMain), this);
        mFreeDrawView = mViewHolder.getView(R.id.mFreeDrawView);
        mFreeDrawView.getDrawScreenshot(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        draw = null;
        saveDraw();
    }

    @Override
    protected void loadData() {
        mDrawingImage = (DrawingImage) getIntent().getSerializableExtra(Constant.ARG1);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDrawCreated(Bitmap draw) {
        this.draw = draw;
    }

    @Override
    public void onDrawCreationError() {

    }

    private void saveDraw() {
        try {
            Field mPointsField = FreeDrawView.class.getDeclaredField("mPoints");
            Field mPathsField = FreeDrawView.class.getDeclaredField("mPaths");
            Field mCanceledPathsField = FreeDrawView.class.getDeclaredField("mCanceledPaths");
            mPointsField.setAccessible(true);
            mPathsField.setAccessible(true);
            mCanceledPathsField.setAccessible(true);
            Class<?> mPointClass = Class.forName("com.rm.freedraw.Point");
            Class<?> mHistoryPathClass = Class.forName("com.rm.freedraw.HistoryPath");
            ArrayList<mPointClass> mPoints = mPointsField.get(mDrawingImage);
            ArrayList<mHistoryPathClass> mPaths = mPathsField.get(mDrawingImage);
            ArrayList<mHistoryPathClass> mCanceledPaths = mCanceledPathsField.get(mDrawingImage);

        } catch (Exception e) {
        }
    }
}
