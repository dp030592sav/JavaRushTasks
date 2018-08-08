package com.javarush.task.task34.task3408;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        if (!cache.containsKey(key))
            cache.put(key, clazz.getConstructor(key.getClass()).newInstance(key));

        return cache.get(key);
    }

    public boolean put(V obj) {
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey");
            method.setAccessible(true);

            K key = (K) method.invoke(obj);
            cache.put(key, obj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
