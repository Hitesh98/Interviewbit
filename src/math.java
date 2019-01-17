import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class math {

    public String[] fizzBuzz(int A) {
        String[] list = new String[A];
        for(int i = 1; i <= A; i++) {
            list[i-1] = returnString(i);
        }
        return list;
    }

    @NotNull
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

    private static int isPalindrome(String A) {
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


    // Replace array
    public int[] replaceArray(int[] A) {
        int n = A.length;
        A[0] = n * A[0] + A[A[0]];
        for (int i = 1; i < n; i++) {
            if (A[i] >= i) {
                A[i] = n * A[i] + A[A[i]];
            } else {
                A[i] = n * A[i] + A[A[i]] / n;
            }
        }
        for (int i = 0; i < n; i++) {
            A[i] = A[i] % n;
        }
        return A;
    }

    public void arrange(ArrayList<Integer> a) {
        int n = a.size();
        a.set(0, n * a.get(0) + a.get(a.get(0)));
        for (int i = 1; i < n; i++) {
            if (a.get(i) >= i) {
                a.set(i, n * a.get(i) + a.get(a.get(i)));
            } else {
                a.set(i, n * a.get(i) + a.get(a.get(i)) / n);
            }
        }
        for (int i = 0; i < n; i++) {
            a.set(i, a.get(i) % n);
        }
    }

    /*
        public String largestNumber(final int[] A) {
            int k = 0;
            int[] B = new int[A.length];
            for ( int i = 0; i < A.length; i++) {
                B[i] = A[i];
                if (A[i] != 0) {
                    k = 1;
                    break;
                }
            }
            if (k == 0) return "0";
            Collections.sort(B, new Comparator<String>(){

                // A comparison function which is used by
                // sort() in printLargest()
                @Override
                public int compare(String X, String Y) {

                    // first append Y at the end of X
                    String XY=X + Y;

                    // then append X at the end of Y
                    String YX=Y + X;

                    // Now see which of the two formed numbers
                    // is greater
                    return XY.compareTo(YX) > 0 ? -1:1;
                }
            });
            StringBuilder sb = new StringBuilder("");
            for (int i = A.length-1; i >= 0; i--) {
                sb.append(Integer.toString(A[i]));
            }
            return sb.toString();
        }

        public static void sortMyCompare(int[] A) {
            for (int i = A.length; i > 0; i--) {
                for (int j = 0; j < i - 1; j++) {
                    if (A[j] == compare(A[j], A[j+1])) {
                        swap(A, j, j+1);
                    }
                }
            }
        }

        public static void swap(int[] A, int i, int j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
        }

        public static int compare(int a, int b) {
            StringBuilder A = new StringBuilder(Integer.toString(a));
            StringBuilder D = new StringBuilder(Integer.toString(a));
            StringBuilder B = new StringBuilder(Integer.toString(b));
            long c = Long.parseLong(A.append(B).toString());
            long d = Long.parseLong(B.append(D).toString());
            if (c > d) {
                return a;
            } else {
                return b;
            }
        }

    */
    // First Missing Integer
    public int firstMissingPositive(int[] A) {
        int j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                j = 1;
                break;
            }
        }
        if (j == 0) return 1;
        else j = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                j++;
            }
        }
        int k = j;
        for (; j < A.length; j++) {
            int x = Math.abs(A[j]);
            if (x + k < A.length)
                A[x + k] = -1 * Math.abs(A[x + k]);
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                if (i != k) {
                    return i - k;
                }
            }
        }
        return A.length + 1 - k;
    }

    public int sqrt(int a) {

        int start = 0, end = a / 2, ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid * mid == a) return mid;
            if (mid * mid < a) {
                start = mid + 1;
                ans = mid;
            } else end = mid - 1;
        }
        return ans;
    }

    public static int floorSqrt(int x) {
        // Base Cases
        if (x == 0 || x == 1)
            return x;

        // Do Binary Search for floor(sqrt(x))
        int start = 1, end = x, ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            // If x is a perfect square
            if (mid * mid == x)
                return mid;

            // Since we need floor, we update answer when mid*mid is
            // smaller than x, and move closer to sqrt(x)
            if (mid * mid < x) {
                start = mid + 1;
                ans = mid;
            } else   // If mid*mid is greater than x
                end = mid - 1;
        }
        return ans;
    }

    public int lengthOfLastWord(final String A) {
        int j = A.length();
        while (A.charAt(j - 1) == ' ') {
            j--;
        }
        if (j == -1) return 0;
        int count = 0;
        for (int i = 0; i < j; i++) {
            if (A.charAt(i) == ' ') {
                count = 0;
            } else count++;
        }
        return count;
    }

    public int solve(String A) {

        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            if (isSpecial(A.charAt(i))) {
                count += (A.length() - i) % 10003;
            }
        }
        return count;
    }

    public static boolean isSpecial(char a) {
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u' ||
                a == 'A' || a == 'E' || a == 'I' || a == 'O' || a == 'U') {
            return true;
        }
        return false;
    }

    public int[] subUnsort(int[] A) {
        int[] B = Arrays.copyOf(A, A.length);
        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            B[i] = A[i] - B[i];
        }
        int i = -1, j = A.length;
        while (i + 1 < A.length && B[i + 1] == 0) {
            i++;
        }
        while (j - 1 >= 0 && B[j - 1] == 0) {
            j--;
        }
        if (i + 1 == A.length) {
            int[] returned = new int[1];
            returned[0] = -1;
            return returned;
        } else {
            int[] returned = new int[2];
            returned[0] = i + 1;
            returned[1] = j - 1;
            return returned;
        }
    }

    public int consecutivediff(final int[] A) {
        int[] B = Arrays.copyOf(A, A.length);
        Arrays.sort(B);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            max = Math.max(max, B[i + 1] - B[i]);
        }
        return max;
    }


    // prime upto N
    public ArrayList<Integer> sieve(int A) {
        ArrayList<Integer> E = new ArrayList<>();
        int[] a = new int[A + 1];
        for (int i = 2; i < A + 1; i += 2) {
            a[i] = 1;
        }
        E.add(2);
        for (int i = 3; i <= Math.sqrt(A); i += 2) {
            if (a[i] == 0) {
                int j = i * 2;
                while (j < A + 1) {
                    if (a[j] == 0) {
                        a[j] = 1;
                    }
                    j += i;
                }
            }
        }
        for (int i = 3; i <= A; i++) {
            if (a[i] == 0) E.add(i);
        }
        return E;
    }

    //all factors
    public ArrayList<Integer> allFactors(int A) {
        if (A == 0 || A == 1) {
            ArrayList<Integer> E = new ArrayList<>();
            E.add(0);
            return E;
        }
        ArrayList<Integer> E = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(A); i++) {
            if (A % i == 0) {
                E.add(i);
            }
        }
        int x = E.size();
        if (E.get(x - 1) == Math.sqrt(A)) {
            x = x - 1;
        }
        for (int i = x - 1; i >= 0; i--) {

            E.add(A / E.get(i));
        }
        return E;
    }


    // prime sum
    public int[] primesum(int A) {
        int[] solution = new int[2];
        if (A == 4) {
            solution[0] = solution [1] = 2;
            return solution;
        } else {
            int[] lit = isPrimeList(A);
            int current = 3;
            while (current <= A / 2) {
                if (lit[A - current] == 0) {
                    solution[0] = Math.min(current, A - current);
                    solution[1] = Math.max(current, A - current);
                    return solution;
                }
                current++;
                while (current <= A / 2 && lit[current] == 1) current++;
            }
            return solution;
        }
    }

    public static int[] isPrimeList(int A) {
        int[] lit = new int[A - 1];
        lit[0] = lit[1] = 1;
        for (int n = 4; n < A - 2; ) {
            lit[n] = 1;
            n += 2;
        }
        for (int n = 3; n < Math.sqrt(A - 2); n += 2) {
            if (lit[n] != 1) {
                int j = n * 2;
                while (j < A - 2) {
                    if (lit[j] == 0) {
                        lit[j] = 1;
                    }
                    j += n;
                }
            }
        }
        return lit;
    }

    public static void main(String[] args) {
        math m = new math();
        int[] A = {417, 929, 845, 462, 675, 175, 73, 867, 14, 201, 777, 407, 80, 882, 785, 563, 209, 261, 776, 362, 730, 74, 649, 465, 353, 801, 503, 154, 998, 286, 520, 692, 68, 805, 835, 210, 819, 341, 564, 215, 984, 643, 381, 793, 726, 213, 866, 706, 97, 538, 308, 797, 883, 59, 328, 743, 694, 607, 729, 821, 32, 672, 130, 13, 76, 724, 384, 444, 884, 192, 917, 75, 551, 96, 418, 840, 235, 433, 290, 954, 549, 950, 21, 711, 781, 132, 296, 44, 439, 164, 401, 505, 923, 136, 317, 548, 787, 224, 23, 185, 6, 350, 822, 457, 489, 133, 31, 830, 386, 671, 999, 255, 222, 944, 952, 637, 523, 494, 916, 95, 734, 908, 90, 541, 470, 941, 876, 264, 880, 761, 535, 738, 128, 772, 39, 553, 656, 603, 868, 292, 117, 966, 259, 619, 836, 818, 493, 592, 380, 500, 599, 839, 268, 67, 591, 126, 773, 635, 800, 842, 536, 668, 896, 260, 664, 506, 280, 435, 618, 398, 533, 647, 373, 713, 745, 478, 129, 844, 640, 886, 972, 62, 636, 79, 600, 263, 52, 719, 665, 376, 351, 623, 276, 66, 316, 813, 663, 831, 160, 237, 567, 928, 543, 508, 638, 487, 234, 997, 307, 480, 620, 890, 216, 147, 271, 989, 872, 994, 488, 291, 331, 8, 769, 481, 924, 166, 89, 824, -4, 590, 416, 17, 814, 728, 18, 673, 662, 410, 727, 667, 631, 660, 625, 683, 33, 436, 930, 91, 141, 948, 138, 113, 253, 56, 432, 744, 302, 211, 262, 968, 945, 396, 240, 594, 684, 958, 343, 879, 155, 395, 288, 550, 482, 557, 826, 598, 795, 914, 892, 690, 964, 981, 150, 179, 515, 205, 265, 823, 799, 190, 236, 24, 498, 229, 420, 753, 936, 191, 366, 935, 434, 311, 920, 167, 817, 220, 219, 741, -2, 674, 330, 909, 162, 443, 412, 974, 294, 864, 971, 760, 225, 681, 689, 608, 931, 427, 687, 466, 894, 303, 390, 242, 339, 252, 20, 218, 499, 232, 184, 490, 4, 957, 597, 477, 354, 677, 691, 25, 580, 897, 542, 186, 359, 346, 409, 655, 979, 853, 411, 344, 358, 559, 765, 383, 484, 181, 82, 514, 582, 593, 77, 228, 921, 348, 453, 274, 449, 106, 657, 783, 782, 811, 333, 305, 784, 581, 746, 858, 249, 479, 652, 270, 429, 614, 903, 102, 378, 575, 119, 196, 12, 990, 356, 277, 169, 70, 518, 282, 676, 137, 622, 616, 357, 913, 161, 3, 589, 327};
        int[] B = {1, 2, -3, 3, 4, 6, 12, -5, -11, -12324, 11, 10, 5, 8, 9, 7};
        System.out.println(m.firstMissingPositive(B));
    }
}




