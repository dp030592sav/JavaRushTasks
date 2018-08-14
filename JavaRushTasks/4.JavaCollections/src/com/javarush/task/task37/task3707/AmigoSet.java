package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int) Math.ceil(collection.size() / .75f));
        map = new HashMap<E, Object>(capacity);
        this.addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return !map.containsKey(o);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet<E> result = new AmigoSet<>();

        try {
            result.map = (HashMap<E, Object>) map.clone();
        } catch (Exception e) {
            throw new InternalError(e);
        }

        return result;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        try
        {
            stream.defaultWriteObject();

            stream.writeObject(map.keySet().size());
            for (E elem : map.keySet())
            {
                stream.writeObject(elem);
            }

            stream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            stream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        try
        {
            stream.defaultReadObject();

            Set<E> set = new HashSet<>();
            int size = (int) stream.readObject();
            for (int i = 0; i < size; i++)
            {
                set.add((E) stream.readObject());
            }

            int capacity = (int) stream.readObject();
            float loadFactor = (float) stream.readObject();
            map = new HashMap<>(capacity, loadFactor);
            for (E elem : set)
            {
                map.put(elem, PRESENT);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
