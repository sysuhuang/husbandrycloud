package com.example.husbandrycloud.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_USER_ROLE = "user_role";

    public static void setUserRole(Context context, String role) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_ROLE, role);
        editor.apply();
    }

    public static String getUserRole(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_USER_ROLE, null);
    }
}