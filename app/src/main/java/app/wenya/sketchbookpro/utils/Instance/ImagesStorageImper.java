package app.wenya.sketchbookpro.utils.Instance;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.wenya.sketchbookpro.db.OperationUtils;
import app.wenya.sketchbookpro.utils.Interface.ImagesStorage;
import app.wenya.sketchbookpro.model.DrawingImage;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 17:06
 */

public class ImagesStorageImper implements ImagesStorage {
    @Override
    public List<DrawingImage> getAllDrawingImage(Activity mActivity, String fileName) {
        String data = OperationUtils.getString(ImagesStorageImper.class.getSimpleName() + fileName);
        if (TextUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        return com.alibaba.fastjson.JSONArray.parseArray(data, DrawingImage.class);
    }

    @Override
    public void setAllDrawingImage(Activity mActivity, List<DrawingImage> mDrawingImages, String fileName) {
        JSONArray jsonArray = new JSONArray();
        for (DrawingImage item : mDrawingImages) {
            try {
                jsonArray.put(new JSONObject(JSON.toJSONString(item)));
            } catch (Exception e) {
            }
        }
        OperationUtils.putString(ImagesStorageImper.class.getSimpleName() + fileName, jsonArray.toString());
    }
}
