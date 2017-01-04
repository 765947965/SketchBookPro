package app.wenya.sketchbookpro.utils.Interface;

import android.app.Activity;

import java.util.List;

import app.wenya.sketchbookpro.model.DrawingImage;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 16:51
 */

public interface ImagesStorage {

    List<DrawingImage> getAllDrawingImage(Activity mActivity, String fileName);

    void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages, String fileName);
}
