package model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    public void insert(T value) {
        heap.add(value);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(current).compareTo(heap.get(parentIndex(current))) > 0) {
            swap(current, parentIndex(current));
            current = parentIndex(current);
        }
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T root = heap.get(0);
        if (heap.size() == 1) {
            heap.remove(0);
            return root;
        }

        heap.set(0, heap.remove(heap.size() - 1));
        heapify(0);

        return root;
    }

    private void heapify(int index) {
        int largestIndex = index;

        int leftIndex = leftChildIndex(index);
        if (leftIndex < heap.size() && heap.get(leftIndex).compareTo(heap.get(largestIndex)) > 0) {
            largestIndex = leftIndex;
        }

        int rightIndex = rightChildIndex(index);
        if (rightIndex < heap.size() && heap.get(rightIndex).compareTo(heap.get(largestIndex)) > 0) {
            largestIndex = rightIndex;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            heapify(largestIndex);
        }
    }

    private int leftChildIndex(int i) {
        return 2 * i + 1;
    }

    private int rightChildIndex(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public T peek() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }

        return heap.get(0);
    }
}
