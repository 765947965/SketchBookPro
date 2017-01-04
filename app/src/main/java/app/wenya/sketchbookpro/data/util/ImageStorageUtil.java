package app.wenya.sketchbookpro.data.util;


import android.app.Activity;

import java.util.List;

import app.wenya.sketchbookpro.data.Instance.ImagesStorageInstance;
import app.wenya.sketchbookpro.data.Interface.ImagesStorageInterface;
import app.wenya.sketchbookpro.model.DrawingImage;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 16:50
 */

public class ImageStorageUtil {
    private static ImageStorageUtil mImagesStorageUtil;
    private ImagesStorageInterface mImagesStorageInterface;

    private ImageStorageUtil() {
        mImagesStorageInterface = new ImagesStorageInstance();
    }

    public static ImageStorageUtil instance() {
        if (mImagesStorageUtil == null) {
            synchronized (ImageStorageUtil.class) {
                if (mImagesStorageUtil == null) {
                    mImagesStorageUtil = new ImageStorageUtil();
                }
            }
        }
        return mImagesStorageUtil;
    }

    public List<DrawingImage> getAllDrawingImage(Activity mActivity) {
        return mImagesStorageInterface.getAllDrawingImage(mActivity);
    }

    public void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages) {
        mImagesStorageInterface.setAllDrawingImage(mActivity, mDrawingImages);
    }
}
