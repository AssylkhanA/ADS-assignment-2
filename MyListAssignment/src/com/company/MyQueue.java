package com.company;
import java.util.NoSuchElementException;
public class MyQueue<T> {
    private MyLinkedList<T> list;
    public MyQueue() {
        list = new MyLinkedList<>();
    }

    public void enqueue(T item) {
        list.addLast(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        T item = list.getFirst(); // Take first element
        list.removeFirst();      // Remove first element
        return item;             // Take back element
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}

