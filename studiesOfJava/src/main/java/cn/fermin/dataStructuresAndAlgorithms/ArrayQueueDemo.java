package cn.fermin.dataStructuresAndAlgorithms;

public class ArrayQueueDemo {

}

class ArrayQueue {
    private int front = 0;
    private int tail = 0;
    private int[] objects;
    private int capacity = 1;

    ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.objects = new int[capacity];
    }

    public void add(int t) {
        if (isFull()) {
            throw new RuntimeException("队列已经满了");
        }

    }

    public void del(int index) {

    }

    public boolean isEmpty() {
        return this.front == this.tail;
    }

    public boolean isFull() {
        return (this.tail + 1) % this.capacity == this.front;
    }

    public int size() {
        return (this.tail + this.capacity - this.front) % this.capacity;
    }
}
