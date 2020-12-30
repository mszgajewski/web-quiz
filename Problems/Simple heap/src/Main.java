import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static class MinHeap {
        private int[] heap;
        private int size;
        private int maxsize;

        public MinHeap(int maxsize) {
            this.maxsize = maxsize;
            this.size = 0;
            heap = new int[this.maxsize + 1];
            heap[0] = Integer.MIN_VALUE;

        }

        private void swap(int fpos, int spos) {
            int tmp;
            tmp = heap[fpos];
            heap[fpos] = heap[spos];
            heap[spos] = tmp;
        }

        private void minHeapify(int pos) {
            if (2 * pos == size) {
                if (heap[pos] > heap[2 * pos]) {
                    swap(pos, 2 * pos);
                    minHeapify(2 * pos);
                }
                return;
            }

            if (2 * pos <= size) {
                if (heap[pos] > heap[2 * pos] || heap[pos] > heap[2 * pos + 1]) {
                    if (heap[2 * pos] < heap[2 * pos + 1]) {
                        swap(pos, 2 * pos);
                        minHeapify(2 * pos);
                    }
                    else {
                        swap(pos, 2 * pos + 1);
                        minHeapify(2 * pos + 1);
                    }
                }
            }
        }

        public void insert(int element) {

            heap[++size] = element;
            int current = size;

            while (heap[current] < heap[current / 2]) {
                swap(current, current / 2);
                current = current / 2;
            }
        }

        public void minHeap() {
            for (int pos = (size / 2); pos >= 1; pos--) {
                minHeapify(pos);
            }
        }

        public int extractMin() {
            if (size == 0) {
                throw new NoSuchElementException("Heap is empty");
            }
            int popped = heap[1];
            heap[1] = heap[size--];
            minHeapify(1);
            return popped;
        }

    }
     static int height(int value)
    {
        return (int)Math.ceil(Math.log(value +
                1) / Math.log(2)) - 1;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        MinHeap minHeap = new MinHeap(n);

        String command = scanner.next();
int value = 0;
        for (int i = 0; i < n; i++) {
            switch (command) {
                case "+":
                     value = scanner.nextInt();
                    minHeap.insert(value);
                    break;
                case "-":
                    minHeap.extractMin();
                    break;
                case"?":
                    System.out.println(height(value));
                    break;
            }
        }
    }
}