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
            temp3 = isSum(a, num - a[pre], pre, i + 1);
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

        int diff = (arr[arrLength - 1] - arr[0]) / arrLength; //Arithmetic difference

        int low = 0, high = arrLength - 1;

        return arithmeticMissingValue(arr, low, high, diff);
    }

    private static int arithmeticMissingValue(int[] arr, int low, int high, int diff) {
        int mid = 0;

        while (low <= high) {
            mid = (low + high) / 2;

            if (arr[mid + 1] - arr[mid] != diff) {  //Found the missing value
                return arr[mid] + diff;
            }

            if (mid > 0 && arr[mid] - arr[mid - 1] != diff) {  //Found the missing value
                return arr[mid] - diff;
            }

            if (arr[mid] == arr[0] + mid * diff) {  //Check where missing value is found
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
        return totalWays(mat, 0, 0, k, 0, 0);
    }

    private static int totalWays(int[][] mat, int i, int j, int k, int prevI, int prevJ){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat.length){
            return 0;
        }

        if(i == mat.length - 1 && j == mat[i].length - 1 && k == 0){
            return 1;
        }

        if(i == mat.length -1 && j == mat[i].length - 1 && k != 0){
            return 0;
        }

        if(i > prevI && j == prevJ){
            return totalWays(mat, i, j + 1, k - 1, i, j) + totalWays(mat, i + 1, j, k, i, j);

        } else if(i == prevI && j > prevJ){
            return totalWays(mat, i + 1, j, k - 1, i, j) + totalWays(mat, i, j + 1, k, i, j);

        }

        return totalWays(mat, i, j + 1, k, i, j) + totalWays(mat, i + 1, j, k, i, j);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///Count how many time a value is showing up in a sorted array --> O(log n)
    public static int count(int[] a, int x){
        int low = 0, high = a.length - 1, mid = 0;

        int indexValue1 = 0, indexValue2 = 0;

        while(low <= high){     //get the rightest index of x in the array
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
        while(low <= high){        //fet the leftest index of x in the array
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

        return indexValue1 - indexValue2 + 1;   //subtract both indexes + 1 to get the result
    }

///////////////////////////////////////////////////////////////////////////////////////////
    //Find the length in array from the nearest zero and change the array accordingly --> O(n)
    public static void zeroDistance(int[] a){
        int zeroIndex = 0;

        for (int i = a.length - 1; i >= 0 ; i--) {
            if(a[i] == 0){
                zeroIndex = i;
            } else {
                a[i] = Math.abs(i - zeroIndex);
            }
        }

        zeroIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] == 0){
                zeroIndex = i;
            } else {
                if(a[i] > Math.abs(zeroIndex - i)){
                    a[i] = Math.abs(zeroIndex - i);
                }
            }
        }
    }

    private static void printArray(int[] a){
        for (int j : a) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Find the maximal drop value between to values in the array --> 2010,82
    public static int maximalDrop(int[] a){ // O(n)
        int hillIndex = 0, maxDrop = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > a[hillIndex]) {
                hillIndex = i;
            } else {
                if (a[hillIndex] - a[i] > maxDrop) {
                    maxDrop = a[hillIndex] - a[i];
                }
            }
        }
        return maxDrop;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Prints all the triplets which the sum of their multiplication is equal to num --> 2020,87
    public static void printTriplets(int[] a, int num){         /// --> O(n)
        for (int i = 0; i < a.length; i++) {
            int mid = i + 1;
            int high = a.length - 1;

            while(mid < high){
                int multiResult = a[i] * a[mid] * a[high];
                if(multiResult == num){
                    System.out.println(a[i] + " " + a[mid] + " " + a[high] + " ");
                }
                if(multiResult < num){
                    mid = mid + 1;
                } else {
                    high = high - 1;
                }
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Prints a special string without the char 'a' --> 2016,A
    public static void specialPrint(String s){
        if(s.length() == 0){
            return;
        }
        if(s.charAt(0) == 'a'){
            System.out.println(s);
        }
        specialPrint(s.substring(1));
    }

   private static boolean isValid(int[][] mat, int i, int j){
        return i >= 0 && j >= 0 && i < mat.length && j < mat[i].length;
   }

   private static boolean allZero(int[][] mat, int i, int j){
        if(j == mat[i].length - 1 && i < mat.length - 1){
            return allZero(mat, i + 1, 0);
        }

        if(j == mat[i].length - 1 && i == mat.length - 1){
            return true;
        }

        if(mat[i][j] != 0){
            return false;
        }

        return allZero(mat, i, j + 1);
    }

    public static void printArray(int[][] arr){
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + "  ");
            }
            System.out.println();
        }
    }

    public static void printArray(char[][] arr){
        for (char[] chars : arr) {
            for (char aChar : chars) {
                System.out.print(aChar + "  ");
            }
            System.out.println();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Comparing between two words and checking which one is the bigger in a dictionary order --> 2006, 5

    public static int myCompare(String s1, String s2){
        if(s1.length() > 0 && s2.length() == 0){
            return 1;
        }

        if(s2.length() > 0 && s1.length() == 0){
            return -1;
        }

        if(s1.length() == 0){
            return 0;
        }

        if(s1.charAt(0) > s2.charAt(0)){
            return 1;
        }

        if(s1.charAt(0) < s2.charAt(0)){
            return -1;
        }

        return myCompare(s1.substring(1), s2.substring(1));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Returns true if two int arrays have the same values - not necessarily in the same order
    public static boolean isPermutation(int[] a, int[] b){
        if(a.length != b.length){
            return false;
        }

        bubble(a, 0);
        bubble(b, 0);

        return isPermutation(a, b, 0, 0);
    }

    public static boolean isPermutation(int[] a, int[] b, int i, int j){
        if(i == a.length && j == b.length){
            return true;
        }

        if(a[i] != a[j]){
            return false;
        }

        return isPermutation(a, b, i + 1, j + 1);
    }

    private static void bubble(int[] a, int i){
        int temp = 0;
        if(sorted(a, 0)){
            return;
        }

        if(i < a.length - 1){
            if(a[i] > a[i + 1]){
                temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }

        if(i == a.length){
            bubble(a, 0);
        }
        bubble(a, i + 1);
    }

    private static boolean sorted(int[] a, int i){
        if (i == a.length - 1){
            return true;
        }

        if(a[i] > a[i + 1]){
            return false;
        }

        return sorted(a, i + 1);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isPermutation2(int[] a, int[] b){
        if(a.length != b.length){
            return false;
        } else {
            return isPermutation2(a, b, 0);
        }
    }

    private static boolean isPermutation2(int[] a, int[] b, int i){
        if(i == a.length){
            return true;
        }

        boolean checkValue = checkVal(b, a[i], 0);

        if(!checkValue){
            return false;
        }

        return isPermutation2(a, b, i + 1);
    }

    private static boolean checkVal(int[] b, int value, int i){
        if(i == b.length){
            return false;
        }

        if(b[i] == -1){
            return checkVal(b, value, i + 1);
        }

        if(b[i] == value){
            b[i] = -1;
            return true;
        }

        return checkVal(b, value, i + 1);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //Check what is the minimal combination you can reach num with addition of 1 5 and 7
    public static int oneFiveSeven(int n){
        return oneFiveSeven(n, 0);
    }

    private static int oneFiveSeven(int n, int count){
        if(n == 0){
            return count;
        }

        if(n < 0){
            return Integer.MAX_VALUE;
        }

        int one = oneFiveSeven(n - 1, count + 1);
        int five = oneFiveSeven(n - 5, count + 1);
        int seven = oneFiveSeven(n - 7, count + 1);

//        if(one < five && one < seven){
//            return one;
//        } else if(one > five && five < seven){
//            return five;
//        } else {
//            return seven;
//        }
        return minValue(one, five, seven);
//        return Math.min(Math.min(one, five), seven);
    }

    private static int minValue(int a, int b, int c){
        if(a <= b && a <= c){
            return a;
        } else if(b <= a && b <= c){
            return b;
        } else {
            return c;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    ///How many ropes in a matrix -->2015,a
    public static int countRopes(int[][] mat){
        return countRopes(mat, 0);
    }

    private static int countRopes(int[][] mat, int j){
        if(j == mat.length){
            return 0;
        }

        int count = countRopes(mat, 0, j, Integer.MAX_VALUE);

        return countRopes(mat, j + 1) + count;
    }

    private static int countRopes(int[][] mat, int i, int j, int prevValue){
        if(i >= mat.length || i < 0 || j < 0 || j >= mat[i].length){
            return 0;
        }

        if(mat[i][j] >= prevValue){
            return 0;
        }

        if(i == mat.length - 1 && mat[i][j] < prevValue){
            return 1;
        }

        int temp = mat[i][j];


        int down = countRopes(mat, i + 1, j, temp);
        int downLeft = countRopes(mat, i + 1, j - 1, temp);
        int downRight = countRopes(mat, i + 1, j + 1, temp);

        mat[i][j] = temp;

        return down + downLeft + downRight;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //How many couples in the array have the distance k o(n)
    public static void printPairs(int[] a, int k){
        int high = 1, low = 0;

        while(high < a.length){
            int distance = a[high] - a[low];

            if(distance < k){
                high++;
            } else if(distance == k){
                System.out.println("Pair Found: " + a[low] + " ," + a[high]);
                low++;
                high++;
            } else {
                low++;
            }
        }

    }
///////////////////////////////////////////////////////////////////////////////////////////
    ///2015,a
    public static boolean splitTo3(int[] arr){
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int left = 0, right = arr.length -1;

        while(left < right){
            if(sum == 0){
                return true;
            }

            if(sum < 0){
                sum -= arr[left];
                left ++;
            }

            if(sum > 0){
                sum-= arr[right];
                right--;
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isSum2(int[] arr, int num){
        return isSum2(arr, num, 0, 0);
    }

    private static boolean isSum2(int[] arr, int num, int i, int count){
        if(i == arr.length && num != 0){
            return false;
        }

        if(num == 0 && count < 3){
            return true;
        }

        if(count == 3 || num < 0){
            return false;
        }

        return isSum2(arr, num - arr[i], i + 1, count + 1) || isSum2(arr, num, i + 1, 0);

    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Return the smallest sub array which sum is greater than k
    public static int smallestSub(int[] a, int k){
        int currentSum = 0, left = 0, minIndex = Integer.MAX_VALUE;

        for (int right = 0; right < a.length; right++) {

            currentSum += a[right];

            while(currentSum > k && left <= right){
                minIndex = Math.min(minIndex, right -  left + 1);
                currentSum -= a[left++];
            }

        }

        if(minIndex == Integer.MAX_VALUE){
            return a.length + 1;
        } else {
            return minIndex;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //Prince 2018
    public static int prince(int[][] drm, int i, int j){
        int findThePrince = prince(drm, i, j , drm[i][j], 1);

        if(findThePrince == Integer.MAX_VALUE){
            return -1;
        } else {
            return findThePrince;
        }
    }

    private static int prince(int[][] drm, int i, int j, int preValue, int count){
        if(!isValid2(drm,i,j) || drm[i][j] == -2){       //Check if step is within the matrix and was not visited before
            return Integer.MAX_VALUE;
        }

        if(drm[i][j] == - 1){       //If step is equal to -1 we found the villain
            return count;
        }

        int climb = Math.abs(drm[i][j] - preValue);
        int jump = drm[i][j] - preValue;

        if(climb > 2 || jump > 1){
            return Integer.MAX_VALUE;
        }

        int temp = drm[i][j];
        drm[i][j] = -2;

        //Recursive call for all directions
        int north = prince(drm, i - 1, j, temp, count + 1);
        int south = prince(drm, i + 1, j, temp, count + 1);
        int east = prince(drm, i , j + 1, temp, count + 1);
        int west = prince(drm, i, j - 1, temp, count + 1);

        drm[i][j] = temp;

        int min = Math.min(Math.min(north, south), Math.min(east, west));

        return min;
    }

    private static boolean isValid2(int[][] mat, int i, int j){
        return i >= 0 && j >= 0 && i < mat.length && j < mat[i].length;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //Search word in a word game -          2018,85
    public static void findWord(char[][] arr, String word){
        int[][] mat = new int[arr.length][arr.length];

        findWord(arr, mat, word, 0, 0);

        if(allZero(mat, 0, 0)){
            System.out.println("No Such Word");
        } else {
            printArray(mat);
        }
    }

    private static void findWord(char[][] arr, int[][] mat, String word, int i, int j){
        if(!isValid(mat, i, j)){
            return;
        }

        boolean wasFound = false;
        if(arr[i][j] == word.charAt(0)){
           wasFound = searchWord(arr, mat, word, i, j, 0);
        }

        if(wasFound){
            return;
        } else {
            findWord(arr, mat, word, i , j + 1);
            findWord(arr,mat,word,i + 1, j);
        }
    }

    private static boolean searchWord(char[][] arr, int[][] mat, String word, int i, int j, int k){
        if(i < 0 || j < 0 || i >= arr.length || j >= arr.length || arr[i][j] == 'X' || k >= word.length()){
            return false;
        }

        if(word.charAt(k) != arr[i][j]){
          return false;
        }

        if(k == word.length() - 1 && word.charAt(k) == arr[i][j]){
            mat[i][j] = k + 1;
            return true;
        }


        char temp = arr[i][j];
        arr[i][j] = 'X';

        mat[i][j] = k + 1;

       boolean up = searchWord(arr, mat, word, i - 1, j, k + 1);
       boolean down = searchWord(arr, mat, word, i + 1, j, k + 1);
       boolean right = searchWord(arr, mat, word, i, j + 1, k + 1);
       boolean left = searchWord(arr, mat, word, i, j - 1, k + 1);

        arr[i][j] = temp;


        if(up|| down || right || left){
            return true;
        } else {
            mat[i][j] = 0;
            return false;
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////
    ///Longest slope        2018,85
    public static int longestSlope(int[][] mat, int num){
        return longestSlope(mat, num, 0, 0);
    }

    private static int longestSlope(int[][] mat, int num, int i, int j){
        if(i >= mat.length){
            return 0;
        }

        if(j >= mat[i].length){
            return longestSlope(mat, num, i + 1, 0);
        }

        int slopeLength = longestSlope(mat, num, i, j, mat[i][j] + num, 0);

        return Math.max(slopeLength, longestSlope(mat, num, i, j + 1));
    }

    private static int longestSlope(int[][] mat, int num, int i, int j, int preValue, int count){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat.length || mat[i][j] == -1){
            return count;
        }

        if(mat[i][j] != preValue - num){
            return count;
        }

        int temp = mat[i][j];
        mat[i][j] = -1;
        count += 1;
        int up = longestSlope(mat, num, i - 1, j, temp, count);
        int down = longestSlope(mat, num, i + 1, j, temp, count);
        int right = longestSlope(mat, num, i, j + 1, temp, count);
        int left = longestSlope(mat, num, i, j - 1, temp, count);

        mat[i][j] = temp;

        int max = Math.max(Math.max(up, down), Math.max(right, left));

       return max;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //2020, 81
    public static int makeSum(int[] lengths, int k, int num){
        return makeSum(lengths, k, num, 0);
    }

    private static int makeSum(int[] arr, int k, int num, int i){
        if(i == arr.length || num < 0 || k < 0){
            return 0;
        }

        if(k == 0){
            return 1;
        }

        return makeSum(arr, k - arr[i], num - 1, i) + makeSum(arr, k, num, i + 1);

    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //2020, 81
    public static void minimumSubK(int[] arr, int k){
        int minSum = 0, left = 0, right = 0, currentSum = 0, leftIndex = 0, rightIndex = 0;

        while(right < arr.length - 1){
            while(right < k - 1){
                minSum += arr[right];
                currentSum = minSum;
                right++;
            }

            if(currentSum < minSum){
                minSum = currentSum;

                leftIndex = left;
                rightIndex = right;
            }
            currentSum += arr[right + 1];
            currentSum -= arr[left];

            right++;
            left++;
        }

        System.out.println("Minimum sub-array is: (" + leftIndex + "," + rightIndex + ")");
    }

    public static void minimumSubK2(int[] arr, int k){
        int minSum = 0, tempSum = 0, minL = 0, minR = 0, l =0, r =0;

        while(r < arr.length - 1){
            while(r < k - 1){
                minSum += arr[r++];
                tempSum = minSum;
            }

            if(tempSum < minSum){
                minSum = tempSum;
                minL = l;
                minR = r;
            }

            tempSum += arr[r+ 1];
            tempSum -= arr[l];

            r++;
            l++;
        }

        System.out.println(minL  + " " + minR);
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //2019, 84
    public static boolean sumPower3(int num){
       return sumPower3(num, 0);
    }

    private static boolean sumPower3(int num, int power){

        int value = num - pow(3, power);


        if(value == 0){
            return true;
        }

        if(value < 0){
            return false;
        }

        return sumPower3(num - pow(3, power), power + 1) || sumPower3(num, power + 1);
    }

    private static int pow(int a, int b){
        if(b == 0){
            return 1;
        }

        return a * pow(a, b - 1);
    }
///////////////////////////////////////////////////////////////////////////////////////////
    //2019, 84
    public static int average(int[] arr){   //O(n)
        double averageLeft, averageRight;
        int sumLeft = 0, sumRight = 0;

        for (int i = 0; i < arr.length; i++) {
            sumLeft += arr[i];
        }

        double maxDifference = 0;
        int indexToReturn = -1;

        for (int i = arr.length - 1; i > 0; i--) {
            averageLeft = calculateAverage(sumLeft - arr[i - 1], i + 1);

            sumRight += arr[i];
            averageRight = calculateAverage(sumRight, arr.length - i);

            if(Math.abs(averageLeft - averageRight) > maxDifference){
                maxDifference = Math.abs(averageLeft - averageRight);

                indexToReturn = i - 1;
            }
        }
        return indexToReturn;
    }

    private static double calculateAverage(int sum, int k){
        return sum / k;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static int arithmeticCount(int num){
        return arithmeticCount(num, num, 1);
    }

    private static int arithmeticCount(int sum,int num, int index){
        if(index > num || sum < 0){
            return 0;
        }

        if(sum == 0){
            return 1;
        }

        return arithmeticCount(sum - index, num, index + 1) + arithmeticCount(sum, num, index + 1);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    //2021, a
    public static int findMaxPrice(int[] prices, int n){
        return findMaxPrice(prices, n, 1);
    }

    private static int findMaxPrice(int[] prices, int n, int i){
        if(i >= prices.length || i > n){
            return 0;
        }

        if(n == 0){
            return prices[i];
        }

        int opt1 = prices[i] + findMaxPrice(prices, n - i, 1);
        int opt2 = findMaxPrice(prices, n, i + 1);

        return Math.max(opt1, opt2);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int count(int num){
        return count(num, 1);
    }

    private static int count(int num, int counter){
        if(num == 0){
            return 1;
        }

        if(counter > num){
            return 0;
        }

        int opt1 = count(num - counter, counter + 1);
        int opt2 = count(num, counter + 1);

        return opt1 + opt2;
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public static int lcs(String s, String t){
        return lcs(s, 0, t, 0);
    }

    private static int lcs(String s, int i, String t, int j){
        if(i >= s.length() || j >= t.length()){
            return 0;
        }

        int a = 0;
        if(s.charAt(i) == t.charAt(j)){
            a = 1 + lcs(s, i + 1, t, j + 1);
        }

        int opt1 = lcs(s, i + 1, t, j);
        int opt2 = lcs(s, i, t, j + 1);

        return Math.max(a, Math.max(opt1, opt2));
    }
///////////////////////////////////////////////////////////////////////////////////////////
    public static int longOrderNum(String s){
        return longOrderNum(s, 0, 0);
    }

    private static int longOrderNum(String s, int i, int count) {
        if(i == s.length()){
            return count;
        }

        int newCount = 0;
        
        if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
            if(i == 0 || s.charAt(i) > s.charAt( i - 1)){
                count += 1;
                newCount = count;
            } else {
                newCount = 1;
            }
        }

        return Math.max(count, longOrderNum(s, i + 1, newCount));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int maxEqualChar(String s){
        return maxEqualChar(s, 0, 0);
    }

    private static int maxEqualChar(String s, int i, int count){
        if(i == s.length()){
            return count;
        }

        int newCount = 0;
        if(i > 0 && s.charAt(i) == s.charAt(i - 1)){
            count += 1;
            newCount = count;
        } else {
            newCount = 1;
        }

        return Math.max(count, maxEqualChar(s, i + 1, newCount));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int minDiff(int[] arr){
        return minDiff(arr, 0, 0, 0);
    }

    private static int minDiff(int[] arr, int i, int sum1, int sum2){
        if(i == arr.length){
            return Math.abs(sum1 - sum2);
        }

        int move1 = minDiff(arr, i + 1, sum1 + arr[i], sum2);
        int move2 = minDiff(arr, i + 1, sum1, sum2 + arr[i]);

        return Math.min(move1, move2);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int longestFlat(int[] arr){
        if(arr.length == 0){
            return 0;
        } else {
            return longestFlat(arr, 0, Integer.MIN_VALUE);
        }
    }

    private static int longestFlat(int[] arr, int i, int maxCount){
       if(i == arr.length){
           return maxCount;
       }

       int flatSequence = lengthFlatSequence(arr, i, arr[i], arr[i], 1);

       if(flatSequence > maxCount){
           maxCount = flatSequence;
       }

       return longestFlat(arr, i + 1, maxCount);
    }

    private static int lengthFlatSequence(int[] arr, int i, int base, int secVal, int count){
        if(i == arr.length){
            return count;
        }

        if(secVal == base && Math.abs(base - arr[i]) == 1){
            return lengthFlatSequence(arr, i + 1, base, arr[i], count + 1);
        }

        if(arr[i] != base || arr[i] != secVal){
            return count;
        }

        return lengthFlatSequence(arr, i + 1, base, secVal, count + 1);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean splitEqualMulti(int[] a){
        return splitEqualMulti(a, 1, 1, 0);
    }

    private static boolean splitEqualMulti(int[] a, int sum1, int sum2, int i){
        if(i == a.length){
            return sum1 == sum2;
        }

        boolean temp1 = splitEqualMulti(a, sum1 * a[i], sum2, i + 1);
        boolean temp2 = splitEqualMulti(a, sum1, sum2 * a[i], i + 1);

        return temp1 || temp2;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int lengthOfLongestSubstring(String s){
        return lengthOfLongestSubstring(s, 0, "", 0);
    }

    private static int lengthOfLongestSubstring(String s, int i, String t, int count){
       if(i == s.length()){
           return count;
       }

       int temp1 = 0;

       if(!isFound(t, s.charAt(i), 0)){
           temp1 = lengthOfLongestSubstring(s, i + 1, t + s.charAt(i), count + 1);
       } else {
           return count;
       }

       int temp2 = lengthOfLongestSubstring(s, i + 1, "",0 );

       return Math.max(temp1, temp2);
    }

    private static boolean isFound(String s, char c, int i){
        if(i == s.length()){
            return false;
        }

        if(s.charAt(i) == c){
            return true;
        }

        return isFound(s, c, i + 1);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean isWay(int[] a){
        return isWay(a, 0);
    }

    private static boolean isWay(int[] a, int i){
        if(i == a.length - 1){
            return true;
        }
       if(i < 0 || i >= a.length || a[i] == -1){
           return false;
       }

       int temp = a[i];
       a[i] = -1;

       return isWay(a, i + temp) || isWay(a, i - temp);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int howManySorted(int n, int max){
        return howManySorted(n, max, 1);
    }

    private static int howManySorted(int length, int maxInt, int value){
       if(value > maxInt){
           return 0;
       }

       if(length == 0){
           return 1;
       }

       int temp1 = howManySorted(length - 1, maxInt, value);
       int temp2 = howManySorted(length, maxInt, value + 1);

       return temp1 + temp2;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static int edit(String s1, String s2){
        return edit(s1, s2, 0, 0);
    }

    private static int edit(String s1, String s2, int s1Index, int s2Index){
        if(s1Index == s1.length() && s2Index == s2.length()){
            return 0;
        }

        if(s1.length() == s1Index){
            return edit(s1, s2, s1Index, s2Index + 1) + 1;
        }

        if(s2.length() == s2Index){
            return edit(s1, s2, s1Index + 1, s2Index) + 1;
        }

        if(s1.charAt(s1Index) == s2.charAt(s2Index)){
            return edit(s1, s2, s1Index + 1, s2Index + 1);
        }

        int temp1 = edit(s1, s2, s1Index, s2Index + 1) + 1;
        int temp2 = edit(s1, s2, s1Index + 1, s2Index) + 1;

        return Math.min(temp1, temp2);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static int where(int[] vec){
        return where(vec, 0, 1, vec.length - 1);
    }

    private static int where(int[] vec, int left, int p, int right){
        if(p == vec.length){
            return -1;
        }

        if(sum(vec, left, p - 1) == sum(vec, p, right)){
            return p;
        }

        return where(vec, left, p + 1, right);
    }

    private static int sum(int[] vec, int lo, int hi){
        if(lo > hi){
            return 0;
        }

        return vec[lo] + sum(vec, lo + 1, hi);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
//    public static boolean isBalanced(int[] vec, int k){
//        return isBalanced(vec, k, 0, vec.length - 1);
//    }
//
//    private static boolean isBalanced(int[] vec, int k, int left, int right){
//
//    }
///////////////////////////////////////////////////////////////////////////////////////////
    public static int minDragons(int[][] maze){
        return minDragons(maze, 0, 0, 0);
    }

    private static int minDragons(int[][] maze, int i, int j, int count){
        if(i >= maze.length || i < 0 || j < 0 || j >= maze[i].length || maze[i][j] == 0 || maze[i][j] == -1){
            return Integer.MAX_VALUE;
        }

        if(maze[i][j] == 3){
            count += 1;
        }

        if(i == maze.length - 1 && j == maze[i].length - 1){
            return count;
        }

        int temp = maze[i][j];
        maze[i][j] = -1;

        int left = minDragons(maze, i, j + 1, count);
        int right = minDragons(maze, i, j - 1, count);
        int up = minDragons(maze, i - 1, j, count);
        int down = minDragons(maze, i + 1, j, count);

        maze[i][j] = temp;

        return Math.min(Math.min(left, right), Math.min(up, down));
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean covers(int[][] mat, int[] arr, int k){
        return true;
    }

    private static boolean allZero(int[] arr){
        return allZero(arr, 0);
    }

    private static boolean allZero(int[] arr, int i){
        if(i == arr.length){
            return true;
        }

        if(arr[i] != 0){
            return false;
        }

        return allZero(arr, i + 1);
    }

    private static boolean isFound(int[] arr, int n){
        return isFound(arr, n, 0);
    }

    private static boolean isFound(int[] arr, int n, int i){
        if(i == arr.length){
            return false;
        }
        if(arr[i] == n){
            arr[i] = 0;
            return true;
        }
        return isFound(arr, n, i + 1);
    }

    public static int minPath(char[][] minChess, int i, int j){
        if(i < 0 || j < 0 || i >= minChess.length || j >= minChess[i].length || minChess[i][j] == 'X'){
            return minChess.length * minChess.length;
        }

        if(minChess[i][j] == 'K'){
            return 0;
        }

        char temp = minChess[i][j];
        minChess[i][j] = 'X';

        int opt1 = 1 + minPath(minChess, i + 2, j - 1);
        int opt2 = 1 + minPath(minChess, i + 2, j + 1);
        int opt3 = 1 + minPath(minChess, i - 1, j + 2);
        int opt4 = 1 + minPath(minChess, i - 1, j + 2);
        int opt5 = 1 + minPath(minChess, i - 2, j + 1);
        int opt6 = 1 + minPath(minChess, i - 2, j - 1);
        int opt7 = 1 + minPath(minChess, i - 1, j - 2);
        int opt8 = 1 + minPath(minChess, i + 1, j - 2);

        minChess[i][j] = temp;

        int temp1 = Math.min(Math.min(opt1, opt2), Math.min(opt3, opt4));
        int temp2 = Math.min(Math.min(opt5, opt6), Math.min(opt7, opt8));

        return Math.min(temp1, temp2);
    }

    public static boolean isSum3(int[] a, int num){
        return isSum3(a, num, 0, 0);
    }

    private static boolean isSum3(int[] a, int num, int i, int count){
        if(num == 0){
            return true;
        }

        if(i >= a.length){
            return false;
        }

        if(count == 2){
            return isSum3(a, num, i + 1, 0);
        }

        boolean opt1 = isSum3(a, num - a[i], i + 1, count + 1);
        boolean opt2 = isSum3(a, num, i + 1, 0);

        return opt1 || opt2;
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
        int[] arr4 = {0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1};
        int[] arr5 = {5,21,3,27,12,24,7,6,4};
        int[] arr6 = {5,21,3,22,12,7,26,14};
        int[] arr7 = {5,15,3,22,7,27,14};
        int[] arr8 = {1,2,3,4,5,6,7,8,9};

        String s = "Java is a good language!";

        char[][] wordGame = {
                {'t','z','x','c','d'},
                {'s','h','a','z','x'},
                {'h','w','l','o','m'},
                {'o','r','n','t','m'},
                {'s','h','a','l','o'},
        };
        char[][] wordGame2 = {
                {'t', 'z', 'x', 'c', 'd'},
                {'o', 'r', 'n', 't', 'n'},
                {'s', 'p', 'a', 'z', 'x'},
                {'m', 'o', 'l', 'a', 'h'},
                {'a', 'b', 'r', 'i', 's'},
        };
        int[] isSum = {4,2,3,1};

        int[][] mat5 = {
                {0,0,0,10,0,0},
                {100,100,8,100,9,100},
                {100,6,100,100,100,7},
                {3,100,4,100,100,5},
                {1,2,100,100,100,2}
        };

        int[] arr10 = {-7,-3,0,1,3,5,12,14,17,19,25,30};
        int[] arr11 = {-8,-7,-5,-3,-2,1,-1};
        int[] arr12 = {5,4,2,1,3};

//        findWord(wordGame, "shalom");
//        int[] arr9 = {9,4,1,5};
//        int[] arr10 = {5,4,9,1};
        int[] arr13 = {1,4,13,6,0,19};

        int[][] princeMat = {
                {2,0,1,2,3},
                {2,3,5,5,4},
                {2,5,6,8,7},
                {2,4,7,2,4},
                {-1,4,3,1,2}
        };

        int[][] slopeMat = {
                {3,13,15,28,30},
                {55,54,53,27,26},
                {54,12,52,51,50},
                {50,10,8,53,11}
        };

        int[] checkSum = {2, 5, 10, 20, 50};

//        System.out.println(longestSlope(slopeMat, 50));

        int[] minSubArray = {10, 4, 2 ,5, 6, 3, 8, 1, 5, 9};

//        minimumSubK(minSubArray, 2);

        int[] average = {5, 7, -2, 10};

//        System.out.println(average(average));

//        System.out.println(arithmeticCount(7));

        int[] findMax = {0, 4, 3, 10, 9, 10, 17, 17, 20};

//        System.out.println(findMaxPrice(findMax, 8));

//        System.out.println(count(7));

//        String s1 = "abcdefgh";
//        String s2 = "bcwxftjg";
//        String s3 = "xwd";
//        String s4 = "kmns";
//        String s5 = "x12y3348";
//        String s6 = "xxxyyyyz";

//        System.out.println(maxEqualChar(s6));

//        int[] isPer1 = {1,2,3,4};
//        int[] isPer2 = {3,2,4,5};
//
//        System.out.println(isPermutation2(isPer1, isPer2));

        int[] minDi = {1, 2, 7, 17, 6};

        int[] flat = {4,5,6,5,4,3};

        int[] split = {2, 4, 6, 2, 3, 4};

        String long1 = "abcabcbb";

        int[] way = {1, 4, 3, 1, 2, 4, 3};
        int[] way2 = {2, 4, 1 ,6, 4, 2, 4, 3, 5};

        String edit1 = "sunday"; String edit2 = "saturday";

        int[] where = {5, 6, 1, 2, 8};

        int[][] maze1 = {
                {1, 1, 3, 3},
                {3, 0, 1, 1},
                {3, 0, 0, 1},
                {1, 3, 3, 1}
        };

        int[][] maze2 = {
                {1, 1, 3, 3},
                {3, 0, 1, 1},
                {3, 0, 0, 0},
                {1, 3, 3, 1}
        };

        int[][] maze3 = {
                {1, 0, 3, 3},
                {3, 0, 1, 1},
                {3, 0, 0, 0},
                {0, 3, 3, 1}
        };

        char[][] minChessBoard1 = {
                {'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O'},
                {'O', 'O', 'K', 'O'},
                {'H', 'O', 'O', 'O'}
        };

        char[][] minChessBoard2 = {
                {'O', 'O', 'O', 'O'},
                {'O', 'O', 'K', 'O'},
                {'O', 'O', 'O', 'O'},
                {'H', 'O', 'O', 'O'}
        };
        char[][] minChessBoard3 = {
                {'O', 'O', 'K', 'O'},
                {'O', 'O', 'H', 'O'},
                {'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O'}
        };

//        System.out.println(minPath(minChessBoard3, 1, 2));

        int[] sum = {4, 2, 3, 1};

        System.out.println(isSum3(sum, 6));
    }
}
