package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    // Он должен возвращать время в миллисекундах необходимое для получения идентификаторов для всех строк из strings.
    // Идентификаторы должны быть записаны в ids.
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTime = new Date();

        for (String i : strings)
            ids.add(shortener.getId(i));

        Date endTime = new Date();

        return endTime.getTime() - startTime.getTime();
    }

    // должен возвращать время в миллисекундах необходимое для получения строк для всех строк из ids.
    // Строки должны быть записаны в strings
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTime = new Date();

        for (Long i : ids)
            strings.add(shortener.getString(i));

        Date endTime = new Date();

        return endTime.getTime() - startTime.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> strings = new HashSet<>();
        for (int i = 0; i < 10000; i++)
            strings.add(Helper.generateRandomString());

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();

        Long time1 = getTimeForGettingIds(shortener1, strings, ids1);
        Long time2 = getTimeForGettingIds(shortener2, strings, ids2);

        // Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше,
        // чем для shortener2.
        Assert.assertTrue(time1 > time2);

        Set<String> strings1 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();

        time1 = getTimeForGettingStrings(shortener1, ids1, strings1);
        time2 = getTimeForGettingStrings(shortener2, ids2, strings2);

        // Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно
        // времени для shortener2
        Assert.assertEquals(time1, time2, 30);
    }
}
