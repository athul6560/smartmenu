package com.andria.qrscan.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SmartMenuUtil {
    public static String PRFF = "smartmenu";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static void init(Context context, boolean isSet) {
        sharedPreferences = context.getSharedPreferences(PRFF, Context.MODE_PRIVATE);
        if (isSet) {
            editor = sharedPreferences.edit();
        }
    }

    public static void setItem(Context context, String item) {
        init(context, true);
        editor.putString("item", item);
        editor.commit();
    }
    public static String getItem(Context context) {
        init(context, false);
        return sharedPreferences.getString("item","");
    }
}
