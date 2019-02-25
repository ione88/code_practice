package com.hackerrank.interview.preparation.kit.dynamic.programming.candies;

import java.io.*;
import java.util.*;

import static java.util.stream.IntStream.rangeClosed;

public class Solution {

    // Complete the activityNotifications function below.
    static long candies(int n, int[] arr) {
        int[] res = new int[n];
        Arrays.setAll(res, i -> ((i > 0) && (arr[i - 1] < arr[i])) ? (res[i - 1] +  1) : 1);

        int[] arrRev = rangeClosed(1, n).map(i -> arr[n - i]).toArray();
        int[] resRev = rangeClosed(1, n).map(i -> res[n - i]).toArray();

        Arrays.setAll(resRev, i -> ((i > 0) && (arrRev[i - 1] < arrRev[i])) ? Math.max(resRev[i - 1] +  1, resRev[i]) : resRev[i]);
        return rangeClosed(1, n).mapToLong(i -> resRev[i - 1]).sum();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
