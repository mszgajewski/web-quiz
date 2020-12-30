import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name());
        String s = sc.nextLine();

        int t = Integer.parseInt(sc.nextLine());

        int[][] pairs = new int[t][4];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 4; j++) {
                pairs[i][j] = sc.nextInt();
            }
        }

        System.out.println(proceed(s, t, pairs));
    }

    static int proceed(String s, int t, int[][] pairs) {
        int radix = radix();
        long prime = prime();
        int result = 0;
//        int naiveResult = 0;

        long[] prefixHashArray = new long[s.length()];
        long[] powerArray = new long[s.length()];
        long hash = 0;
        long power = 1;
        for (int i = 0; i < s.length(); i++) {
            hash += charToLong(s.charAt(i)) * power;
            hash %= prime;
            prefixHashArray[i] = hash;

            powerArray[i] = power;
            power = power * radix % prime;
        }

        for (int i = 0; i < t; i++) {
            int posI, posJ, posK, posM;
            if (pairs[i][0] > pairs[i][2]) {
                posI = pairs[i][2];
                posJ = pairs[i][3];
                posK = pairs[i][0];
                posM = pairs[i][1];
            } else {
                posI = pairs[i][0];
                posJ = pairs[i][1];
                posK = pairs[i][2];
                posM = pairs[i][3];
            }

//            if (s.substring(posI, posJ).equals(s.substring(posK, posM))) {
//                naiveResult++;
//            }

            long hashLeft = prefixHash(posI, posJ, prefixHashArray);
            long hashRight = prefixHash(posK, posM, prefixHashArray);

//            log(s, i, posI, posJ, posK, posM, hashLeft, hashRight, powerArray[posK - posI]);

            if (hashLeft * powerArray[posK - posI] % prime == hashRight) {
                result++;
            }
        }

//        System.out.println("result: " + result);
//        System.out.println("naiveResult: " + naiveResult);
        return result;
    }

    private static long hash(String s) {
        long hash = 0;
        long power = 1;
        long prime = prime();
        int radix = radix();
        for (int i = 0; i < s.length(); i++) {
            hash += charToLong(s.charAt(i)) * power;
            hash %= prime;
            power = power * radix % prime;
        }

        return hash;
    }

    private static long prefixHash(int left, int right, long[] prefixHashArray) {
        long prime = prime();
        if (left == 0) {
            return prefixHashArray[right - 1];
        }
        long leftHash = prefixHashArray[left - 1];
        long rightHash = prefixHashArray[right - 1];

        return (rightHash - leftHash + prime) % prime;
    }

    private static int radix() {
        return 53;
    }

    private static long prime() {
        return 1_000_000_000L + 9L;
    }

    private static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    private static void log(String s, int i, int posI, int posJ, int posK, int posM, long hashLeft, long hashRight, long power) {
        long prime = prime();
        long hashLeftNative = hash(s.substring(posI, posJ));
        long hashRightNative = hash(s.substring(posK, posM));
        System.out.println("===================");
        System.out.println("line: " + (i + 1));
        System.out.println("left: " + s.substring(posI, posJ));
        System.out.println("right: " + s.substring(posK, posM));
        System.out.println("posI: " + posI);
        System.out.println("posK: " + posK);
        System.out.println("posK - posI: " + (posK - posI));
        System.out.println("power: " + power);
        System.out.println("hashLeft: " + hashLeft);
        System.out.println("hashRight: " + hashRight);
        System.out.println("hashLeft * power: " + hashLeft * power);
        System.out.println("hashLeft * power % prime: " + hashLeft * power % prime);
        System.out.println("hashLeftNative: " + hashLeftNative);
        System.out.println("hashRightNative: " + hashRightNative);
        System.out.println("hashLeft % hashLeftNative: " + hashLeft % hashLeftNative);
        System.out.println("hashRight % hashRightNative: " + hashRight % hashRightNative);
        System.out.println("hashRight % hashLeft: " + hashRight % hashLeft);
        System.out.println("hashRightNative % hashLeftNative: " + hashRightNative % hashLeftNative);
    }
}
