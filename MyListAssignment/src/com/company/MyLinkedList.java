package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {
        T element;
        MyNode next;
        MyNode prev;

        MyNode(T element, MyNode next, MyNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        addLast(item);
    }
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        MyNode node = getNode(index);
        node.element = element; // don't return old value
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(item);
        } else {
            MyNode nodeAtIndex = getNode(index);
            MyNode newNode = new MyNode(item, nodeAtIndex, nodeAtIndex.prev);
            if (nodeAtIndex.prev != null) {
                nodeAtIndex.prev.next = newNode;
            } else {
                head = newNode;
            }
            nodeAtIndex.prev = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {
        MyNode newNode = new MyNode(item, head, null);
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode newNode = new MyNode(item, null, tail);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        MyNode node = getNode(index);
        return node.element;
    }

    @Override
    public T getFirst() {
        if (head == null) throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public T getLast() {
        if (tail == null) throw new NoSuchElementException();
        return tail.element;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode nodeToRemove = getNode(index);
        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }
        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }
        size--;
        return nodeToRemove.element;
    }

    @Override
    public void removeFirst() {
        if (head == null) throw new NoSuchElementException();
        remove(0);
    }

    @Override
    public void removeLast() {
        if (tail == null) throw new NoSuchElementException();
        remove(size - 1);
    }
    @Override
    public void sort() {
        if (size > 1) {
            boolean wasChanged;
            do {
                MyNode current = head;
                MyNode previous = null;
                MyNode next = head.next;
                wasChanged = false;

                while ( next != null ) {
                    if (((Comparable<T>)current.element).compareTo(next.element) > 0) {
                        wasChanged = true;
                        if ( previous != null ) {
                            MyNode sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            MyNode sig = next.next;

                            head = next;
                            next.next = current;
                            current.next = sig;
                        }

                        previous = next;
                        next = current.next;
                    } else {
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                }
            } while( wasChanged );
        }
    }

    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        for (int index = 0; current != null; current = current.next, index++) {
            if (object.equals(current.element)) {
                return index;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        for (int index = size - 1; current != null; current = current.prev, index--) {
            if (object.equals(current.element)) {
                return index;
            }
        }
        return -1;
    }
    @Override
    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        MyNode current = head;
        while (current != null) {
            result[i++] = current.element;
            current = current.next;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private MyNode currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = currentNode.element;
                currentNode = currentNode.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private MyNode getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        MyNode node = head;
        if (index < (size >> 1)) {
            for (int i = 0; i < index; i++)
                node = node.next;
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--)
                node = node.prev;
        }
        return node;
    }
}
