package com.geektech.util;


public interface SharedStorage<T> {

    int getInt(String key);

    T get(String key, T defaultValue);

    void set(String key, T value);

    T contains(String key);

}
