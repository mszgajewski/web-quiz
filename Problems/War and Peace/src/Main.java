import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toLowerCase();
        String[] splitted = s.split("\\s+");
        Map<String, Integer> hm = new HashMap<String, Integer>();
        int x;

        for (int i = 0; i < splitted.length; i++) {

            if (hm.containsKey(splitted[i])) {
                x = hm.get(splitted[i]);
                hm.put(splitted[i], x + 1);
            } else {
                hm.put(splitted[i], 1);
            }
        }

        for (String key : hm.keySet()) {

            System.out.println(key + " " + hm.get(key));
        }
    }
}