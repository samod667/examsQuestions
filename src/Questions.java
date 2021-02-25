public class Questions {
    public static void printClosest(int[] a, int[] b, int x) {
        int minA = 0, minB = 0, min = Integer.MAX_VALUE;
        int tempA = 0, tempB = b.length - 1;

        while (tempA < a.length && tempB >= 0) {
            int diffRes = Math.abs((a[tempA] + b[tempB]) - x);

            if (diffRes < min) {
                minA = tempA;
                minB = tempB;
                min = diffRes;

                if (min == 0) {
                    break;
                }
            }
            if (a[tempA] + b[tempB] > x) {
                tempB--;
            } else {
                tempA++;
            }
        }

        System.out.println(a[minA] + "  " + b[minB]);
    }

    public static boolean splitTo3(int[] a) {
        int limit1 = 0, limit2 = a.length - 1;
        int sum = 0;

        for (int i = limit1; i < limit2; i++) {
            sum += a[limit1];

            while (limit1 < limit2) {
                if (sum == 0) {
                    return true;
                } else if (sum < 0) {
                    sum -= a[limit1];
                    limit1++;
                } else {
                    sum -= a[limit2];
                    limit2--;
                }
            }
        }
        return false;
    }

    public static void replace(int[] a) {
        int maxVal = a[a.length - 1];

        a[a.length - 1] = 0;

        for (int i = a.length - 2; i > -1; i--) {
            int temp = a[i];

            a[i] = maxVal;

            if (temp > maxVal) {
                maxVal = temp;
            }
        }
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void printPairs(int[] a, int k) {
        int lo = 0, hi = 1, diff = 0;

        while (hi < a.length) {
            diff = Math.abs(a[hi] - a[lo]);

            if (diff == k) {
                System.out.println("Pair Found: " + a[lo] + " " + a[hi]);
                lo++;
                hi++;
            } else if (diff > k) {
                lo++;
            } else {
                hi++;
            }
        }
    }

    public static int smallestSub(int[] a, int k) {
        int smallestSize = a.length + 1, count = 1;

        int lo = 0, hi = 1, sum = a[0];

        while (hi >= lo) {
            if (sum > k) {    //BIGGER
                smallestSize = Math.min(count, smallestSize);
                sum -= a[lo++];
                count--;
            } else {        //SMALLER
                if (hi == a.length - 1) {
                    break;
                }
                sum += a[hi++];
                count++;
            }
        }
        return smallestSize;
    }

    public static int findSmallest(int[] a) {
        int i, sum;
        for (i = 1, sum = a[0]; i < a.length; i++) {
            if (a[i] - sum > 1) {
                return sum + 1;
            }

            sum += a[i];
        }

        return sum + 1;
    }

    public static int minDiff(int[] arr) {
        return minDiff(arr, 0, 0, 0);
    }

    private static int minDiff(int[] arr, int i, int sum, int sum2) {
        if (i == arr.length) {
            return Math.abs(sum - sum2);
        }
        return Math.min(minDiff(arr, i + 1, sum + arr[i], sum2), minDiff(arr, i + 1, sum, sum2 + arr[i]));
    }

    public static int passingCars(int[] a) {
        int west = 0, east = 0;


        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                east++;
            } else {
                west += east;
            }
        }
        return west;
    }

    public static boolean findX(int[] a, int x) {
        int l = 0, h = a.length - 1, m, sum;

        if (a.length == 1) {
            return false;
        }

        while (l <= h) {
            m = (l + h) / 2;

            sum = a[m] + a[m + 1];

            if (sum == x) {
                return true;
            }

            if (sum > x) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public static int count(int num) {
        return count(num, 1);
    }

    private static int count(int num, int counter) {
        if (num == 0) {
            return 1;
        }

        if (num < 0 || counter > num) {
            return 0;
        }

        int opt1 = count(num, counter + 1);
        int opt2 = count(num - counter, counter + 1);

        return opt1 + opt2;
    }

    public static int cheapestRoute(int[] a) {
        return cheapestRoute(a, 0);
    }

    private static int cheapestRoute(int[] a, int i) {
        if (i >= a.length) {
            return Integer.MAX_VALUE;
        }

        if (i == a.length - 1) {
            return a[i];
        }

        if (i == a.length - 2) {
            return a[i] + cheapestRoute(a, i + 1);
        }

        int opt1 = a[i] + cheapestRoute(a, i + 1);
        int opt2 = a[i] + cheapestRoute(a, i + 2);

        return Math.min(opt1, opt2);
    }

    public static int oneFiveSeven(int num) {
        return oneFiveSeven(num, 0);
    }

    public static int oneFiveSeven(int num, int count) {
        if (num < 0) {
            return Integer.MAX_VALUE;
        }

        if (num == 0) {
            return count;
        }

        int one = oneFiveSeven(num - 1, count + 1);
        int five = oneFiveSeven(num - 5, count + 1);
        int seven = oneFiveSeven(num - 7, count + 1);

        return minValue(one, five, seven);
    }

    private static int minValue(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }

    public static int findMaximum(int[][] mat) {
        return findMaximum(mat, 0, 0);
    }

    private static int findMaximum(int[][] mat, int i, int j) {
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[i].length || mat[i][j] == -1) {
            return 0;
        }
        if (i % 2 == 0) {
            int val = mat[i][j];

            mat[i][j] = -1;

            int right = val + findMaximum(mat, i, j + 1);
            int down = val + findMaximum(mat, i + 1, j);

            mat[i][j] = val;

            return Math.max(right, down);
        } else {
            int val = mat[i][j];

            mat[i][j] = -1;

            int left = val + findMaximum(mat, i, j - 1);
            int down = val + findMaximum(mat, i + 1, j);

            mat[i][j] = val;

            return Math.max(left, down);
        }
    }

    public static int strictlyIncreasing(int[] a) {
        int i = 1, count = 0;

        while (i < a.length) {
            if (a[i] > a[i - 1]) {
                count++;
                i++;
            } else {
                i++;
            }
        }

        return count;
    }

    public static int howManySorted(int n, int max) {
        return howManySorted(n, max, 1);
    }

    private static int howManySorted(int n, int max, int value) {
        if (n == 0 || value == max) {
            return 1;
        }

        int opt1 = howManySorted(n, max, value + 1);
        int opt2 = howManySorted(n - 1, max, value);

        return opt1 + opt2;
    }

    public static boolean split(int[] a, int diff) {
        return split(a, diff, 0, 0, 0);
    }

    private static boolean split(int[] a, int diff, int i, int sum1, int sum2) {
        if (i == a.length) {
            if (diff == Math.abs(sum1 - sum2)) {
                return true;
            }
            return false;
        }
        return split(a, diff, i + 1, sum1 + a[i], sum2) || split(a, diff, i + 1, sum1, sum2 + a[i]);
    }

    public static int minPrice(int[][] mat){
        return minPrice(mat, 0, 1);
    }

    private static int minPrice(int[][] mat, int i, int j){
        if(j == mat[i].length - 1){
            return mat[i][j];
        }

        if(j >= mat[i].length || i > j || mat[i][j] == -1){
            return Integer.MAX_VALUE;
        }

        int opt1 = mat[i][j] + minPrice(mat, j, j);
        int opt2 = minPrice(mat, i, j + 1);

        return Math.min(opt1, opt2);
    }

    public static boolean findAverage(int[] arr, double x){
        int l = 0;
        while(l < arr.length && arr[l] < x){
            l++;
        }
        int r = l;
        double sum = arr[l];
        double count = 1;

        while(sum / count != x){
            double avg = sum / count;
            if(avg > x){
                l--;
                if(l < 0){
                    return false;
                }
                sum += arr[l];
            } else {
                r++;
                if(r == arr.length){
                    return false;
                }
                sum += arr[r];
            }
            count++;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trainPath = {
                {0, 15, 80, 90},
                {-1, 0, 40, 50},
                {-1, -1, 0, 70},
                {-1, -1, -1, 0}
        };

        int[] avg = {2, 3, 8, 14, 15, 35};
        System.out.println(findAverage(avg, 8.5));
    }
}