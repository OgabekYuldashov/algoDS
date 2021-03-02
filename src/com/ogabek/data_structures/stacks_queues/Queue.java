package com.ogabek.data_structures.stacks_queues;

public class Queue {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println("Peek: " + queue.peek());
        try {
            queue.dequeue();
            queue.dequeue();
            queue.dequeue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        queue.print();
    }

    private int size;
    private Node start;
    private Node end;

    public Queue() {
        size = 0;
        start = null;
        end = null;
    }

    public String peek() {
        return isEmpty() ? null : start.getValue();
    }

    public void enqueue(String value) {
        Node newNode = new Node(value);

        if(isEmpty()) {
            start = newNode;
            end = newNode;
            size++;
            return;
        }

        end.next = newNode;
        end = newNode;
        size++;
    }

    public String dequeue() throws Exception {
        if(isEmpty())
            throw new Exception("Cannot dequeue an empty queue");

        if(size == 1)
            end = null;

        String dequeued = start.getValue();
        start = start.getNext();
        size--;
        return dequeued;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        StringBuilder builder = new StringBuilder("size: " + size);

        Node current = start;
        while (current != null) {
            builder.append("\n").append(current.getValue());
            current = current.getNext();
        }
        System.out.println(builder.toString());
    }

    private class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
            this.next = null;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

    }
}
