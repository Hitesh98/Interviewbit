import java.util.*;

public class arrays {

    // minimum distance in infinite grid
    private static int coverPoints(final int[] A, final int[] B) {

        int n = A.length, steps = 0;
        for (int i = 0; i < n - 1; i++) {
            steps += max(getDist(A[i + 1], A[i]), getDist(B[i + 1], B[i]));
        }
        return steps;
    }

    private static int getDist(final int x, final int x2) {
        return (x > x2) ? (x - x2) : (x2 - x);
    }

    private static int max(final int a, final int b) {
        return (a > b) ? a : b;
    }

    // add one to number
    public static int[] plusOne(final int[] A) {
        int n = A.length, start = 0;
        try {
            while (A[start] == 0) {
                start++;
            }
        } catch (IndexOutOfBoundsException e) {
            int[] AplusOne = {1};
            return AplusOne;
        }
        int[] AplusOne = (isAllNines(A, start)) ? (new int[n - start + 1]) : (new int[n - start]);
        int i = n - 1, j = AplusOne.length - 1;
        try {
            while (A[i] == 9) {
                AplusOne[j] = 0;
                i--;
                j--;
            }
        } catch (IndexOutOfBoundsException e) {
            AplusOne[j] = 1;
            return AplusOne;
        }
        AplusOne[j--] = A[i--] + 1;
        for (; j >= 0; j--, i--) {
            AplusOne[j] = A[i];
        }
        return AplusOne;
    }

    private static boolean isAllNines(int A[], int start) {
        int n = A.length;
        for (int i = start; i < n; i++) {
            if (A[i] != 9) {
                return false;
            }
        }
        return true;
    }

    // maximum sum contiguous subarray
    private static int maxSubArray(final int[] A) {
        int n = A.length, max = A[0], max_ending = A[0];
        for (int i = 1; i < n; i++) {
            max_ending = Math.max(A[i], max_ending + A[i]);
            max = Math.max(max, max_ending);
        }
        return max;
    }

    // max array
    private static int maxArr(int[] A) {
        int max1 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, max2 = Integer.MIN_VALUE, min2 = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            max1 = Math.max(max1, A[i] + i);
            min1 = Math.min(min1, A[i] + i);
            max2 = Math.max(max2, A[i] - i);
            min2 = Math.min(min2, A[i] - i);
        }
        return Math.max(max1 - min1, max2 - min2);
    }


    // MAXSPROD
    private static int maxSpecialProduct(int[] A) {
        int maxProduct = 0;

        for (int i = 0; i < A.length; i++) {
            int j = i;
            while (j > 0 && A[j] <= A[i]) j--;
            int k = i;
            while (k < A.length - 1 && A[k] <= A[i]) k++;
            if (k == A.length - 1 && A[k] <= A[i]) k = 0;
            maxProduct = Math.max((k % 1000000007) * (j % 1000000007), maxProduct);
        }
        return maxProduct;
    }

    // Repeat and missing number
    private static int[] repeatedNumber(final int[] A) {
        long sum = 0, sum2 = 0;
        double product = 1;
        for (int i = 0; i < A.length; i++) {
            sum += (A[i] - (long) i - 1);
            sum2 += (A[i] * A[i] - (((long) i + 1) * ((long) i + 1)));
        }
        sum2 = sum2 / sum;
        int[] x = {(int) (sum + sum2) / 2, (int) (sum2 - sum) / 2};
        return x;
    }


    //rotate matrix
    public void rotate(ArrayList<ArrayList<Integer>> a) {
        int N = a.get(0).size();
        this.matrixTranspose(a);
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                this.swapInMatrix(a, j, i, j, N - 1 - i);
            }
        }
    }

    //method to swap in matrix
    private void swapInMatrix(ArrayList<ArrayList<Integer>> matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix.get(i1).get(j1);
        matrix.get(i1).set(j1, matrix.get(i2).get(j2));
        matrix.get(i2).set(j2, temp);
    }


    // method to find transpose of a given matrix
    private void matrixTranspose(ArrayList<ArrayList<Integer>> matrix) {
        int n = matrix.get(0).size();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                this.swapInMatrix(matrix, i, j, j, i);
            }
        }
    }


    // noble Integer
    public int solve(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (A.length - 1 - i == A[i]) {
                if (A[i] != A[i + 1])
                    return 1;
            }
        }
        return -1;
    }


    // Repeat Number
    public int repeatedNumber(final List<Integer> a) {
        int len = a.size();
        ArrayList<Integer> E = new ArrayList<>(a);
        for (int i = 0; i < len; i++) {
            int temp = Math.abs(E.get(i));
            E.add(temp);
            int old = E.get(temp - 1);
            if (old > 0) {
                E.set(temp - 1, -1 * (old));
            } else {
                return temp;
            }
        }
        return -1;
    }

    //public int retFindDup2(int x, int l) {
    //
    //}

    public ArrayList<Integer> findDuplicates(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int temp = retFindDup(A[i], A.length);
            if (A[temp] >= 0) {
                if (A[temp] < A.length) {
                    A[temp] = A[temp] * -1 - 1;
                }
            } else {
                A[temp] = Math.abs(A[temp] + 1) + A.length;
            }
        }


        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= A.length) {
                a.add(i);
            }
        }
        return a;
    }

    /**
     * Longest non-negetive subarray
     */
    public int[] maxset(final int[] A) {
        if (isAllNeg(A)) {
            return new int[0];
        }
        long max = Integer.MIN_VALUE, start = 0, iter = 0, maxStart = Integer.MAX_VALUE, maxEnd = 0;
        for (; start < A.length; ) {
            long curr = 0;
            if (A[(int) start] >= 0) {
                while (iter < A.length && A[(int) iter] >= 0) {
                    curr += A[(int) iter];
                    iter++;
                }
                if (curr >= max) {
                    if (curr == max) {
                        if ((maxEnd - maxStart < iter - start) || (maxEnd - maxStart == iter - start && maxStart > start)) {
                            maxStart = start;
                            maxEnd = iter;
                        }
                    } else {
                        maxStart = start;
                        maxEnd = iter;
                        max = curr;
                    }
                }
                if (iter == A.length) {
                    break;
                } else if (A[(int) iter] < 0) {
                    start = iter;
                }
            } else {
                start++;
                iter = start;
            }
        }
        int[] arr = new int[(int) (maxEnd - maxStart)];
        arr = Arrays.copyOfRange(A, (int) maxStart, (int) maxEnd);
        return arr;
    }


    /**
     * wave Array
     */
    public int[] wave(int[] A) {
        if (A.length <= 1) {
            return A;
        }
        for (int i = 1; i < A.length; i += 2) {
            if (A[i] > A[i - 1]) {
                swap(A, i, i - 1);
            }
            if (i + 1 < A.length && A[i] > A[i + 1]) {
                swap(A, i, i + 1);
            }
        }
        return A;
    }

    private void swap(int[] A, int first, int last) {
        int temp = A[first];
        A[first] = A[last];
        A[last] = temp;
    }

    private boolean isAllNeg(int[] A) {
        for (int x : A) {
            if (x >= 0) {
                return false;
            }
        }
        return true;
    }

    public static int retFindDup(int x, int l) {
        if (x >= l) {
            return x - l;
        } else if (x < 0) {
            return -1 * (x + 1);
        } else {
            return x;
        }
    }


    // Pascal's Triangle
    public int[][] solve(int A) {
        int[][] arr = new int[A][];
        for (int i = 0; i < A; i++) {
            int[] temp = new int[i + 1];
            temp[0] = 1;
            for (int j = 1; j <= i; j++) {
                temp[j] = temp[j - 1] * (i - j + 1) / (j);
            }
            arr[i] = temp;
        }
        return arr;
    }


    // kth row of pascal's triangle
    public int[] getRow(int A) {
        int[] temp = new int[A + 1];
        temp[0] = 1;
        for (int j = 1; j <= A; j++) {
            temp[j] = temp[j - 1] * (A - j + 1) / (j);
        }
        return temp;

    }

    // Max j-i
    public int maximumGap(final int[] A) {
        int[] B = new int[A.length];
        int maxSoFar = Integer.MIN_VALUE;
        for (int i = B.length - 1; i >= 0; i--) {
            B[i] = Math.max(A[i], maxSoFar);
            if (B[i] != maxSoFar) maxSoFar = B[i];
        }
        int minSoFar = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.min(A[i], minSoFar);
            if (A[i] != minSoFar) minSoFar = A[i];
        }
        int i = 0, j = 0, max = Integer.MIN_VALUE;
        for (; j < A.length; j++) {
            while (A[i] > B[j]) {
                i++;
                continue;
            }
            max = Math.max(j - i, max);

        }
        return max;
    }

    // merge intervals
    public ArrayList<Interval> mergeIntervals(ArrayList<Interval> arr, Interval a) {
        arr.add(a);
        arr = merge(arr);
        return arr;
    }

    //N/3 repeat array
    public int repeatedNumber2(final List<Integer> a) {
        int count1 = 0, count2 = 0;

        // take the integers as the maximum
        // value of integer hoping the integer
        // would not be present in the array
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int i = 1; i < a.size(); i++) {

            // if this element is previously
            // seen, increment count1.
            if (first == a.get(i))
                count1++;

                // if this element is previously
                // seen, increment count2.
            else if (second == a.get(i))
                count2++;

            else if (count1 == 0) {
                count1++;
                first = a.get(i);
            } else if (count2 == 0) {
                count2++;
                second = a.get(i);
            }

            // if current element is different
            // from both the previously seen
            // variables, decrement both the
            // counts.
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        // Again traverse the array and
        // find the actual counts.
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == first)
                count1++;

            else if (a.get(i) == second)
                count2++;
        }

        if (count1 > a.size() / 3)
            return first;

        if (count2 > a.size() / 3)
            return second;

        return -1;
    }


    // Flip
    public int[] flip(String A) {
        int max = returnVal(A.charAt(0)), max_ending = returnVal(A.charAt(0)), start = 0, end = 0, n = A.length(), beg = 0;
        for (int i = 0; i < n; i++) {
            max_ending = Math.max(returnVal(A.charAt(i)), max_ending + returnVal(A.charAt(i)));
            if (returnVal(A.charAt(i)) > max_ending) {
                beg = i + 1;
            }
            if (max < max_ending) {
                start = beg;
                end = i;
            }
            max = Math.max(max, max_ending);
        }
        System.out.println(max + " " + start + " " + end);
        int[] a = new int[1];
        return a;
    }

    private static int returnVal(char c) {
        if (c == '0') {
            return 1;
        } else {
            return -1;
        }
    }


    private static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals.size() <= 0)
            return intervals;

        // Create an empty stack of intervals
        Stack<Interval> stack = new Stack<>();

        ArrayList<Interval> toReturn = new ArrayList<>();
        // sort the intervals in increasing order of start time
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        // push the first interval to stack
        stack.push(intervals.get(0));

        // Start from the next interval and merge if necessary
        for (int i = 1; i < intervals.size(); i++) {
            // get interval from stack top
            Interval top = stack.peek();

            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.end < intervals.get(i).start) {
                toReturn.add(stack.pop());
                stack.push(intervals.get(i));

                // Otherwise update the ending time of top if ending of current
                // interval is more
            } else if (top.end < intervals.get(i).end) {
                top.end = intervals.get(i).end;
                stack.pop();
                stack.push(top);
            }
        }
        while (!stack.isEmpty()) {
            toReturn.add(stack.pop());
        }
        return toReturn;
    }


    public static void main(String[] args) {
        arrays m = new arrays();
        ArrayList<Interval> a = new ArrayList<>();
        a.add(new Interval(1, 5));
        a.add(new Interval(6, 9));
        a.add(new Interval(2, 4));
        a.add(new Interval(10, 12));
        a = m.merge(a);
        for (Interval x : a) {
            System.out.println(x.start + " " + x.end);
        }
    }
}
