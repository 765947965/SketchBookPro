package app.wenya.sketchbookpro.data.Interface;

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

public interface ImagesStorageInterface {

    List<DrawingImage> getAllDrawingImage(Activity mActivity);

    void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages);
}
