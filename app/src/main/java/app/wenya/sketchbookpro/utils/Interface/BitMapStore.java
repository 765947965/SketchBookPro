package app.wenya.sketchbookpro.utils.Interface;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/4 15:45
 */

public interface BitMapStore {
    void saveBitMap(Activity mActivity, Bitmap mBitmap, String mPatch);

    Bitmap getBitMap(Activity mActivity, String mPatch);
}
