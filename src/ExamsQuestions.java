public class ExamsQuestions {
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static boolean isSum(int[] a, int num) {
        return isSum(a, num, 0, 0);
    }

    private static boolean isSum(int[] a, int num,int pre, int i) {
        if(num == 0) {
            return true;
        }

        if(i >= a.length) {
            return false;
        }

        if(num < 0) {
            return false;
        }

        boolean temp1 = false;
        boolean temp2 = false;
        boolean temp3 = false;

        if(pre + 1 == i) {
            temp1 = isSum(a, num - a[i], i, i + 2);
        } else {
            temp2 = isSum(a, num - a[i], i, i + 1);
            temp3 = isSum(a, num, pre, i+1);
        }

        return temp1 || temp2 ||temp3;

    }

////////////////////////////////////////////////////////////////////////////////////

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

            if(halfSum * 2 == sum) {
                return i;
            } else if(halfSum * 2 > sum) {
                return Integer.MIN_VALUE;
            }
        }

        return Integer.MIN_VALUE;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    //finds if there is a following two values in the array which their sum is equal to x
    public static boolean findX(int[] arr, int x) {
        int low = 0, high = arr.length-1, mid = 0;

        while(low < high) {
            mid = (low + high) / 2;

            if(arr[mid] + arr[mid + 1] == x) {
                return true;
            } else if(arr[mid] + arr[mid - 1] == x) {
                return true;
            }

            if(arr[mid] + arr[mid + 1] > x) {
                high = mid - 1;
            } else if(arr[mid] + arr[mid + 1] < x) {
                low = mid + 1;
            }
        }
        return false;
    }

///////////////////////////////////////////////////////////////////////////////////////////

    ///Function that will return the missing value from an arithmetic sequence
    public static int arithmeticMissingValue(int[] arr) {
        int arrLength = arr.length;

        int diff = (arr[arrLength - 1] - arr[0]) / arrLength;

        int low = 0, high = arrLength - 1;

        return arithmeticMissingValue(arr, low, high, diff);
    }

    private static int arithmeticMissingValue(int[] arr,int low, int high,int diff) {
        int mid = 0;

        while(low <= high) {
            mid = (low + high) / 2;

            if(arr[mid + 1] - arr[mid] != diff) {
                return arr[mid] + diff;
            }

            if(mid > 0 && arr[mid] - arr[mid - 1] != diff) {
                return arr[mid] - diff;
            }

            if(arr[mid] == arr[0] + mid * diff) {
                low = mid + 1;
            } else {
                high = mid -1;
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
        if(i == matt.length) {
            return 0;
        }

        if(j == matt[i].length) {
            return longestWorm(matt, i + 1, 0);
        }

        int r1 = longestWorm(matt, i + 1 ,j , matt[i][j]);
        int r2 = longestWorm(matt, i -1 ,j , matt[i][j]);
        int r3 = longestWorm(matt, i ,j +1 , matt[i][j]);
        int r4 = longestWorm(matt, i ,j - 1 , matt[i][j]);

        int r = Math.max(Math.max(r1, r2), Math.max(r3, r4)) + 1;

        return Math.max(r, longestWorm(matt, i, j + 1));
    }

    private static int longestWorm(int[][] matt, int i, int j, int prevCell) {
        if(i < 0 || i == matt.length || j < 0 || j == matt[i].length) {
            return 0;
        }

        if(matt[i][j] != prevCell + 1) {
            return 0;
        }

        int r1 = longestWorm(matt, i + 1 ,j, matt[i][j]);
        int r2 = longestWorm(matt, i -1 ,j , matt[i][j]);
        int r3 = longestWorm(matt, i ,j +1 , matt[i][j]);
        int r4 = longestWorm(matt, i ,j - 1 , matt[i][j]);
        int r = Math.max(Math.max(r1, r2), Math.max(r3, r4));

        return r + 1;

    }


///////////////////////////////////////////////////////////////////////////////////////////

    ///Find the shortest path in a matrix
    public static int shortestPath(int[][] matt) {
        return shortestPath(matt, 0, 0, 0, 0);
    }

    private static int shortestPath(int[][] matt, int i, int j, int prevValue, int count) {
        if(i < 0 || j < 0 || i == matt.length || j == matt[i].length || matt[i][j] <= prevValue) {
            return Integer.MAX_VALUE;
        }

        if(i == matt.length - 1 && j == matt[i].length - 1) {
            return count+1;
        }

        int option1 =  shortestPath(matt, i + 1, j, matt[i][j], count + 1);
        int option2 =  shortestPath(matt, i - 1, j, matt[i][j], count + 1);
        int option3 =  shortestPath(matt, i, j + 1, matt[i][j], count + 1);
        int option4 = shortestPath(matt, i, j - 1, matt[i][j], count + 1);

        return Math.min(Math.min(option1, option2), Math.min(option3, option4));
    }


    //////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {

        int[][] matt1 = {
                {3,13,15,28,30},
                {50, 51,52,29,30},
                {51,10,53,54,55},
                {53,12,14,53,11}
        };

        int [][] matt2 = {
                {3,13,15,28,30},
                {40, 51,52,29,30},
                {28,10,53,54,53},
                {53,12,55,53,60},
                {70,62,56,20,80},
                {80,81,90,95,100}
        };

        System.out.println(shortestPath(matt2));

    }
}
