package app.wenya.sketchbookpro.data.Instance;

import android.app.Activity;

import java.util.List;

import app.wenya.sketchbookpro.data.Interface.ImagesStorageInterface;
import app.wenya.sketchbookpro.model.DrawingImage;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 17:06
 */

public class ImagesStorageInstance implements ImagesStorageInterface {
    @Override
    public List<DrawingImage> getAllDrawingImage(Activity mActivity) {
        return null;
    }

    @Override
    public void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages) {

    }
}
