package com.ogabek.algorithms.stacks;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 *
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented
 * queue should support all the functions of a normal queue (push, peek, pop, and empty).
 */
public class QueueWithStacks {
    Stack<Integer> writeStack;
    Stack<Integer> readStack;

    /** Initialize your data structure here. */
    public QueueWithStacks() {
        writeStack = new Stack<>();
        readStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        writeStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!readStack.isEmpty()) {
            return readStack.pop();
        } else {
            while(!writeStack.isEmpty()) {
                readStack.push(writeStack.pop());
            }
            if (!readStack.isEmpty()) {
                return readStack.pop();
            }
        }

        throw new RuntimeException("Queue is empty!");
    }

    /** Get the front element. */
    public int peek() {
        if(!readStack.isEmpty()) {
            return readStack.peek();
        } else {
            while(!writeStack.isEmpty()) {
                readStack.push(writeStack.pop());
            }
            if (!readStack.isEmpty()) {
                return readStack.peek();
            }
        }

        throw new RuntimeException("Queue is empty!");
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return writeStack.isEmpty() && readStack.isEmpty();
    }
}
