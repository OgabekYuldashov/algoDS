package com.ogabek.data_structures.stacks_queues;

import java.util.NoSuchElementException;

public class QueueWithStacks {

    public static void main(String[] args) {
        QueueWithStacks myQueue = new QueueWithStacks();
        myQueue.enqueue("1");
        myQueue.enqueue("2");
        myQueue.enqueue("3");
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.peek());

    }

    private int size;
    private final Stack enqueueStack;
    private final Stack dequeueStack;

    public QueueWithStacks() {
        enqueueStack = new Stack();
        dequeueStack = new Stack();
    }

    /**
     * Adds the element to the queue
     * @param value String value to be added to the queue
     */
    public void enqueue(String value) {
        enqueueStack.push(value);
        size++;
    }

    /**
     * Removes the first element from the queue based on FIFO principle
     * @return the first element in the queue which has been removed
     * @throws NoSuchElementException if the queue is empty
     */
    public String dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();

        moveToDequeueStack();
        size--;
        return dequeueStack.pop();
    }

    /**
     * Returns the first element in the queue without removing it.
     * @return the first element in the queue, without removing it from the queue. Returns null if the queue is empty
     */
    public String peek() {
        if(isEmpty())
            return null;

        moveToDequeueStack();
        return dequeueStack.peek();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void moveToDequeueStack() {
        if(dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
    }

}
