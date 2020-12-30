import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);

        int numberOfCommands = in.nextInt();
        double scaleUpFactor = in.nextDouble();
        double scaleDownFactor = in.nextDouble();

        DynamicArray<Object> dynamicArray = new DynamicArray<>(scaleUpFactor, scaleDownFactor);


        for (int i = 0; i < numberOfCommands; i++) {
            String command = in.next();
            switch (command) {
                case "add":
                    dynamicArray.addCount(in.nextInt());
                    break;
                case "delete":
                    dynamicArray.deleteCount(in.nextInt());
                    break;
                case "count":
                    System.out.println(dynamicArray.count());
                    break;
//                case "add":
//                    dynamicArray.add(in.nextInt());
//                    break;
                case "size":
                    System.out.println(dynamicArray.getSize());
                    break;
                case "remove":
                    dynamicArray.remove(in.nextInt());
                    break;
                case "isempty":
                    System.out.println(dynamicArray.isEmpty());
                    break;
                case "contains":
                    System.out.println(dynamicArray.contains(in.nextInt()));
                    break;
                case "indexof":
                    System.out.println(dynamicArray.indexOf(in.nextInt()));
                    break;
                case "clear":
                    dynamicArray.clear();
                    break;
                default:
                    System.out.println("Error: Unknown command");
                    break;
            }
        }
    }

    public static class DynamicArray<E> {
        private Object[] arr;
        private int size;
        private double scaleUpFactor = 1.6;
        private double scaleDownFactor;
        private final int DEFAULT_SIZE = 2;

        public DynamicArray() {
            arr = new Object[DEFAULT_SIZE];
            size = 0;
        }

        public DynamicArray(double scaleUpFactor, double scaleDownFactor) {
            this();
            this.scaleUpFactor = scaleUpFactor;
            this.scaleDownFactor = scaleDownFactor;
        }

        public void tryIncrease() {
            if (size == arr.length) {
                arr = Arrays.copyOf(arr, (int) (arr.length * scaleUpFactor));
            }
        }

        public void add(E value) {
            tryIncrease();
            arr[size++] = value;
        }

        public E remove(int index) {
            if (index < 0 || index > size - 1) {
                throw new IndexOutOfBoundsException();
            }
            E elem = (E) arr[index];
            System.arraycopy(arr,index + 1, arr, index, size - index - 1);

            arr[--size] = null;
            return elem;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean contains(E value) {
            return Arrays.stream(arr)
                    .anyMatch(value::equals);
        }

        public int indexOf(E value) {
            return IntStream.range(0, size)
                    .filter(i -> arr[i].equals(value))
                    .findFirst()
                    .orElse(-1);
        }

        public void clear() {
            size = 0;
            arr = new Object[DEFAULT_SIZE];
        }

        public int getSize() {
            return size;
        }

        public void addCount(int count) {
            double newCapacity = arr.length;

            while (newCapacity <= count + size) {
                newCapacity = newCapacity * scaleUpFactor;
//                System.out.println("current capacity: " + newCapacity);
            }

            arr = Arrays.copyOf(arr, (int) Math.floor(newCapacity));
            size += count;
        }

        public void deleteCount(int count) {
            double newCapacity = arr.length;
            int requiredMemory = size - count;
            while (newCapacity / scaleDownFactor > requiredMemory) {
                newCapacity = newCapacity / scaleDownFactor;
            }
            arr = Arrays.copyOf(arr, (int) Math.floor(newCapacity));
            size -= count;
        }

        public int count() {
            return arr.length;
        }

        @Override
        public String toString() {
            return Arrays.toString(arr);
        }
    }
}
