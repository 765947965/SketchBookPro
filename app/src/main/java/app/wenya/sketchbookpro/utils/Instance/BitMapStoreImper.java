package app.wenya.sketchbookpro.utils.Instance;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

import app.wenya.sketchbookpro.R;
import app.wenya.sketchbookpro.utils.Interface.BitMapStore;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/4 15:49
 */

public class BitMapStoreImper implements BitMapStore {


    @Override
    public void saveBitMap(Activity mActivity, Bitmap mBitmap, String folder, String fileName) {
        File folderFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + mActivity.getResources().getString(R.string.app_name), folder);
        if (!folderFile.exists()) folderFile.mkdirs();
        try {
            File file = new File(folderFile.getAbsolutePath(), fileName);
            FileOutputStream out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBitMapPatch(Activity mActivity, String folder, String fileName) {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + mActivity.getResources().getString(R.string.app_name) + "/" + folder + "/" + fileName;
    }
}
