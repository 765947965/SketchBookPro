package app.wenya.sketchbookpro.db;

import android.content.Context;
import android.content.SharedPreferences;

import app.wenya.sketchbookpro.base.MyApplication;

/**
 * @author: xiewenliang
 * @Filename: OperationUtils
 * @Description: 相关数据
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/2/25 14:01
 */
public class OperationUtils {
    private static final String FILENAME = "OperationData";
    private static OperationUtils instance;
    private SharedPreferences mSp;

    public static SharedPreferences getSharedPreference() {
        if (instance == null || instance.mSp == null) {
            synchronized (OperationUtils.class) {
                if (instance == null || instance.mSp == null) {
                    instance = new OperationUtils();
                    instance.mSp = MyApplication.getContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
                }
            }
        }
        return instance.mSp;
    }


    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putString(key, EncryptionData.encrypt(key, value));
        editor.apply();
    }

    public static String getString(String key) {
        return EncryptionData.decrypt(key, getSharedPreference().getString(key, null));
    }

    public static void putInt(String key, int value, boolean addUserID) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        return getSharedPreference().getInt(key, 0);
    }


    public static void putBoolean(String key, boolean value, boolean addUserID) {
        SharedPreferences.Editor editor = getSharedPreference().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        return getSharedPreference().getBoolean(key, false);
    }

}
