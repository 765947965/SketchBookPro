package app.wenya.sketchbookpro.utils.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/9 10:14
 */

public class PermissionUtils {

    /**是否已经允许申请该权限
     * @param mActivity
     * @param permissions
     * @return true 已经允许权限 false未允许权限
     */
    public static Boolean checkPermission(Activity mActivity, String[] permissions) {
        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED)
                    return false;
            }
        }
        return true;
    }

    /**是否曾经拒绝过申请该权限
     * @param mActivity
     * @param permissions
     * @return true 曾经拒绝过申请该权限 false 没有拒绝过申请该权限
     */
    public static Boolean checkPermissionRationale(Activity mActivity, String[] permissions) {
        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission))
                    return true;
            }
        }
        return false;
    }
}
