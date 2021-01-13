public class ExamsQuestions {
///////////////////////////////////////////////////////////////////////////////////////////

    public static boolean isSum(int[] a, int num) {
        return isSum(a, num, 0, 0);
    }

    private static boolean isSum(int[] a, int num, int pre, int i) {
        if (num == 0) {
            return true;
        }

        if (i >= a.length) {
            return false;
        }

        if (num < 0) {
            return false;
        }

        boolean temp1 = false;
        boolean temp2 = false;
        boolean temp3 = false;

        if (pre + 1 == i) {
            temp1 = isSum(a, num - a[i], i, i + 2);
        } else {
            temp2 = isSum(a, num - a[i], i, i + 1);
            temp3 = isSum(a, num, pre, i + 1);
        }

        return temp1 || temp2 || temp3;

    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Calculates the shortest road between to roads
    public static int shortestRoad(int[] road1, int[] road2) {
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < road2.length; i++) {
            sum1 += road1[i];
            sum2 += road2[i];
        }

        int smallestSum = Math.min(sum1, sum2);

        for (int i = 0; i < road2.length; i++) {
            sum1 -= road1[i] + road2[i];
            sum2 -= road2[i] + road1[i];

            smallestSum = Math.min(smallestSum, Math.min(sum1, sum2));
        }

        return smallestSum;

    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Checks which integer is the median between two sub arrays with the same sum of values
    public static int halfSum(int[] a) {
        int sum = 0;

        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        int halfSum = 0;

        for (int i = 0; i < a.length; i++) {
            halfSum += a[i];

            if (halfSum * 2 == sum) {
                return i;
            } else if (halfSum * 2 > sum) {
                return Integer.MIN_VALUE;
            }
        }

        return Integer.MIN_VALUE;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    //finds if there is a following two values in the array which their sum is equal to x
    public static boolean findX(int[] arr, int x) {
        int low = 0, high = arr.length - 1, mid = 0;

        while (low < high) {
            mid = (low + high) / 2;

            if (arr[mid] + arr[mid + 1] == x) {
                return true;
            } else if (arr[mid] + arr[mid - 1] == x) {
                return true;
            }

            if (arr[mid] + arr[mid + 1] > x) {
                high = mid - 1;
            } else if (arr[mid] + arr[mid + 1] < x) {
                low = mid + 1;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///A function that will return the number which the value will be equal to the index in the array a
    public static int fixedPoint(int[] a) {
        int low = 0, high = a.length - 1, mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (a[mid] == mid) {
                return a[mid];
            }
            if (a[low] == low) {
                return a[low];
            }

            if (a[high] == high) {
                return a[high];
            }

            if (a[mid] > mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Function that will return the missing value from an arithmetic sequence
    public static int arithmeticMissingValue(int[] arr) {
        int arrLength = arr.length;

        int diff = (arr[arrLength - 1] - arr[0]) / arrLength;

        int low = 0, high = arrLength - 1;

        return arithmeticMissingValue(arr, low, high, diff);
    }

    private static int arithmeticMissingValue(int[] arr, int low, int high, int diff) {
        int mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid + 1] - arr[mid] != diff) {
                return arr[mid] + diff;
            }

            if (mid > 0 && arr[mid] - arr[mid - 1] != diff) {
                return arr[mid] - diff;
            }

            if (arr[mid] == arr[0] + mid * diff) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }


        return Integer.MIN_VALUE;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Longest path of a warm that goes through a sorted sequence of values within a matrix
    public static int longestWorm(int[][] matt) {
        return longestWorm(matt, 0, 0);
    }

    private static int longestWorm(int[][] matt, int i, int j) {
        if (i == matt.length) {
            return 0;
        }

        if (j == matt[i].length) {
            return longestWorm(matt, i + 1, 0);
        }

        int r1 = longestWorm(matt, i + 1, j, matt[i][j]);
        int r2 = longestWorm(matt, i - 1, j, matt[i][j]);
        int r3 = longestWorm(matt, i, j + 1, matt[i][j]);
        int r4 = longestWorm(matt, i, j - 1, matt[i][j]);

        int r = Math.max(Math.max(r1, r2), Math.max(r3, r4)) + 1;

        return Math.max(r, longestWorm(matt, i, j + 1));
    }

    private static int longestWorm(int[][] matt, int i, int j, int prevCell) {
        if (i < 0 || i == matt.length || j < 0 || j == matt[i].length) {
            return 0;
        }

        if (matt[i][j] != prevCell + 1) {
            return 0;
        }

        int r1 = longestWorm(matt, i + 1, j, matt[i][j]);
        int r2 = longestWorm(matt, i - 1, j, matt[i][j]);
        int r3 = longestWorm(matt, i, j + 1, matt[i][j]);
        int r4 = longestWorm(matt, i, j - 1, matt[i][j]);
        int r = Math.max(Math.max(r1, r2), Math.max(r3, r4));

        return r + 1;

    }


///////////////////////////////////////////////////////////////////////////////////////////

    ///Find the shortest path in a matrix
    public static int shortestPath(int[][] matt) {
        return shortestPath(matt, 0, 0, 0, 0);
    }

    private static int shortestPath(int[][] matt, int i, int j, int prevValue, int count) {
        if (i < 0 || j < 0 || i == matt.length || j == matt[i].length || matt[i][j] <= prevValue) {
            return Integer.MAX_VALUE;
        }

        if (i == matt.length - 1 && j == matt[i].length - 1) {
            return count + 1;
        }

        int option1 = shortestPath(matt, i + 1, j, matt[i][j], count + 1);
        int option2 = shortestPath(matt, i - 1, j, matt[i][j], count + 1);
        int option3 = shortestPath(matt, i, j + 1, matt[i][j], count + 1);
        int option4 = shortestPath(matt, i, j - 1, matt[i][j], count + 1);

        return Math.min(Math.min(option1, option2), Math.min(option3, option4));
    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Will find the longest path from (0,0) in the array to (x,y) that goes only through 1 values --> 2019,83
    public static int longestPath(int[][] mat, int x, int y) {
        return longestPath(mat, x, y, 0, 0, 0);
    }

    private static int longestPath(int[][] mat, int x, int y, int i, int j, int count) {
        if (i == x && j == y) {
            return count + 1;
        }

        if (i < 0 || j < 0 || i == mat.length || j == mat[i].length || mat[i][j] == 0) {
            return Integer.MIN_VALUE;
        }

        mat[i][j] = 0;

        int up = longestPath(mat, x, y, i + 1, j, count + 1);
        int down = longestPath(mat, x, y, i - 1, j, count + 1);
        int right = longestPath(mat, x, y, i, j + 1, count + 1);
        int left = longestPath(mat, x, y, i, j - 1, count + 1);

        mat[i][j] = 1;

        return Math.max(Math.max(up, down), Math.max(right, left));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    public static int maxSumKnight(int[][] mat) {
        return maxSumKnight(mat, 0, 0, mat[0][0] - 1);
    }

    private static int maxSumKnight(int[][] mat, int i, int j, int prevValue) {
        if (i == mat.length - 1 && j == mat[i].length - 1) {
            return mat[i][j];
        }

        if (i < 0 || j < 0 || i >= mat.length || j >= mat[i].length) {
            return -1;
        }

        if (mat[i][j] == -1) {
            return -1;
        }

        if (prevValue + 1 != mat[i][j] && prevValue - 1 != mat[i][j]) {
            return -1;
        }

        int tmp = mat[i][j];
        mat[i][j] = -1;

        int moveKnightOpt1 = maxSumKnight(mat, i + 2, j + 1, tmp);
        int moveKnightOpt2 = maxSumKnight(mat, i + 2, j - 1, tmp);
        int moveKnightOpt3 = maxSumKnight(mat, i + 1, j + 2, tmp);
        int moveKnightOpt4 = maxSumKnight(mat, i - 1, j + 2, tmp);
        int moveKnightOpt5 = maxSumKnight(mat, i - 2, j + 1, tmp);
        int moveKnightOpt6 = maxSumKnight(mat, i - 2, j - 1, tmp);
        int moveKnightOpt7 = maxSumKnight(mat, i - 1, j - 2, tmp);
        int moveKnightOpt8 = maxSumKnight(mat, i + 1, j - 2, tmp);

        mat[i][j] = tmp;

        int max1 = Math.max(Math.max(moveKnightOpt1, moveKnightOpt2), Math.max(moveKnightOpt3, moveKnightOpt4));
        int max2 = Math.max(Math.max(moveKnightOpt5, moveKnightOpt6), Math.max(moveKnightOpt7, moveKnightOpt8));

        int max = Math.max(max1, max2);

        if (max == -1) {
            return -1;
        } else {
            return tmp + max;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //A function that calculates the longest palindrome sequence in an Array --> 2019,85
    public static int longestPalindrome(int[] a){
        return longestPalindrome(a, 0, 1);
    }

    private static int longestPalindrome(int[] a, int baseIndex, int j) {
        if(baseIndex == a.length){
            return 1;
        }

        if(j == a.length){
            return longestPalindrome(a, baseIndex + 1, baseIndex + 1);
        }
        int palindromeRes = 0;

        if(a[baseIndex] == a[j]){
            palindromeRes = longestPalindrome(a, baseIndex, j, 0);
        }

        return Math.max(palindromeRes, longestPalindrome(a, baseIndex, j + 1));
    }

    private static int longestPalindrome(int[] a, int start, int end, int count){
        if(a[start] != a[end]){
            return 0;
        }

        if(start > end){
            return count;
        }

        if(start == end){
            return count + 1;
        }

        return longestPalindrome(a, start + 1, end - 1, count + 2);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///Function that calculates how many negative numbers can be found in a squared matrix --> O(n + m)
    public static int howManyNegativeNumbers(int[][] mat){      ///2019,83
       int row = 0, col = mat[row].length - 1, count = 0;

       while(row < mat.length && col >= 0){
           if(mat[row][col] < 0){
            count += col + 1;
            row += 1;
           } else {
               col -= 1;
           }
       }
       return count;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //A function that will return the index of the elements of which sum is equal to the following values backwards ---> 2007
    public static int equalSum(int[] arr){
        return equalSum(arr, 0, 0);
    }

    private static int equalSum(int[] arr, int i, int sum){
       if(i == arr.length){
           return sum;
       }

       if(equalSum(arr, i + 1, sum + arr[i]) / 2 == sum){
           return i - 1;
       } else {
           return equalSum(arr, i + 1, sum + arr[i]);
       }
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Will print to the console the path to a hill in a matrix ----> 2014
    public static void printPath(int[][] mat){
        printPath(mat, 0, 0);
    }
    private static void printPath(int[][] mat, int i , int j){
        int value = mat[i][j];
        int width = mat[0].length;
        int height = mat.length;

        System.out.print("(" + i + "," + j + ") ");

        if(i < width - 1 && mat[i + 1][j] > value){
            printPath(mat, i + 1, j);
        }
        else if(i > 0 && mat[i - 1][j] > value){
            printPath(mat, i - 1, j);
        }
        else if(j < width - 1 && mat[i][j + 1] > value){
            printPath(mat,i ,j + 1);
        }
        else if(j > 0 && mat[i][j - 1] > value){
            printPath(mat, i, j - 1);
        } else {
            return;
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///Find the maximum value in an array ---> 2014
    public static int findMax(int[] arr){
        int low = 0, high = arr.length - 1, mid = 0;

        while(low <= high){
            mid = (low + high) / 2;

            if(arr[mid] > arr[low] && arr[mid] < arr[high]){
                return high;
            }

            if(high == low){
                return high;
            }

            if(arr[mid] < arr[low]){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
     //Find Maximum length of matrix where you can go only through 1s and 0s  ---> 2020,85
    public static int findMaximum(int[][] mat) {
        if(mat[0][0] == -1){
            return -1;
        }
        return findMaximum(mat, 0, 0);
    }

    private static int findMaximum(int[][] mat, int i, int j){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat[i].length || mat[i][j] == -1){
            return 0;
        }

        if (i % 2 == 0){
            int val = mat[i][j];

            mat[i][j] = -1;

            int right =  val + findMaximum(mat, i, j + 1);
            int down = val + findMaximum(mat, i + 1, j);

            mat[i][j] = val;

            return Math.max(right,down);
        }else {
            int val = mat[i][j];

            mat[i][j] = -1;

            int left = val + findMaximum(mat, i, j - 1);
            int down = val + findMaximum(mat, i + 1, j);

            mat[i][j] = val;

            return Math.max(left,down);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Get the number of sub array which are increasing in an array --> 2020,85
    public static int strictlyIncreasing(int[] a){
        int count = 0, ptr = 0;

        for (int i = 1; i < a.length; i++) {
           if(a[i] > a[i - 1]){
               count = count + (i - ptr);
           } else {
               ptr = i;
           }
        }
        return count;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Question 1 --> 2020,87
    public static int totalWays(int[][] mat, int k){
        return totalWays(mat, 0, 0, k);
    }

    private static int totalWays(int[][] mat, int i, int j, int k){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat.length){
            return 0;
        }

        if(i == mat.length - 1 && j == mat[i].length - 1 && k == 0){
            return 1;
        }

        if(i == mat.length -1 && j == mat[i].length - 1 && k != 0){
            return 0;
        }

        if(k == 0 && i != mat.length - 1 && j != mat[i].length - 1){
            return 0;
        }

        int turn = 0, down = 0, right = 0;
        if(k > 0){
            turn = totalWays(mat, i + 1, j + 1, k - 1);
        } else {
           down = totalWays(mat, i + 1, j, k);
           right = totalWays(mat, i, j + 1, k);
        }

        return turn + down + right;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///Count how many time a value is showing up in a sorted array --> O(log n)
    public static int count(int[] a, int x){
        int low = 0, high = a.length - 1, mid = 0;

        int indexValue1 = 0, indexValue2 = 0;

        while(low <= high){
            mid = (low + high) / 2;
            if(a[mid] > x){
                high = mid - 1;
            } else if(a[mid] < x){
                low = mid + 1;
            } else {
                indexValue1 = mid;
                low = mid + 1;
            }
        }

        low = 0; high = a.length - 1;
        while(low <= high){
            mid = (low + high) / 2;

            if(a[mid] > x){
                high = mid -1;
            } else if(a[mid] < x){
                low = mid + 1;
            } else {
                indexValue2 = mid;
                high = mid -1;
            }
        }

        return indexValue1 - indexValue2 + 1;
    }



    public static void main(String[] args) {

//        int[][] matt1 = {
//                {3,13,15,28,30},
//                {50, 51,52,29,30},
//                {51,10,53,54,55},
//                {53,12,14,53,11}
//        };
//
//        int [][] matt2 = {
//                {3,13,15,28,30},
//                {40, 51,52,29,30},
//                {28,10,53,54,53},
//                {53,12,55,53,60},
//                {70,62,56,20,80},
//                {80,81,90,95,100}
//        };
//
//        int[] a = {-3,0,2,4,11,12,15, 7};
//
//        int[][] binaryMatt = {
//                {1,1,1,1,1,1,1},
//                {1,1,0,1,0,0,1},
//                {1,1,1,1,0,1,1}
//        };

//        int[][] knightBoard = {
//                {4, 5, 6, 7, 1},
//                {3, 5, 1, 7, 4},
//                {4, 5, 6, 5, 8},
//                {3, 4, 7, 7, 9},
//                {6, 2, 2, 7, 6}
//        };

        int[] palindromeArray = {1,3,2,3,10,10,3,2,4};
        int[] pal2 = {1,6,3,10,10,1,19};

        int[][] negativeMatrix = {
                {-99,-72,-64,-55,-28,-10,-5},
                {-72,-53,-46,-38,11,13,12},
                {-63,-48,-27,-12,14,16,23},
                {-44,-29,-10,0,18,20,28},
                {0,12,14,20,28,30,35}
        };

        int[] arr1 = {2,1,6,5,4};

        int[][] hillMatt = {
                {3,8,7,1},
                {5,15,2,4},
                {12,14,-13,22},
                {13,16,17,52}
        };

        int[] arr = {6,70,75,90,150,200,48,49,52};

        int[][] matt3 = {
                {1,1,-1,1,1},
                {1,0,0,-1,1},
                {1,1,1,1,-1},
                {-1,-1,1,1,1},
                {1,1,-1,-1,1}
        };

        int[][] mat4 = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        int[] arr2 = {1,2,4,4, 5, 6};
        int[] arr3 = {-5,-5,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,67,67,99};

        System.out.println(count(arr3, -5));

    }
}
