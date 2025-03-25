
public class Comparision{


    // javaapplication to Describe a method for finding both the maximum and minimum of n numbers using

//fewer than 3n/2 comparisons.

    public   static class Pair {
        int min;
        int max;
    }

    static Pair findMaxMin(int[] arr, int start, int end) {
        Pair result = new Pair();

        // If there is only one element
        if (start == end) {
            result.min = arr[start];
            result.max = arr[start];
            return result;
        }

        // If there are two elements, compare and assign
        if (end == start + 1) {
            result.min = Math.min(arr[start], arr[end]);
            result.max = Math.max(arr[start], arr[end]);
            return result;
        }

        // Divide the array into two halves
        int mid = (start + end) / 2;
        Pair leftPair = findMaxMin(arr, start, mid);
        Pair rightPair = findMaxMin(arr, mid + 1, end);

        // Combine results from both halves
        result.min = Math.min(leftPair.min, rightPair.min);
        result.max = Math.max(leftPair.max, rightPair.max);

        return result;
    }
    public static void main(String[] args) {
           int[] numbers = {3, 1, 9, 4, 6, 7, 2, 8, 5};

        Pair result = findMaxMin(numbers, 0, numbers.length - 1);

        System.out.println("Minimum: " + result.min);
        System.out.println("Maximum: " + result.max);
    }

}
/*
 B.)Use binary search to find the position of the first 0 in each row.
The index of this position gives the count of 1's in the row.
 Perform binary search on each row and sum the counts of 1's.
 Binary search in one row takes (logn)

 For n rows, the total time is O(nlogn).
