package main.java.arrays;

import java.util.*;

//    Two Sum - Unique Pairs
//
//    Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to
//    target. Return the number of pairs.
//
//    Example 1:
//    Input: nums = [1, 1, 2, 45, 46, 46], target = 47
//    Output: 2
//    Explanation:
//        1 + 46 = 47
//        2 + 45 = 47
//
//    Example 2:
//    Input: nums = [1, 1], target = 2
//    Output: 1
//    Explanation:
//        1 + 1 = 2
//
//    Example 3:
//    Input: nums = [1, 5, 1, 5], target = 6
//    Output: 1
//    Explanation:
//        [1, 5] and [5, 1] are considered the same.

public class UniquePairsSum {

    public static void main(String[] args) {
        UniquePairsSum ups = new UniquePairsSum();
        int[] nums = {1, 1, 2, 45, 46, 46};
        System.out.println(ups.setMethod(nums, 47));
        nums = new int[] {1,5,1,5};
        System.out.println(ups.setMethod(nums, 6));
    }

    // Two Pointer Method
    // - sort array and moving from both left and right count number of times sum of the elements equals target
    // O(nlogn) Time Complexity due to Sorting
    // O(1) Space Complexity
    public int twoPointerMethod(int[] nums, int target) {
        int count = 0;
        int l = 0, r = nums.length - 1;
        Arrays.sort(nums);
        while (l < r) {
            int sum = nums[l] + nums[r];
            if (sum == target) {
                count++;
                while (l < r && nums[l] == nums[l+1]) l++;
                l++; r--;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return count;
    }

    // Set Method
    // - for each element (n), determine the difference (d) from target and check if d was already seen (or in the set)
    // - add both d and n to the results if d is in the set, then add n to the set
    // O(n) Time Complexity
    // O(n) Space Complexity
    public int setMethod(int[] nums, int target) {

        Set<Integer> set = new HashSet<>();
        Set<Integer> results = new HashSet<>();

        for (int n : nums) {

            // calculate difference (d) from target
            int d = target - n;

            // add both d and n if d is already in the set
            if (set.contains(d)) {
                results.add(n);
                results.add(d);
            }
            set.add(n);
        }

        // take results size, add 1 (in case odd) and divide by 2 (because we stored the pair)
        return (results.size() + 1) / 2;
    }

}
