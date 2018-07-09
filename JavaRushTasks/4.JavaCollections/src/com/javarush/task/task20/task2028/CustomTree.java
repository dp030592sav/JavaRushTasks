package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    @Override
    public boolean add(String entryName) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> entry = queue.poll();
            if (addEntry(entry, entryName)) {
                return true;
            } else {
                queue.offer(entry.leftChild);
                queue.offer(entry.rightChild);
            }
        }

        return false;
    }

    private boolean addEntry(Entry<String> entry, String entryName) {
        boolean result = false;
        entry.checkChildren();

        if (entry.availableToAddLeftChildren) {
            entry.leftChild = new Entry<>(entryName);
            entry.leftChild.parent = entry;
            result = true;
        } else if (entry.availableToAddRightChildren) {
            entry.rightChild = new Entry<>(entryName);
            entry.rightChild.parent = entry;
            result = true;
        }

        return result;
    }

    @Override
    public int size() {
        return sizeEntry(root) - 1;
    }

    private int sizeEntry(Entry<String> entry) {
        if (entry == null)
            return 0;
        return sizeEntry(entry.leftChild) + 1 + sizeEntry(entry.rightChild);
    }

    public String getParent(String entryName) {
        return getParentEntry(root, entryName);
    }

    private String getParentEntry(Entry<String> entry, String entryName) {
        if (entry == null)
            return null;
        else if (entry.elementName.equals(entryName))
            return entry.parent.elementName;

        String left = getParentEntry(entry.leftChild, entryName);
        if (left != null)
            return left;

        String rigth = getParentEntry(entry.rightChild, entryName);
        if (rigth != null)
            return rigth;

        return null;
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            if (rightChild != null)
                availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
