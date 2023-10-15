package model;

import java.util.NoSuchElementException;

public class Cola<T> {
    private Node<Task> front;
    private Node<Task> rear;
    private int size;

    public Cola() {
        front = null;
        rear = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Task data) {
        Node<Task> temp = new Node<>(data);
        if (isEmpty()) {
            front = temp;
        } else {
            rear.setNext(temp);
        }
        rear = temp;
        size++;
    }

    public Task dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Task result = front.getValue();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        size--;
        return result;
    }

    public void print() {
        Node<Task> current = front;
        if (isEmpty()) {
            return;
        } else {
            int i = 1;
            while (current != null) {
                System.out.print(i + ") " + current.getValue().getTitle() + "\n");
                current = current.getNext();
                i++;
            }
        }
    }

}
