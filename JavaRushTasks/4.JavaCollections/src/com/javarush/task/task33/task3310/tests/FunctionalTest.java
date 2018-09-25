package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {

    public void testStorage(Shortener shortener){
        String str1 = Helper.generateRandomString();
        String str2 = Helper.generateRandomString();
        String str3 = str1;

        Long id1 = shortener.getId(str1);
        Long id2 = shortener.getId(str2);
        Long id3 = shortener.getId(str3);

        // Проверять, что идентификатор для 2 строки не равен идентификатору для 1 и 3 строк.
        Assert.assertNotEquals(id2, id1);
        Assert.assertNotEquals(id2, id3);

        // Проверять, что идентификаторы для 1 и 3 строк равны.
        Assert.assertEquals(id1, id3);

        String str1res = shortener.getString(id1);
        String str2res= shortener.getString(id2);
        String str3res = shortener.getString(id3);

        // Проверять, что строки, полученные в предыдущем пункте, эквивалентны оригинальным.
        Assert.assertEquals(str1, str1res);
        Assert.assertEquals(str2, str2res);
        Assert.assertEquals(str3, str3res);
    }

    @Test
    public void testHashMapStorageStrategy(){
        StorageStrategy strategy = new HashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy(){
        StorageStrategy strategy = new OurHashMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy(){
        StorageStrategy strategy = new FileStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy(){
        StorageStrategy strategy = new HashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy(){
        StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy(){
        StorageStrategy strategy = new OurHashBiMapStorageStrategy();
        Shortener shortener = new Shortener(strategy);
        testStorage(shortener);
    }
}
