package com.company;

import java.util.NoSuchElementException;

public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public void add(T item) {
        heap.add(item);  // Add to last of heap
        siftUp();        // Move to element to right position
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T minItem = heap.getFirst();
        heap.set(0, heap.remove(heap.size() - 1)); // Move last element to root
        siftDown();  // move to the down element until the right position
        return minItem;
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return heap.getFirst(); //  Back to the element to root of heap
    }

    private void siftUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T item = heap.get(index);
            T parent = heap.get(parentIndex);
            if (item.compareTo(parent) < 0) {
                //Exchange to parent element's value
                heap.set(index, parent);
                heap.set(parentIndex, item);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int index = 0;
        int leftChildIndex = 2 * index + 1;
        while (leftChildIndex < heap.size()) {
            int smallerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < heap.size() && heap.get(rightChildIndex).compareTo(heap.get(leftChildIndex)) < 0) {
                smallerChildIndex = rightChildIndex;
            }
            if (heap.get(index).compareTo(heap.get(smallerChildIndex)) > 0) {
                T tmp = heap.get(index);
                heap.set(index, heap.get(smallerChildIndex));
                heap.set(smallerChildIndex, tmp);
            } else {
                break;
            }
            index = smallerChildIndex;
            leftChildIndex = 2 * index + 1;
        }
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    // Take size of heap
    public int size() {
        return heap.size();
    }
}
