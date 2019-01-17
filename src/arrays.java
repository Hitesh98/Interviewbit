import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class arrays {

    // minimum distance in infinite grid
    private static int coverPoints(final int[] A,final int[] B) {

        int n = A.length, steps = 0;
        for (int i = 0; i < n - 1; i++) {
            steps += max(getDist(A[i + 1], A[i]), getDist(B[i + 1],B[i]));
        }
        return steps;
    }

    private static int getDist(final int x,final int x2) {
        return (x > x2) ? (x - x2) : (x2 - x);
    }

    private static int max(final int a,final int b) {
        return (a>b)? a:b;
    }

    // add one to number
    public static int[] plusOne(final int[] A) {
        int n = A.length, start = 0;
        try {
            while (A[start] == 0) {
                start++;
            }
        }catch(IndexOutOfBoundsException e)
        {
            int[] AplusOne = {1};
            return AplusOne;
        }
        int[] AplusOne = (isAllNines(A, start))? (new int[n-start+1]):(new int[n-start]);
        int i = n - 1, j = AplusOne.length - 1;
        try {
            while (A[i] == 9) {
                AplusOne[j] = 0;
                i--;
                j--;
            }
        }catch (IndexOutOfBoundsException e) {
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
        for (int i = 1; i< n; i++) {
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
        int  maxProduct = 0;

        for (int i = 0; i < A.length; i++) {
            int j = i;
            while (j > 0 && A[j] <= A[i]) j--;
            int k = i;
            while (k < A.length-1 && A[k] <= A[i]) k++;
            if (k == A.length-1 && A[k] <= A[i]) k = 0;
            maxProduct = Math.max((k% 1000000007)*(j % 1000000007), maxProduct);
        }
        return maxProduct;
    }

    // Repeat and missing number
    private static int[] repeatedNumber(final int[] A) {
        long sum = 0, sum2 = 0;
        double product = 1;
        for (int i = 0; i < A.length; i++) {
            sum += (A[i] - (long) i - 1);
            sum2 += (A[i]*A[i] - (((long) i + 1)*((long) i + 1)));
        }
        sum2 = sum2 / sum;
        int[] x = {(int) (sum + sum2)/2, (int) (sum2 - sum)/2};
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
        for (int i = 0; i< n; i++) {
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


    public static void main(String[] args) {
        arrays m = new arrays();
        int[] ass = {417, 929, 845, 462, 675, 175, 73, 867, 14, 201, 777, 407, 80, 882, 785, 563, 209, 261, 776, 362, 730, 74, 649, 465, 353, 801, 503, 154, 998, 286, 520, 692, 68, 805, 835, 210, 819, 341, 564, 215, 984, 643, 381, 793, 726, 213, 866, 706, 97, 538, 308, 797, 883, 59, 328, 743, 694, 607, 729, 821, 32, 672, 130, 13, 76, 724, 384, 444, 884, 192, 917, 75, 551, 96, 418, 840, 235, 433, 290, 954, 549, 950, 21, 711, 781, 132, 296, 44, 439, 164, 401, 505, 923, 136, 317, 548, 787, 224, 23, 185, 6, 350, 822, 457, 489, 133, 31, 830, 386, 671, 999, 255, 222, 944, 952, 637, 523, 494, 916, 95, 734, 908, 90, 541, 470, 941, 876, 264, 880, 761, 535, 738, 128, 772, 39, 553, 656, 603, 868, 292, 117, 966, 259, 619, 836, 818, 493, 592, 380, 500, 599, 839, 268, 67, 591, 126, 773, 635, 800, 842, 536, 668, 896, 260, 664, 506, 280, 435, 618, 398, 533, 647, 373, 713, 745, 478, 129, 844, 640, 886, 972, 62, 636, 79, 600, 263, 52, 719, 665, 376, 351, 623, 276, 66, 316, 813, 663, 831, 160, 237, 567, 928, 543, 508, 638, 487, 234, 997, 307, 480, 620, 890, 216, 147, 271, 989, 872, 994, 488, 291, 331, 8, 769, 481, 924, 166, 89, 824, -4, 590, 416, 17, 814, 728, 18, 673, 662, 410, 727, 667, 631, 660, 625, 683, 33, 436, 930, 91, 141, 948, 138, 113, 253, 56, 432, 744, 302, 211, 262, 968, 945, 396, 240, 594, 684, 958, 343, 879, 155, 395, 288, 550, 482, 557, 826, 598, 795, 914, 892, 690, 964, 981, 150, 179, 515, 205, 265, 823, 799, 190, 236, 24, 498, 229, 420, 753, 936, 191, 366, 935, 434, 311, 920, 167, 817, 220, 219, 741, -2, 674, 330, 909, 162, 443, 412, 974, 294, 864, 971, 760, 225, 681, 689, 608, 931, 427, 687, 466, 894, 303, 390, 242, 339, 252, 20, 218, 499, 232, 184, 490, 4, 957, 597, 477, 354, 677, 691, 25, 580, 897, 542, 186, 359, 346, 409, 655, 979, 853, 411, 344, 358, 559, 765, 383, 484, 181, 82, 514, 582, 593, 77, 228, 921, 348, 453, 274, 449, 106, 657, 783, 782, 811, 333, 305, 784, 581, 746, 858, 249, 479, 652, 270, 429, 614, 903, 102, 378, 575, 119, 196, 12, 990, 356, 277, 169, 70, 518, 282, 676, 137, 622, 616, 357, 913, 161, 3, 589, 327};
        int[] f = {759, 752, 892, 304, 10, 305, 106, 557, 205, 292, 362, 28, 756, 754, 872, 778, 178, 291, 198, 331, 191, 616, 47, 625, 629, 853, 503, 425, 78, 408, 9, 39, 394, 207, 427, 880, 223, 693, 492, 116, 662, 80, 646, 626, 495, 763, 555, 286, 415, 100, 615, 447, 71, 500, 400, 698, 873, 234, 765, 416, 262, 535, 520, 218, 546, 649, 694, 818, 19, 654, 411, 368, 303, 845, 246, 856, 37, 343, 221, 783, 601, 843, 862, 848, 392, 341, 45, 846, 449, 714, 180, 877, 775, 465, 277, 204, 136, 114, 552, 407, 437, 828, 607, 316, 241, 813, 445, 232, 23, 94, 564, 915, 788, 379, 410, 202, 260, 426, 691, 166, 404, 580, 637, 866, 231, 904, 18, 239, 459, 901, 349, 281, 684, 52, 611, 361, 147, 167, 784, 435, 732, 664, 677, 824, 139, 203, 213, 130, 469, 530, 56, 852, 748, 377, 569, 364, 466, 736, 399, 902, 482, 301, 851, 63, 254, 200, 266, 830, 41, 14, 832, 668, 332, 159, 439, 484, 119, 758, 559, 310, 253, 397, 859, 627, 806, 62, 622, 146, 81, 2, 641, 51, 105, 390, 443, 740, 354, 558, 89, 263, 755, 386, 638, 905, 378, 844, 315, 728, 706, 43, 631, 645, 860, 817, 567, 501, 870, 99, 721, 553, 269, 151, 850, 750, 280, 185, 184, 888, 526, 31, 20, 32, 418, 236, 480, 460, 584, 735, 235, 140, 545, 518, 712, 797, 505, 746, 50, 452, 602, 240, 247, 572, 665, 893, 417, 376, 244, 803, 802, 76, 237, 704, 302, 723, 371, 4, 102, 857, 798, 49, 762, 389, 83, 667, 838, 887, 289, 620, 571, 760, 861, 471, 61, 713, 75, 346, 187, 233, 592, 682, 95, 369, 181, 243, 186, 895, 834, 800, 502, 161, 847, 885, 653, 430, 916, 488, 899, 816, 249, 182, 458, 327, 841, 59, 676, 491, 554, 659, 776, 17, 786, 671, 780, 468, 594, 849, 917, 908, 259, 716, 890, 475, 60, 652, 82, 493, 636, 85, 508, 812, 533, 536, 46, 689, 598, 444, 751, 34, 385, 576, 670, 211, 73, 419, 455, 805, 563, 162, 479, 477, 150, 282, 128, 779, 630, 320, 345, 423, 396, 11, 642, 53, 272, 7, 579, 685, 539, 225, 868, 322, 744, 323, 796, 174, 562, 473, 176, 278, 699, 839, 374, 842, 148, 33, 414, 581, 27, 201, 494, 504, 58, 506, 283, 647, 782, 296, 863, 25, 227, 129, 250, 522, 127, 42, 311, 919, 382, 597, 661, 133, 208, 574, 710, 226, 93, 69, 695, 328, 64, 511, 16, 920, 155, 101, 708, 585, 36, 720, 450, 487, 799, 711, 462, 697, 403, 729, 321, 342, 265, 317, 192, 701, 29, 384, 432, 894, 809, 792, 734, 703, 658, 456, 299, 295, 517, 855, 131, 854, 440, 810, 98, 790, 639, 612, 547, 586, 648, 229, 193, 727, 312, 688, 92, 112, 21, 333, 635, 833, 66, 113, 15, 194, 588, 773, 84, 318, 831, 772, 420, 719, 380, 777, 604, 722, 135, 30, 515, 358, 766, 442, 910, 428, 55, 804, 77, 308, 363, 457, 340, 789, 733, 632, 700, 197, 214, 911, 261, 134, 521, 807, 903, 336, 219, 398, 276, 715, 157, 548, 696, 216, 375, 405, 768, 125, 413, 570, 669, 795, 483, 245, 3, 168, 656, 217, 605, 730, 351, 441, 801, 835, 307, 827, 556, 560, 583, 109, 785, 678, 406, 900, 575, 96, 690, 724, 820, 867, 794, 747, 651, 8, 681, 692, 170, 525, 884, 738, 623, 434, 542, 527, 156, 891, 177, 808, 258, 814, 314, 454, 339, 673, 103, 921, 534, 881, 165, 68, 122, 87, 359, 431, 115, 918, 79, 914, 284, 412, 573, 190, 618, 883, 365, 344, 309, 516, 826, 530, 485, 373, 188, 499, 290, 675, 294, 220, 858, 357, 86, 209, 461, 875, 287, 864, 111, 663, 811, 549, 507, 707, 561, 619, 350, 793, 672, 5, 825, 242, 401, 822, 749, 634, 741, 297, 725, 913, 496, 256, 726, 215, 171, 829, 121, 476, 108, 1, 117, 149, 175, 324, 640, 657, 355, 298, 224, 273, 255, 153, 650, 12, 257, 587, 26, 44, 118, 683, 230, 300, 874, 628, 633, 391, 367, 774, 680, 513, 271, 643, 172, 666, 821, 823, 179, 550, 463, 338, 787, 566, 313, 599, 402, 565, 306, 909, 274, 13, 739, 679, 771, 453, 753, 486, 67, 541, 285, 123, 577, 388, 144, 293, 781, 764, 769, 621, 366, 383, 907, 124, 372, 72, 35, 173, 606, 519, 451, 353, 54, 348, 617, 761, 152, 137, 132, 836, 514, 409, 70, 370, 387, 524, 6, 88, 163, 160, 718, 745, 472, 869, 40, 481, 248, 551, 889, 898, 709, 886, 897, 268, 912, 154, 478, 538, 819, 467, 97, 644, 596, 104, 251, 206, 145, 199, 878, 319, 608, 497, 195, 737, 448, 529, 65, 582, 489, 57, 613, 490, 158, 600, 252, 686, 438, 275, 393, 238, 757, 624, 183, 589, 270, 267, 169, 326, 767, 815, 421, 352, 24, 578, 126, 356, 687, 446, 544, 141, 865, 436, 603, 532, 674, 429, 731, 91, 896, 90, 120, 196, 329, 360, 717, 660, 591, 512, 593, 381, 325, 395, 876, 212, 48, 424, 337, 610, 222, 334, 882, 906, 264, 509, 871, 702, 705, 537, 189, 107, 474, 609, 743, 543, 422, 138, 837, 330, 164, 433, 143, 498, 879, 22, 590, 528, 655, 210, 288, 464, 742, 568, 228, 840, 770, 510, 540, 347, 142, 470, 523, 335, 595, 279, 531, 110, 74, 614, 38};
        int[] b = {-4, 7, 5, 3, 5, -4, 2, -1, -9, -8, -3, 0, 9, -7, -4, -10, -4, 2, 6, 1, -2, -3, -1, -8, 0, -8, -7, -3, 5, -1, -8, -8, 8, -1, -3, 3, 6, 1, -8, -1, 3, -9, 9, -6, 7, 8, -6, 5, 0, 3, -4, 1, -10, 6, 3, -8, 0, 6, -9, -5, -5, -6, -3, 6, -5, -4, -1, 3, 7, -6, 5, -8, -5, 4, -3, 4, -6, -7, 0, -3, -2, 6, 8, -2, -6, -7, 1, 4, 9, 2, -10, 6, -2, 9, 2, -4, -4, 4, 9, 5, 0, 4, 8, -3, -9, 7, -8, 7, 2, 2, 6, -9, -10, -4, -9, -5, -1, -6, 9, -10, -1, 1, 7, 7, 1, -9, 5, -1, -3, -3, 6, 7, 3, -4, -5, -4, -7, 9, -6, -2, 1, 2, -1, -7, 9, 0, -2, -2, 5, -10, -1, 6, -7, 8, -5, -4, 1, -9, 5, 9, -2, -6, -2, -9, 0, 3, -10, 4, -6, -6, 4, -3, 6, -7, 1, -3, -5, 9, 6, 2, 1, 7, -2, 5};
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(1);
        a.add(3);
        a.add(2);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(3);
        a.add(3);
        a.add(3);
        System.out.println(m.repeatedNumber2(a));
    }
}
