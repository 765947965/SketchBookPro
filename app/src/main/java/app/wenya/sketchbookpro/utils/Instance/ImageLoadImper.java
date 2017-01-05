package app.wenya.sketchbookpro.utils.Instance;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import app.wenya.sketchbookpro.utils.Interface.ImageLoad;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 17:30
 */

public class ImageLoadImper implements ImageLoad {
    @Override
    public void loadImageAutoSize(Activity mActivity, String path, ImageView mImageView) {
        Glide.with(mActivity).load(path).diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageAutoSize(Activity mActivity, Uri uri, ImageView mImageView) {
        Glide.with(mActivity).load(uri).diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageAutoSize(Activity mActivity, int resId, ImageView mImageView) {
        Glide.with(mActivity).load(resId).diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageCenterCrop(Activity mActivity, String path, ImageView mImageView) {
        Glide.with(mActivity).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageCenterCrop(Activity mActivity, Uri uri, ImageView mImageView) {
        Glide.with(mActivity).load(uri).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageCenterCrop(Activity mActivity, int resId, ImageView mImageView) {
        Glide.with(mActivity).load(resId).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE).into(mImageView);
    }

    @Override
    public void loadImageCircle(Activity mActivity, String path, ImageView mImageView) {

    }

    @Override
    public void loadImageCircle(Activity mActivity, Uri uri, ImageView mImageView) {

    }

    @Override
    public void loadImageCircle(Activity mActivity, int resId, ImageView mImageView) {

    }

    @Override
    public void loadImageRound(Activity mActivity, String path, ImageView mImageView, float angular) {

    }

    @Override
    public void loadImageRound(Activity mActivity, Uri uri, ImageView mImageView, float angular) {

    }

    @Override
    public void loadImageRound(Activity mActivity, int resId, ImageView mImageView, float angular) {

    }
}
