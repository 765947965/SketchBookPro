package app.wenya.sketchbookpro.data.Interface;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 17:24
 */

public interface ImageLoadInterface {
    void loadImageAutoSize(Activity mActivity, String path, ImageView mImageView);

    void loadImageAutoSize(Activity mActivity, Uri uri, ImageView mImageView);

    void loadImageAutoSize(Activity mActivity, int resId, ImageView mImageView);

    void loadImageCenterCrop(Activity mActivity, String path, ImageView mImageView);

    void loadImageCenterCrop(Activity mActivity, Uri uri, ImageView mImageView);

    void loadImageCenterCrop(Activity mActivity, int resId, ImageView mImageView);

    void loadImageCircle(Activity mActivity, String path, ImageView mImageView);

    void loadImageCircle(Activity mActivity, Uri uri, ImageView mImageView);

    void loadImageCircle(Activity mActivity, int resId, ImageView mImageView);

    void loadImageRound(Activity mActivity, String path, ImageView mImageView, float angular);

    void loadImageRound(Activity mActivity, Uri uri, ImageView mImageView, float angular);

    void loadImageRound(Activity mActivity, int resId, ImageView mImageView, float angular);
}
