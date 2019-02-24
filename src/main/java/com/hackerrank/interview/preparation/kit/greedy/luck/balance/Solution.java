package com.hackerrank.interview.preparation.kit.greedy.luck.balance;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        int result = 0;
        Queue<Integer> imp = new PriorityQueue<>();
        for (int i = 0; i < contests.length; ++i) {
            if (contests[i][1] == 0) {
                result += contests[i][0];
                continue;
            }

            if (imp.size() < k) {
                imp.add(contests[i][0]);
                continue;
            }

            if ( (imp.size() > 0) && (imp.peek() < contests[i][0]) ) {
                result -= imp.poll();
                imp.add(contests[i][0]);
                continue;
            }

            result -= contests[i][0];
        }

        while (imp.peek() != null) {
            result += imp.poll();
        }
        return result;
    }



    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
