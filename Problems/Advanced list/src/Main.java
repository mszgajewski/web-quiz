import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] command;
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        for (int i = 0; i < n; i++) {
            command = scanner.nextLine().split(" ");
            switch (command[0]) {
                case "add":
                    dynamicArray.add(Integer.parseInt(command[1]));
                    break;
                case "remove":
                    dynamicArray.remove(Integer.parseInt(command[1]));
                    break;
                case "size":
                    System.out.println(dynamicArray.getSize());
                    break;
                case "isempty":
                    System.out.println(dynamicArray.isEmpty());
                    break;
                case "contains":
                    System.out.println(dynamicArray.contains(Integer.parseInt(command[1])));
                    break;
                case "indexof":
                    System.out.println(dynamicArray.indexof(Integer.parseInt(command[1])));
                    break;
                case "clear":
                    dynamicArray.clear();
                    break;
                default:
                    break;
            }
        }
    }
}

class DynamicArray<E> {
    private Object[] arr;
    private int size;

    private final int DEFAULT_CAPACITY = 10;
    private final double SCALING_FACTOR = 1.5;

    public DynamicArray() {
        this.arr = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private void tryIncrease() {
        if (arr.length == size)
            arr = Arrays.copyOf(arr, (int) (arr.length * SCALING_FACTOR));
    }

    public void add(E value) {
        tryIncrease();
        arr[size++] = value;
    }


    public void remove(int idx) {
        System.arraycopy(arr, size, arr, size - 1, 0);
        arr[--size] = null;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E value) {
        for (int i = 0; i < arr.length; i++) {
            if (value.equals((E) arr[i])) {
                    return true;
                }
        }
        return false;
    }

    public int indexof(E value) {
        int a = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                a = i;
                break;
            } else  {
                a = -1;
            }
        }
        return a;
    }

    public void clear() {
        while (size != 0) {
            arr[size - 1] = 0;
            size--;
        }
    }
}