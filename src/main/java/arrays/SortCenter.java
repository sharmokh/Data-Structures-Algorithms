package main.java.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//    Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up
//    to a target - 30.
//
//    Conditions:
//        - You will pick exactly 2 numbers.
//        - You cannot pick the same element twice.
//        - If you have muliple pairs, select the pair with the largest number.
//
//    Example 1:
//    Input: nums = [1, 10, 25, 35, 60], target = 90
//    Output: [2, 3]
//    Explanation:
//        nums[2] + nums[3] = 25 + 35 = 60 = 90 - 30
//
//    Example 2:
//    Input: nums = [20, 50, 40, 25, 30, 10], target = 90
//    Output: [1, 5]
//    Explanation:
//        nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30
//        nums[1] + nums[5] = 50 + 10 = 60 = 90 - 30
//        You should return the pair with the largest number.

public class SortCenter {

    public static void main(String[] args) {

        SortCenter sc = new SortCenter();

        int[] nums = {20, 50, 40, 25, 30, 10};
        System.out.println(Arrays.toString(sc.hashMapMethod(nums, 90)));

    }

    // HashMap Method
    // - map value as key and index as value into hashmap
    // - loop through array, calculate compliment and see if compliment is in hashmap
    // - if in hashmap, initialize or compare set of values to current max set
    // - add current value and index to map
    // O(n) Time Complexity
    // O(n) Space Complexity
    public int[] hashMapMethod(int[] nums, int target) {

        // HashMap stores value as key and index as value Map<Value, Index>
        Map<Integer, Integer> map = new HashMap<>();
        int[] max = new int[] {-1, -1};
        target -= 30;

        // Loop through each value in nums
        for (int i = 0; i < nums.length; i++) {

            // n is current number
            // m is complimentary number
            int n = nums[i];
            int m = target - n;

            // if value m is in map, either initialize max array to j, i or compare max of n & m and current max indices
            if (map.containsKey(m)) {
                int j = map.get(target-n);
                if (max[0] == -1) {
                    max = new int[] {j, i};
                }
                if (Math.max(n, m) > Math.max(nums[max[0]], nums[max[1]])) {
                    max = new int[] {j, i};
                }
            }

            // Hash and store value as key and index as value into the map
            map.put(n, i);
        }

        return max;
    }
}
