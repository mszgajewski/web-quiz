import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String sentence = scanner.nextLine().toLowerCase();

        System.out.print(sentence.indexOf("the"));
    }
}

