package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

public class Shortener {
    // поле будет отвечать за последнее значение идентификатора
    private Long lastId = new Long(0);
    // стратегия хранения данных
    private StorageStrategy storageStrategy;

    // конструктор
    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    // будет возвращать идентификатор id для заданной строки
    public synchronized Long getId(String value) {
        if (storageStrategy.containsValue(value))
            return storageStrategy.getKey(value);
        else {
            storageStrategy.put(++lastId, value);
            return lastId;
        }
    }

    // будет возвращать строку для заданного идентификатора или null,
    // если передан неверный идентификатор
    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
