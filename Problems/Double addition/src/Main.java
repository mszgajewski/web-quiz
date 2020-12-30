import java.util.Scanner;

public class Main {

    static class DoublyLinkedList {
        private Node head;
        private Node tail;
        private int size;

        DoublyLinkedList() {
            size = 0;
        }

        void addFirst(Integer elem) {
            Node tmp = new Node(elem, head, null);

            if (head != null) {
                head.prev = tmp;
            }

            head = tmp;

            if (tail == null) {
                tail = tmp;
            }
            size++;
        }

        void addLast(Integer elem) {
            Node tmp = new Node(elem, null, tail);

            if (tail != null) {
                tail.next = tmp;
            }

            tail = tmp;

            if (head == null) {
                head = tmp;
            }
            size++;
        }

        void add(Integer elem) {
            int first = Math.abs(elem - head.value);
            int last = Math.abs(elem - tail.value);

            if (first < last) {
                addFirst(elem);
            } else {
                addLast(elem);
            }
        }

        @Override
        public String toString() {
            Node iterator = head;
            StringBuilder result = new StringBuilder();

            while (iterator != null) {
                result.append(iterator.value).append(" ");
                iterator = iterator.next;
            }

            return result.toString();
        }

        static class Node {
            private int value;
            private Node next;
            private Node prev;

            Node(int element, Node next, Node prev) {
                this.value = element;
                this.next = next;
                this.prev = prev;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DoublyLinkedList list = new DoublyLinkedList();
        for (int i = 0; i < 2 && i < n; i++) {
            list.addLast(sc.nextInt());
        }

        for (int i = 2; i < n; i++) {
            list.add(sc.nextInt());
        }

        System.out.println(list);
    }
}