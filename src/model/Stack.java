package model;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<String> top;
    private int size;

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

    public void push(String data) {
        Node<String> temp = new Node<>(data);
        temp.setNext(top);
        top = temp;
        size++;
    }

    public void pushAction(String data) {
        Node<String> temp = new Node<String>(data);
        temp.setNext(top);
        top = temp;
        size++;
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        String result = top.getValue();
        top = top.getNext();
        size--;
        return result;
    }

    public String peek() {
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
}
