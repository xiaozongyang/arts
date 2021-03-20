package io.xiaozy;

/**
 * Problem: https://leetcode-cn.com/problems/beautiful-arrangement/
 * Solution Reference: https://mp.weixin.qq.com/s/nMUHqvwzG2LmWA9jMIHwQQ
 * @author xiaozongyang
 */
public class PerfectArray {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 15; i++) {
            System.out.println(countArrangement(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("cost=" + (end - start) + "ms");
    }

    public static int countArrangement(int n) {
        int []selected = new int[n];
        int ls = 0;

        int []candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = i + 1;
        }

        Result result = new Result();

        backtrace(1, result, selected, ls, candidates, n);
        return result.count;
    }

    public static void backtrace(int index, Result result, int[] selected, int ls, int[] candidates,
        int lc) {
        if (ls == selected.length) {
            result.count += 1;
            return;
        }
        for (int i = 0; i < lc; i++) {
            if (isNotPerfect(index, candidates[i])) {
                continue;
            }

            selected[ls] = candidates[i];
            swap(candidates, i, lc - 1);

            backtrace(index + 1, result, selected, ls + 1, candidates, lc - 1);

            swap(candidates, i, lc - 1);
        }
    }

    private static void swap(int[] candidates, int i, int j) {
        int tmp = candidates[j];
        candidates[j] = candidates[i];
        candidates[i] = tmp;
    }

    static boolean isNotPerfect(int i, int n) {
        return i % n != 0 && n % i != 0;
    }

    public static class Result {
        public int count = 0;
    }
}
