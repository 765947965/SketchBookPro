package app.wenya.sketchbookpro.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;


import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.rm.freedraw.FreeDrawView;
import com.rm.freedraw.PathRedoUndoCountChangeListener;


import java.util.ArrayList;
import java.util.List;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.base.Constant;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.ViewHolder;
import app.wenya.sketchbookpro.ui.view.MyBubbleSeekBar;
import app.wenya.sketchbookpro.ui.view.MyFreeDrawView;
import app.wenya.sketchbookpro.utils.util.BitMapStoreUtil;
import app.wenya.sketchbookpro.utils.util.ImageLoadUtil;
import app.wenya.sketchbookpro.ui.view.library.ButtonData;
import app.wenya.sketchbookpro.ui.view.library.ButtonEventListener;
import app.wenya.sketchbookpro.ui.view.library.SectorMenuButton;


/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/4 9:59
 */

public class SketchBookProActivity extends BaseActivity implements View.OnClickListener, FreeDrawView.DrawCreatorListener, ColorChooserDialog.ColorCallback, MyBubbleSeekBar.MySelProgressChangeListnener, PathRedoUndoCountChangeListener {
    private ViewHolder mViewHolder;
    private MyFreeDrawView mFreeDrawView;
    private DrawingImage mDrawingImage;
    private ImageView ivBackImageLeft, ivBackImageRight;
    private Intent intent;
    private MaterialDialog progressDialog;
    private String imagePath;//原始图片地址
    private MyBubbleSeekBar seekBarWidth, seekBarAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sketch_book_pro);
        initView();
        setPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    private void initView() {
        mViewHolder = new ViewHolder(this, findViewById(R.id.RltMain), this);
        mFreeDrawView = mViewHolder.getView(R.id.mFreeDrawView);
        initSectorMenuButton();
        seekBarWidth = mViewHolder.getView(R.id.seekBarWidth);
        seekBarAlpha = mViewHolder.getView(R.id.seekBarAlpha);
        ivBackImageLeft = mViewHolder.setOnClickListener(R.id.ivBackImageLeft);
        ivBackImageRight = mViewHolder.setOnClickListener(R.id.ivBackImageRight);
        seekBarWidth.setmMySelProgressChangeListnener(this);
        seekBarAlpha.setmMySelProgressChangeListnener(this);
        mFreeDrawView.setPathRedoUndoCountChangeListener(this);
    }

    private void initSectorMenuButton() {
        SectorMenuButton sectorMenuButton = (SectorMenuButton) findViewById(R.id.sector_menu);
        final List<ButtonData> buttonDatas = new ArrayList<>();
        int[] drawable = {R.drawable.cool, R.drawable.mark,
                R.drawable.search, R.drawable.copy, R.drawable.heart};
        for (int i = 0; i < drawable.length; i++) {
            ButtonData buttonData = ButtonData.buildIconButton(this, drawable[i], 0);
            buttonData.setBackgroundColorId(this, R.color.colorAccent);
            buttonDatas.add(buttonData);
        }
        sectorMenuButton.setButtonDatas(buttonDatas);
        setListener(sectorMenuButton);
    }

    private void setListener(SectorMenuButton button) {
        button.setButtonEventListener(new ButtonEventListener() {
            @Override
            public void onButtonClicked(int index) {
                switch (index) {
                    case 1:
                        checkColor();
                        break;
                    case 2:
                        mFreeDrawView.setStarBrush(!mFreeDrawView.isStarBrush());
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onExpand() {
            }

            @Override
            public void onCollapse() {
            }
        });
    }

    private void checkColor() {
        // Pass AppCompatActivity which implements ColorCallback, along with the title of the dialog
        new ColorChooserDialog.Builder(this, R.string.color_palette)
                .titleSub(R.string.color_palette)  // title of dialog when viewing shades of a color
//                .accentMode(false)  // when true, will display accent palette instead of primary palette
                .doneButton(R.string.sure)
                .cancelButton(R.string.cancel)
                .backButton(R.string.back)
                .customButton(R.string.custom)
                .presetsButton(R.string.presets)
//                .preselect(accent ? accentPreselect : primaryPreselect)  // optionally preselects a color
                .dynamicButtonColor(true)  // defaults to true, false will disable changing action buttons' color to currently selected color
                .show();
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
        switch (view.getId()) {
            case R.id.ivBackImageLeft:
                mFreeDrawView.undoLast();
                break;
            case R.id.ivBackImageRight:
                mFreeDrawView.redoLast();
                break;
        }
    }

    @Override
    public void onDrawCreated(Bitmap draw) {
        if (draw != null) {
            if (!TextUtils.isEmpty(imagePath)) {
                Bitmap bitmapOld = BitmapFactory.decodeFile(imagePath);
                Bitmap bitmapNew = Bitmap.createBitmap(bitmapOld.getWidth(), bitmapOld.getHeight(), bitmapOld.getConfig());
                Canvas canvas = new Canvas(bitmapNew);
                canvas.drawBitmap(bitmapOld, 0, 0, null);
                canvas.drawBitmap(draw, 0, 0, null);
                bitmapOld.recycle();
                draw.recycle();
                draw = bitmapNew;
                mDrawingImage.setName(String.valueOf(System.currentTimeMillis()) + ".jpg");
            }
            BitMapStoreUtil.instance().saveBitMap(this, draw, mDrawingImage.getFolder(), mDrawingImage.getName());
            draw.recycle();
            finish(true);
        } else {
            finish(false);
        }

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
            saveBitMap();
        } else {
            finish(false);
        }
    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        mFreeDrawView.setPaintColor(selectedColor);
        seekBarWidth.setSecondTrackColor(Color.argb(Color.alpha(seekBarWidth.getSecondTrackColor()), Color.red(selectedColor), Color.green(selectedColor), Color.blue(selectedColor)));
        seekBarAlpha.setSecondTrackColor(Color.argb(Color.alpha(seekBarAlpha.getSecondTrackColor()), Color.red(selectedColor), Color.green(selectedColor), Color.blue(selectedColor)));
        seekBarWidth.setThumbColor(seekBarWidth.getSecondTrackColor());
        seekBarAlpha.setThumbColor(seekBarAlpha.getSecondTrackColor());
    }

//    private void setEraserPaint(PorterDuff.Mode mode) {
//        try {
//            Field field = FreeDrawView.class.getDeclaredField("mCurrentPaint");
//            field.setAccessible(true);
//            Paint paint = (Paint) field.get(mFreeDrawView);
//            Xfermode xFermode = new PorterDuffXfermode(mode);
//            paint.setXfermode(xFermode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void onProgressChanged(View view, int progress) {
        switch (view.getId()) {
            case R.id.seekBarWidth:
                mFreeDrawView.setPaintWidthDp(progress);
                break;
            case R.id.seekBarAlpha:
                mFreeDrawView.setPaintAlpha(progress);
                seekBarAlpha.setTrackColor(Color.argb(progress, Color.red(seekBarAlpha.getTrackColor()), Color.green(seekBarAlpha.getTrackColor()), Color.blue(seekBarAlpha.getTrackColor())));
                seekBarAlpha.setSecondTrackColor(Color.argb(progress, Color.red(seekBarAlpha.getSecondTrackColor()), Color.green(seekBarAlpha.getSecondTrackColor()), Color.blue(seekBarAlpha.getSecondTrackColor())));
                seekBarAlpha.setThumbColor(seekBarAlpha.getSecondTrackColor());
                break;
        }
    }

    @Override
    public void onUndoCountChanged(int undoCount) {
        if (undoCount > 0) {
            ivBackImageLeft.setImageResource(R.drawable.pp_icon_reply);
        } else {
            ivBackImageLeft.setImageResource(R.drawable.pp_icon_reply_dis_enable);
        }
    }

    @Override
    public void onRedoCountChanged(int redoCount) {
        if (redoCount > 0) {
            ivBackImageRight.setImageResource(R.drawable.pp_icon_reply_right);
        } else {
            ivBackImageRight.setImageResource(R.drawable.pp_icon_reply_right_dis_enable);
        }
    }
}
