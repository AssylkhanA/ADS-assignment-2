package com.company;

import java.util.Iterator;
import com.company.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> myList = new MyArrayList<>();
        MyLinkedList<String> myLinkedList= new MyLinkedList<>();
        MyStack<String> stack = new MyStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        MyQueue<String> queue = new MyQueue<>();
        // Add element to the queue
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        // Printing first element without removing
        System.out.println("In the start of the queue: " + queue.peek());


        System.out.println("On the top of stack: " + stack.peek());
        myLinkedList.add("Hello");
        myLinkedList.add("World");
        myLinkedList.add("!");
        System.out.println("Original second item: " + myLinkedList.get(1)); // World
        myLinkedList.set(1, "There");
        System.out.println("New second item: " + myLinkedList.get(1)); // There
        System.out.println("List contain:");
        for (String item : myLinkedList) {
            System.out.println(item);
        }
        myLinkedList.addFirst("Aitu");
        System.out.println(myLinkedList.get(0));
        myLinkedList.addLast("Sheker");
        System.out.println(myLinkedList.getLast());



        myList.add("El 1");
        myList.add("El 2");
        myList.add("El 3");
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());
        // Delete elements and type the result
        myList.remove(1); // Remove element with index 1
        System.out.println("element with index 1 deleted. Size of list right now: " + myList.size());

        // Печатаем все элементы списка
        for (int i = 0; i < myList.size(); i++) {
            System.out.println("Element with index " + i + ": " + myList.get(i));
        }

        // Checking, exist this element or not
        boolean exists = myList.exists("Element 3");
        System.out.println("Element 3 exist in the list: " + exists);

        // Cleaning list
        myList.clear();
        System.out.println("List cleared. The size of list: " + myList.size());
        MyMinHeap<Integer> minHeap = new MyMinHeap<>();
        minHeap.add(40);
        minHeap.add(15);
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(5);
        System.out.println("The smallest element in the Heap: " + minHeap.peek());
    }
}
