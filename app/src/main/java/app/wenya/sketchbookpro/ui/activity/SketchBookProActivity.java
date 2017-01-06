package app.wenya.sketchbookpro.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
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


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.base.Constant;
import app.wenya.sketchbookpro.model.DrawingImage;
import app.wenya.sketchbookpro.ui.base.BaseActivity;
import app.wenya.sketchbookpro.ui.base.ViewHolder;
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

public class SketchBookProActivity extends BaseActivity implements View.OnClickListener, FreeDrawView.DrawCreatorListener, ColorChooserDialog.ColorCallback {
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
        initSectorMenuButton();
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
                        setEraserPaint();
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
    }

    private void setEraserPaint() {
//        try {
//            Field field = FreeDrawView.class.getDeclaredField("mCurrentPaint");
//            field.setAccessible(true);
//            Paint paint = (Paint) field.get(mFreeDrawView);
//            Xfermode xFermode = new PorterDuffXfermode(mode);
//            paint.setXfermode(xFermode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        mFreeDrawView.setPaintColor(Color.WHITE);
    }
}
