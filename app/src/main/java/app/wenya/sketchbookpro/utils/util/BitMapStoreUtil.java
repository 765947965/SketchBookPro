package app.wenya.sketchbookpro.utils.util;

import android.app.Activity;
import android.graphics.Bitmap;

import app.wenya.sketchbookpro.utils.Instance.BitMapStoreImper;
import app.wenya.sketchbookpro.utils.Interface.BitMapStore;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/4 15:49
 */

public class BitMapStoreUtil {
    private static BitMapStoreUtil mBitMapStoreUtil;
    private BitMapStore mBitMapStore;

    private BitMapStoreUtil() {
        mBitMapStore = new BitMapStoreImper();
    }

    public static BitMapStoreUtil instance() {
        if (mBitMapStoreUtil == null) {
            synchronized (BitMapStoreUtil.class) {
                if (mBitMapStoreUtil == null) {
                    mBitMapStoreUtil = new BitMapStoreUtil();
                }
            }
        }
        return mBitMapStoreUtil;
    }

    public void saveBitMap(Activity mActivity, Bitmap mBitmap, String folder, String fileName) {
        mBitMapStore.saveBitMap(mActivity, mBitmap, folder, fileName);
    }

    public String getBitMap(Activity mActivity, String folder, String fileName) {
        return mBitMapStore.getBitMapPatch(mActivity, folder, fileName);
    }
}
