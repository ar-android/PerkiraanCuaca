package com.ahmadrosid.perkiraancuaca;

import android.app.Application;
import android.content.Context;

/**
 * Created by ocittwo on 5/7/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public class PerkiraanCuaca extends Application{

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
