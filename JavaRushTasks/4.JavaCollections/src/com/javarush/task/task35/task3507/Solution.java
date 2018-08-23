package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws Exception {
        Set<Animal> result = new HashSet<>();

        File[] files = new File(pathToAnimals).listFiles();

        for (File file : files) {
            if (file.isDirectory() || !file.getName().endsWith(".class"))
                continue;

            Class clazz = new ModuleLoader().load(file.getPath());

            if (Animal.class.isAssignableFrom(clazz)) {
                Constructor<?>[] constructors = clazz.getConstructors();

                for (Constructor constructor : constructors) {
                    if (constructor.getParameterCount() == 0) {
                        Animal animal = (Animal) clazz.newInstance();
                        result.add(animal);
                        break;
                    }
                }

            }
        }

        return result;
    }
}