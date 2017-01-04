package app.wenya.sketchbookpro.base;

import android.app.Application;
import android.content.Context;

import org.joda.time.DateTimeZone;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2017/1/3 14:23
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //默认设置为北京时间
        DateTimeZone.setDefault(DateTimeZone.forOffsetHours(8));
    }

    public static Context getContext() {
        return myApplication;
    }

}
