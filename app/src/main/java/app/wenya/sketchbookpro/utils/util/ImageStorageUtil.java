package app.wenya.sketchbookpro.utils.util;


import android.app.Activity;

import java.util.List;

import app.wenya.sketchbookpro.utils.Instance.ImagesStorageImper;
import app.wenya.sketchbookpro.utils.Interface.ImagesStorage;
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
    private ImagesStorage mImagesStorageInterface;

    private ImageStorageUtil() {
        mImagesStorageInterface = new ImagesStorageImper();
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

    public List<DrawingImage> getAllDrawingImage(Activity mActivity, String fileName) {
        return mImagesStorageInterface.getAllDrawingImage(mActivity, fileName);
    }

    public void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages, String fileName) {
        mImagesStorageInterface.setAllDrawingImage(mActivity, mDrawingImages, fileName);
    }
}
