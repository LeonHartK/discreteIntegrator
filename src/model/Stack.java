package model;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<Task> top;
    private int size;
    private String action;

    public Stack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(Task data) {
        Node<Task> temp = new Node<>(data);
        temp.setNext(top);
        top = temp;
        size++;
    }

    public void pushAction(String action, Task data) {
        Node<Task> temp = new Node<>(data);
        temp.setNext(top);
        top = temp;
        this.action = action;
        size++;
    }

    public Task pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Task result = top.getValue();
        top = top.getNext();
        size--;
        return result;
    }

    public Task peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getValue();
    }

    public String reverse(Stack<T> pila, Stack<T> secondPila, String msg) {
        if (pila.isEmpty()) {
            return msg;
        } else {
            secondPila.push(pila.top.getValue());
            pila.pop();
            msg += secondPila.peek();
            return reverse(pila, secondPila, msg);
        }
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
