import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] array = new int[n * m];
        int count = 0;
        int biggest = 0;
        int swap = 0;

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        for (int p = 0; p < n; p++) {
            for (int i = p * m; i < m - 1 + p * m; i++) {
                int min = i;
                for (int j = i + 1; j < m + p * m; j++) {
                    if (array[j] < array[min]) {
                        min = j;
                        swap++;
                    }
                }

                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;

            }

            if (swap > count) {
                biggest = p;
                count = swap;
            }

            swap = 0;
        }

        System.out.println(biggest + 1);
    }
}

