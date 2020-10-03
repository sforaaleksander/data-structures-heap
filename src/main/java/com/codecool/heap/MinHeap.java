package com.codecool.heap;

public class MinHeap {
    private int[] elements;
    private int size;
    private int capacity;

    public MinHeap(){
        this.capacity = 1;
        this.elements = new int[capacity];
        this.size = 0 ;
    }

    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1;}
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 2;}
    private int getParentIndex(int childIndex) { return (childIndex - 1) / 2;}

    private boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size;}
    private boolean hasRightChild(int index) { return getRightChildIndex(index) < size;}
    private boolean hasParent(int index) { return getParentIndex(index) >= 0;}

    private int getLeftChild(int index) { return elements[getLeftChildIndex(index)];}
    private int getRightChild(int index) { return elements[getRightChildIndex(index)];}
    private int getParent(int index) { return elements[getParentIndex(index)];}

    private void swap(int indexOne, int indexTwo) {
        int temp = elements[indexOne];
        elements[indexOne] = elements[indexTwo];
        elements[indexTwo] = temp;
    }

    private void resizeIfFull(){
        if (size == capacity) {
            int[] resized = new int[capacity * 2];
            if (capacity >= 0) System.arraycopy(elements, 0, resized, 0, capacity);
            elements = resized;
        }
    }

    private void checkIfNotEmpty() {
        if (size == 0) throw new IllegalStateException("The heap is empty");
    }

    private void heapifyUp() {
        int index = elements[size - 1];
        while (hasParent(index)) {
            int parentIndex = getParent(index);
            if (elements[parentIndex] < elements[index]) {
                swap(parentIndex, index);
                index = getParentIndex(index);
            }
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index) > getLeftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (elements[index] < elements[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    public int peek() {
        checkIfNotEmpty();
        return elements[0];
    }

    public int pool(){
        checkIfNotEmpty();
        int element = elements[0];
        elements[0] = elements[size-1];
        size--;
        heapifyDown();
        return element;
    }

    public void insert(int element){
        resizeIfFull();
        elements[size] = element;
        size++;
        heapifyUp();
    }
}
