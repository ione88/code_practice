package com.hackerrank.interview.preparation.kit.dynamic.programming.abbreviation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        if (a.equals(b))
            return "YES";
        String aUp = a.toUpperCase();
        int[][] maxSubLen = new int[a.length() + 1][b.length() + 1];
        int i = 1;
        for (char aCh : aUp.toCharArray()) {
            int j = 1;
            for (char bCh : b.toCharArray()) {
                maxSubLen[i][j] =  ( a.charAt(i - 1) == aCh )
                                   ? (aCh == bCh)
                                        ? maxSubLen[i - 1][j - 1] + 1
                                        : 0
                                   : (aCh == bCh)
                                        ? Math.max(maxSubLen[i - 1][j - 1] + 1, maxSubLen[i - 1][j])
                                        : maxSubLen[i - 1][j];

                ++j;
            }
            ++i;
        }
        return ( maxSubLen[a.length()][b.length()] != b.length() ) ? "NO" : "YES";
    }



    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}