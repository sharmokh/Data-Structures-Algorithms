package main.java.arrays;

// Needs Comments

import java.util.*;

//    Movies on Flight
//
//    You are on a flight and wanna watch two movies during this flight.  You are given int[] movie_duration which
//    includes all the movie durations.  You are also given the duration of the flight which is d in minutes.  Now, you
//    need to pick two movies and the total duration of the two movies is less than or equal to (d - 30min).  Find the
//    pair of movies with the longest total duration. If multiple found, return the pair with the longest movie.
//
//    Example:
//    Input: movie_duration = [90, 85, 75, 60, 120, 150, 125], d = 250
//    Output: [90, 125]
//    Explanation: 90min + 125min = 215 is the maximum number within 220 (250min - 30min)

public class ClosestSum {

    public static void main(String[] args) {
        int[] movieDurations = {90, 85, 75, 60, 120, 150, 125};
        System.out.println(Arrays.toString(twoPointerMethod(movieDurations, 250)));
    }

    // Two Pointer Method
    public static int[] twoPointerMethod(int[] movieDurations, int minutes) {
        int[] maxMovies = new int[] {-1, -1};
        Arrays.sort(movieDurations);
        int i = 0, j = movieDurations.length - 1;
        while (i < j) {
            int sum = movieDurations[i] + movieDurations[j];
            if (sum == minutes - 30) {
                return new int[] {movieDurations[i], movieDurations[j]};
            }
            if (sum > minutes - 30) {
                j--;
            } else {
                if (sum > maxMovies[0] + maxMovies[1]) {
                    maxMovies = new int[] {movieDurations[i], movieDurations[j]};
                }
                i++;
            }
        }
        return maxMovies;
    }

}
