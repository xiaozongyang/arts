package io.xiaozy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem: https://leetcode-cn.com/problems/permutations-ii/
 * @author xiaozongyang
 */
public class PermuteUnique2 {

    public static void main(String[] args) {
        PermuteUnique2 permuteUnique2 = new PermuteUnique2();

        int[] nums1 = new int[]{1, 1, 2};

        List<List<Integer>> perm1 = permuteUnique2.permuteUnique(nums1);

        printPermutation(perm1);

        int[] nums2 = new int[]{1, 2, 3};

        List<List<Integer>> perm2 = permuteUnique2.permuteUnique(nums2);

        printPermutation(perm2);

    }

    private static void printPermutation(List<List<Integer>> ret) {
        for (List<Integer> perm : ret) {
            for (Integer e : perm) {
                System.out.print(e);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }

        if (nums.length == 1) {
            List<Integer> l = new ArrayList<Integer>(1);
            l.add(nums[0]);
            return Collections.singletonList(l);
        }

        Set<List<Integer>> ret = new HashSet<List<Integer>>(fact(nums.length));
        List<Integer> path = new ArrayList<Integer>(nums.length);
        // boolean default to false
        boolean[] selected = new boolean[nums.length];

        permute(ret, nums, path, selected);

        return new ArrayList<List<Integer>>(ret);
    }

    private void permute(Set<List<Integer>> ret, int[] nums, List<Integer> path, boolean[] selected) {
        if (path.size() == nums.length) {
            if (!ret.contains(path)) {
                ret.add(new ArrayList<Integer>(path));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // select
            if (selected[i]) {
                continue;
            }

            path.add(nums[i]);
            selected[i] = true;

            permute(ret, nums, path, selected);

            // unselect
            path.remove(path.size() - 1);
            selected[i] = false;
        }
    }

    private int fact(int n) {
        if (n == 0) {
            return 0;
        }
        int f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
        }
        return f;
    }
}
