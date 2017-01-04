package app.wenya.sketchbookpro.data.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import app.wenya.sketchbookpro.data.Instance.ImageLoadInstance;
import app.wenya.sketchbookpro.data.Interface.ImageLoadInterface;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 17:31
 */

public class ImageLoadUtil {
    private static ImageLoadUtil mImageLoadUtil;
    private ImageLoadInterface mImageLoadInterface;

    private ImageLoadUtil() {
        mImageLoadInterface = new ImageLoadInstance();
    }


    public static ImageLoadUtil instance() {
        if (mImageLoadUtil == null) {
            synchronized (ImageLoadUtil.class) {
                if (mImageLoadUtil == null) {
                    mImageLoadUtil = new ImageLoadUtil();
                }
            }
        }
        return mImageLoadUtil;
    }


    public void loadImageAutoSize(Activity mActivity, String path, ImageView mImageView) {
        mImageLoadInterface.loadImageAutoSize(mActivity, path, mImageView);
    }


    public void loadImageAutoSize(Activity mActivity, Uri uri, ImageView mImageView) {
        mImageLoadInterface.loadImageAutoSize(mActivity, uri, mImageView);
    }

    public void loadImageAutoSize(Activity mActivity, int resId, ImageView mImageView) {
        mImageLoadInterface.loadImageAutoSize(mActivity, resId, mImageView);
    }

    public void loadImageCenterCrop(Activity mActivity, String path, ImageView mImageView) {
        mImageLoadInterface.loadImageCenterCrop(mActivity, path, mImageView);
    }

    public void loadImageCenterCrop(Activity mActivity, Uri uri, ImageView mImageView) {
        mImageLoadInterface.loadImageCenterCrop(mActivity, uri, mImageView);
    }

    public void loadImageCenterCrop(Activity mActivity, int resId, ImageView mImageView) {
        mImageLoadInterface.loadImageCenterCrop(mActivity, resId, mImageView);
    }

    public void loadImageCircle(Activity mActivity, String path, ImageView mImageView) {
        mImageLoadInterface.loadImageCircle(mActivity, path, mImageView);
    }

    public void loadImageCircle(Activity mActivity, Uri uri, ImageView mImageView) {
        mImageLoadInterface.loadImageCircle(mActivity, uri, mImageView);
    }

    public void loadImageCircle(Activity mActivity, int resId, ImageView mImageView) {
        mImageLoadInterface.loadImageCircle(mActivity, resId, mImageView);
    }

    public void loadImageRound(Activity mActivity, String path, ImageView mImageView, float angular) {
        mImageLoadInterface.loadImageRound(mActivity, path, mImageView, angular);
    }

    public void loadImageRound(Activity mActivity, Uri uri, ImageView mImageView, float angular) {
        mImageLoadInterface.loadImageRound(mActivity, uri, mImageView, angular);
    }

    public void loadImageRound(Activity mActivity, int resId, ImageView mImageView, float angular) {
        mImageLoadInterface.loadImageRound(mActivity, resId, mImageView, angular);
    }
}
