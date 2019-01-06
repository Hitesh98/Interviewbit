
import java.lang.Character;

public class math {

    public String[] fizzBuzz(int A) {
        String[] list = new String[A];
        for(int i = 1; i <= A;i++) {
            list[i-1] = returnString(i);
        }
        return list;
    }

    public static String returnString(int i) {
        if (i%3 == 0) {
            if (i % 5 == 0) {
                return "FizzBuzz";
            } else {
                return "Fizz";
            }
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(i);
        }
    }

    public int isPalindrome(String A) {
        A = A.toLowerCase();
        int start = 0, end = A.length()-1;
        for (int i = 0; start < end; start++, end--) {
            while (start < end && !Character.isLetter(A.charAt(start)) && !Character.isDigit(A.charAt(start))) start++;
            while (start < end && !Character.isLetter(A.charAt(end)) && !Character.isDigit(A.charAt(end))) end--;
            if (A.charAt(start) != A.charAt(end)){
                return 0;
            }
        }
        return 1;

    }

    public int[] primesum(int A) {
        int[] solution = new int[2];
        if (A == 4) {
            solution[0] = solution [1] = 2;
            return solution;
        } else {
            int[] lit = isPrimeList(A);
            int current = 3;
            while (current < A - 2) {
                if (lit[A-current] == 1) {
                    solution[0] = Math.min(current, A - current);
                    solution[1] = Math.max(current, A - current);
                    return solution;
                }else {
                    lit[current] = lit[A-current] = 2;
                    while (lit[current] != 1 && current < A-2) current++;
                }

            }
            return solution;
        }
    }

    public static int[] isPrimeList(int A) {
        int[] lit = new int[A + 1];
        lit[0] = lit[1] = -1;
        int index = 2;
        while(index <= A) {
            lit[index] = 1;
            int temp = 2*index;
            while (temp < A) {
                lit[temp] = -1;
                temp += index;
            }
            while (lit[index] != 0 && index%2 == 0) {
                index++;
            }
        }
        return lit;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        math m = new math();
        int[] solution = m.primesum(1286);
        for(int a : solution) {
            System.out.println(a);
        }
    }
}
