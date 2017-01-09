package app.wenya.sketchbookpro.ui.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import app.wenya.sketchbookpro.utils.util.PermissionUtils;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 14:20
 */

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isLoadData;
    private String[] permissions;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isLoadData) {
            isLoadData = true;
            checkPermission();
        }
    }


    protected abstract void loadData();

    /*权限开始*/

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT < 23 || permissions == null || permissions.length == 0 || PermissionUtils.checkPermission(this, permissions)) {
            loadData();
        } else if (PermissionUtils.checkPermissionRationale(this, permissions)) {
            // 已经拒绝过 当时没有勾选不再提示
            new MaterialDialog.Builder(this).title("权限已被拒绝").content("您已经拒绝过我们申请授权，请同意授权，否则功能无法正常使用！").positiveText("继续").negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            ActivityCompat.requestPermissions(BaseActivity.this, permissions, 101);
                        }
                    }).onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    checkPermission();
                }
            }).canceledOnTouchOutside(false).show().setCancelable(false);
        } else {
            // 申请权限 当时可能已经勾选了不再提示
            ActivityCompat.requestPermissions(BaseActivity.this, permissions, 101);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean pass = true;
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_DENIED && !ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {
                // 拒绝了权限 并且勾选了不再提示
                new MaterialDialog.Builder(this).title("权限已被拒绝").content("您已经拒绝过我们申请授权，去设置界面打开应用权限，否则功能无法正常使用！").positiveText("继续").negativeText("取消")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 102);
                            }
                        }).onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        checkPermission();
                    }
                }).canceledOnTouchOutside(false).show().setCancelable(false);
                return;
            }
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                // 拒绝了权限
                pass = false;
                break;
            }
        }
        if (pass) {
            loadData();
        } else {
            checkPermission();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 102) {
            checkPermission();
        }
    }

    /*权限结束*/
}
