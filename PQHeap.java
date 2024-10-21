/**
* Author: Timothy Pratt
* Date: 11/15/2022
* File: PQHeap.java
* Section A
* Project 8
* CS231
*/

import java.util.Comparator;

public class PQHeap<T>
{
    private T[] heap;
    private int size;
    private Comparator<T> comparator;

    //constructor
    public PQHeap(Comparator<T> comparator)
    {
        size = 0;
        this.comparator = comparator;
        heap = (T[]) new Object[16];
    }

    //returns number of elements in heap
    public int size()
    {
        return size;
    }

    //returns number of items heap can currently hold
    public int capacity()
    {
        return heap.length;
    }

    //returns index of parent
    private int parent(int index)
    {
        return index / 2;
    }

    //returns index of left child
    private int left(int index)
    {
        return index * 2;
    }

    //returns index of right child
    private int right(int index)
    {
        return index * 2 + 1;
    }

    //recreates array with given newSize
    private void resize(int newSize)
    {
        T[] newHeap = (T[]) new Object[newSize];

        for (int i = 1; i <= size; i++)
        {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    //adds value to heap 
    public void offer(T item)
    {
        if (size + 1 == heap.length)
        {
            resize(2 * heap.length);
        }

        heap[size + 1] = item;
        size++;
        bubbleUp(size);
    }

    //moves object "up" through tree if necessary
    private void bubbleUp(int index)
    {
        if (index == 1)
        {
            return;
        }

        T myself = heap[index];
        T myParent = heap[parent(index)];

        if (comparator.compare(myself, myParent) > 0)
        {
            swap(index, parent(index));
            bubbleUp(parent(index));
        }
    }

    //swaps index of two opjects
    private void swap(int index1, int index2)
    {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    //returns highest priority element of heap
    public T peek()
    {
        return heap[1];
    }

    //removes and returns highest priority element from heap
    public T poll()
    {
        T toReturn = heap[1];

        if (size - 1 < heap.length / 4)
        {
            resize((int) heap.length / 2);
        }

        swap(1, size);
        heap[size] = null;
        size--;
        bubbleDown(1);
        return toReturn;
    }

    //moves object "down" through tree if necessary
    private void bubbleDown(int index)
    {
        if (left(index) >= size || heap[left(index)] == null)
        {
            return;
        }

        T myself = heap[index];
        T left = heap[left(index)];
        T right = heap[right(index)];

        if (comparator.compare(left, right) > 0)
        {
            if (comparator.compare(left, myself) > 0)
            {
                swap(index, left(index));
                bubbleDown(left(index));
            }
        }
        else
        {
            if (comparator.compare(right, myself) > 0)
            {
                swap(index, right(index));
                bubbleDown(right(index));
            }
        }
    }

    public static void main(String[] args)
    {
        PQHeap<Integer> heap = new PQHeap<Integer>(new Comparator<Integer>()
        {
            public int compare(Integer o1, Integer o2)
            {
                return o1 - o2;
            }
        });
    }
}
