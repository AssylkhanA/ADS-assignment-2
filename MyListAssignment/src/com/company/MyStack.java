package com.company;
import java.util.NoSuchElementException;
import com.company.MyArrayList;
public class MyStack<T> {
    private MyArrayList<T> list;

    public MyStack() {
        list = new MyArrayList<>();
    }

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        // get last element
        T item = list.getLast();
        // Remove last element
        list.remove(list.size() - 1);
        // Return saved data
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}
