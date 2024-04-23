package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10]; // initial size of list
        size = 0;
    }

    @Override
    public void add(T item) {
        ensureCapacity();
        elements[size++] = item;
    }

    @Override
    public void set(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = item;

    }


    @Override
    public void add(int index, T item) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        // Сдвигаем элементы вправо
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = item;
        size++;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T item = elementData(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return item;
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        remove(0);
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        remove(size - 1);
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elementData(index);
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    @Override
    public void sort() {
        // Insertion sort
        for (int i = 1; i < size; i++) {
            T key = elementData(i);
            int j = i - 1;

            while (j >= 0 && ((Comparable<T>) elementData(j)).compareTo(key) > 0) {
                elements[j + 1] = elements[j];
                j--;
            }
            elements[j + 1] = key;
        }
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        return newArray;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elementData(currentIndex++);
            }

            @Override
            public void remove() {
                if (currentIndex == 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                MyArrayList.this.remove(--currentIndex);
            }
        };
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[2 * elements.length];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) elements[index];
    }
}
