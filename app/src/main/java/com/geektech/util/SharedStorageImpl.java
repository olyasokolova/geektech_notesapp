package com.geektech.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedStorageImpl implements SharedStorage<Boolean> {

    private SharedPreferences sharedPreferences;

    public SharedStorageImpl(Context context, String fileName) {
        sharedPreferences = context.getApplicationContext()
                .getSharedPreferences(
                        fileName,
                        Context.MODE_PRIVATE
                );
    }

    @Override
    public int getInt(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    @Override
    public Boolean get(String key, Boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public void set(String key, Boolean value) {
        sharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    @Override
    public Boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}
