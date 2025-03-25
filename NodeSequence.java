import java.util.Random;

// PositionList interface to define positional list methods
interface PositionList<E> {
    void addLast(E element);
    E get(int index);
    int size();
    boolean isEmpty();
}

// Node class to represent each node in the sequence
class Node<E> {
    E element;
    Node<E> next;
    Node<E> prev;

    Node(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}

// NodePositionList class implementing the PositionList interface
class NodePositionList<E> implements PositionList<E> {
    protected Node<E> head;
    protected Node<E> tail;
    protected int size;

    public NodePositionList() {
        head = tail = null;
        size = 0;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

// Sequence interface to define additional sequence operations
interface Sequence<E> {
    void bubbleSort(); // Bubble Sort (O(n^2))
    void mergeSort();  // Merge Sort (O(n log n))
    void print();      // Print the sequence
}

// NodeSequence class implementing PositionList and Sequence interfaces
public class NodeSequence<E extends Comparable<E>> extends NodePositionList<E> implements Sequence<E> {

    // Bubble sort implementation (O(n^2))
    @Override
    public void bubbleSort() {
        int n = size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (get(j).compareTo(get(j + 1)) > 0) {
                    // Swap elements
                    E temp = get(j);
                    set(j, get(j + 1));
                    set(j + 1, temp);
                }
            }
        }
    }

    // Merge Sort implementation (O(n log n))
    @Override
    public void mergeSort() {
        mergeSortRec(0, size() - 1);
    }

    private void mergeSortRec(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRec(left, mid);
            mergeSortRec(mid + 1, right);
            merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        E[] leftArray = (E[]) new Comparable[n1];
        E[] rightArray = (E[]) new Comparable[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) leftArray[i] = get(left + i);
        for (int i = 0; i < n2; i++) rightArray[i] = get(mid + 1 + i);

        // Merge the temp arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                set(k, leftArray[i]);
                i++;
            } else {
                set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        // Copy remaining elements if any
        while (i < n1) {
            set(k, leftArray[i]);
            i++;
            k++;
        }
        while (j < n2) {
            set(k, rightArray[j]);
            j++;
            k++;
        }
    }

    // Set an element at a specific index
    private void set(int index, E element) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = element;
    }

    // Print the sequence
    @Override
    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NodeSequence<Integer> sequence = new NodeSequence<>();
        Random random = new Random();
        int size = 200;

        // Generate a large sequence of random integers
        for (int i = 0; i < size; i++) {
            sequence.addLast(random.nextInt(10000));
        }

        // Clone the sequence for merge sort
        NodeSequence<Integer> sequenceForMergeSort = new NodeSequence<>();
        for (int i = 0; i < size; i++) {
            sequenceForMergeSort.addLast(sequence.get(i));
        }

        // Bubble sort timing
        long startTime = System.currentTimeMillis();
        sequence.bubbleSort();
        long endTime = System.currentTimeMillis();
        System.out.println("Bubble sort time: " + (endTime - startTime) + " ms");

        // Merge sort timing
        startTime = System.currentTimeMillis();
        sequenceForMergeSort.mergeSort();
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort time: " + (endTime - startTime) + " ms");

        // Print the sorted sequence
        // sequence.print(); // Uncomment to print the sorted list (it will be huge)
    }
}
