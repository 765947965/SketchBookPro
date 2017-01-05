package app.wenya.sketchbookpro.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rm.freedraw.FreeDrawView;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.base.Constant;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.ViewHolder;
import app.wenya.sketchbookpro.utils.util.BitMapStoreUtil;
import app.wenya.sketchbookpro.utils.util.ImageLoadUtil;


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
    private Intent intent;
    private MaterialDialog progressDialog;
    private String imagePath;//原始图片地址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sketch_book_pro);
        initView();
    }

    private void initView() {
        mViewHolder = new ViewHolder(this, findViewById(R.id.RltMain), this);
        mFreeDrawView = mViewHolder.getView(R.id.mFreeDrawView);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            willSaveBitMap();
            return true;
        }
        return false;
    }

    @Override
    protected void loadData() {
        intent = getIntent();
        mDrawingImage = (DrawingImage) intent.getSerializableExtra(Constant.ARG1);
        mViewHolder.setText(R.id.tvTitle, mDrawingImage.getName());
        imagePath = BitMapStoreUtil.instance().getBitMap(this, mDrawingImage.getFolder(), mDrawingImage.getName());
        if (!TextUtils.isEmpty(imagePath)) {
            ImageLoadUtil.instance().loadImageAutoSize(this, imagePath, (ImageView) mViewHolder.getView(R.id.ivBackImage));
        } else {
            mFreeDrawView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDrawCreated(Bitmap draw) {
        BitMapStoreUtil.instance().saveBitMap(this, draw, mDrawingImage.getFolder(), mDrawingImage.getName());
        draw.recycle();
        finish(true);
    }

    @Override
    public void onDrawCreationError() {
        finish(false);
    }

    /**
     * @param resultData 是否返回结果
     */
    public void finish(boolean resultData) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        intent.putExtra(Constant.ARG1, resultData ? mDrawingImage : null);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void saveBitMap() {
        progressDialog = new MaterialDialog.Builder(this).content("保存图片中...").progress(true, 0).show();
        mFreeDrawView.getDrawScreenshot(this);
    }

    private void willSaveBitMap() {
        if (mFreeDrawView.getUndoCount() > 0) {
            new MaterialDialog.Builder(this)
                    .content("是否保存图片")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            saveBitMap();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            finish(false);
                        }
                    })
                    .show();
        } else {
            finish(false);
        }
    }
}
