package com.hackerrank.interview.preparation.kit.sorting.fraudulent.activity.notifications;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        Map<Integer, Integer> map = new TreeMap<>();

        int i = 0;
        int result = 0;
        for (; i < d; ++i) {
            map.put(expenditure[i], map.getOrDefault(expenditure[i], 0) + 1);
        }

        for (i = d; i < expenditure.length; ++i) {
            result += getNotice(expenditure[i], getMedianX2(map, d));

            map.put(expenditure[i], map.getOrDefault(expenditure[i], 0) + 1);
            map.put(expenditure[i-d], map.get(expenditure[i-d]) - 1);
        }

        return result;
    }
    static int getNotice(int value, int control) {
        return (value >= control) ? 1 : 0;
    }

    static int getMedianX2(Map<Integer, Integer> map, int d) {
        int result = 0;
        int count = 0;
        int flag = 0;
        int extra = 1 - (d % 2);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
            if ( (flag == 0) && (count > ((d / 2) - extra)) ) {
                result += entry.getKey();
                flag++;
            }

            if ( (flag == 1) && (count > (d / 2)) ) {
                result += entry.getKey();
                flag++;
            }

            if (flag == 2)
                return result;
        }
        return result;
    }



    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
