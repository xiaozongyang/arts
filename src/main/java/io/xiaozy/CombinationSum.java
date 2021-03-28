package io.xiaozy;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem: https://leetcode-cn.com/problems/combination-sum-iii/
 * @author xiaozongyang
 */
public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        List<List<Integer>> result3_7 = sum.combinationSum3(3, 7);
        System.out.println("done" + result3_7);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        Args args = new Args(k, n);

        dfs(args);

        return args.combinations;
    }

    private void dfs(Args args) {
        if (args.k == args.selected.size()) {
            if (args.sum == args.n) {
                args.combinations.add(new ArrayList<Integer>(args.selected));
            }
            return;
        }

        for (int i = args.lastSelected + 1; i <= 9; i++) {
            if (args.sum + i > args.n) {
                continue;
            }
            args.selected.add(i);
            args.sum += i;
            args.lastSelected = i;

            dfs(args);

            args.sum -= i;
            args.selected.remove(args.selected.size() - 1);
            args.lastSelected = args.selected.isEmpty() ? 0 : args.selected.get(args.selected.size() - 1);
        }
    }

    public static class Args {
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        List<Integer> selected= new ArrayList<Integer>(10);
        int k;
        int n;
        int sum = 0;
        int lastSelected = 0;

        public Args(int k, int n) {
            this.k = k;
            this.n = n;
        }
    }
}
