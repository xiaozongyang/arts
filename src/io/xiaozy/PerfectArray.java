package io.xiaozy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 * @author xiaozongyang
 */
public class PerfectArray {

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            System.out.println(countArrangement(i));
        }
    }

    public static int countArrangement(int n) {
        int []selected = new int[n];
        int ls = 0;
        Result result = new Result();

        backtrace(selected, ls, n, result);
        return result.count;
    }

    public static void backtrace(int[] selected, int ls, int n, Result result) {
        if (ls == selected.length) {
            result.count += 1;
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (contains(selected, j) || !isPerfect(ls + 1, j)) {
                continue;
            }
            // select
            selected[ls] = j;
            backtrace(selected, ls + 1, n, result);

            // unselect
            selected[ls] = 0;
        }
    }

    private static boolean contains(int[] selected, int j) {
        for (int s: selected) {
            if (s == j) {
                return true;
            }
        }
        return false;
    }

    private static void swap(int[] candidates, int i, int j) {
        int tmp = candidates[j];
        candidates[j] = candidates[i];
        candidates[j] = tmp;
    }

    static boolean isPerfect(int i, int n) {
        return i % n == 0 || n % i == 0;
    }

    public static class Result {
        public int count = 0;
    }
}