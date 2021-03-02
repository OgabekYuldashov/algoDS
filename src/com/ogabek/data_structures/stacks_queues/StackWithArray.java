package com.ogabek.data_structures.stacks_queues;

public class StackWithArray {
    public static void main(String[] args) {
        StackWithArray stack = new StackWithArray();
        try {
            stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");
        stack.push("7");
        stack.push("8");
        System.out.println("Peek: " + stack.peek());
        try {
            System.out.println("Pop: " + stack.pop());
            System.out.println("Pop: " + stack.pop());
            System.out.println("Pop: " + stack.pop());
            System.out.println("Pop: " + stack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("*******PRINT*******");
        stack.print();
    }

    private int size = 0;
    private String[] data;

    public StackWithArray() {
        data = new String[4];
    }

    public String peek(){
        if (isEmpty())
            return null;
        return data[size-1];
    }

    public void push(String value) {

        double ratio = (double) size/data.length;
        if (ratio > 0.75) {
            resizeArray(true);
        }
        data[size++] = value;
    }

    public String pop() throws Exception {
        if (isEmpty())
            throw new Exception("Cannot pop from empty stack");

        double ratio = (double) size/(double)(data.length/2);
        if (ratio < 0.75) {
            resizeArray(false);
        }

        String toBePopped = data[size-1];
        data[--size] = null;

        return toBePopped;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray(boolean shouldIncrease) {
        // calculate the new size for the array
        int newSize = shouldIncrease ? data.length*2 : data.length/2;
        String[] newArray = new String[newSize];

        // copying the contents of the old array to the new one
        if (size >= 0) System.arraycopy(data, 0, newArray, 0, size);

        data = newArray;
    }

    public void print() {
        StringBuilder builder = new StringBuilder("size: " + this.size);

        for(int i = 0; i<size; i++) {
            builder.append("\n").append(data[i]);
        }

        System.out.println(builder.toString());
    }
}
