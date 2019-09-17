package main.java.problems;

import java.util.*;

//    Favorite Genres
//
//    Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user
//    has listened to as values.  Also given a map Map<String, List<String>> songGenres, with song genre as keys and a
//    list of all the songs within that genre as values. The song can only belong to only one genre.  The task is to
//    return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's
//    favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if
//    he/she has listened to the same number of songs per each of the genres.
//
//    Example 1:
//    Input:
//        userSongs = {
//            "David": ["song1", "song2", "song3", "song4", "song8"],
//            "Emma":  ["song5", "song6", "song7"]
//            },
//        songGenres = {
//            "Rock":    ["song1", "song3"],
//            "Dubstep": ["song7"],
//            "Techno":  ["song2", "song4"],
//            "Pop":     ["song5", "song6"],
//            "Jazz":    ["song8", "song9"]
//            }
//    Output: {
//        "David": ["Rock", "Techno"],
//        "Emma":  ["Pop"]
//        }
//    Explanation:  David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite genres.  Emma has 2 Pop and 1
//                  Dubstep song. Pop is Emma's favorite genre.
//
//    Example 2:
//    Input:
//        userSongs = {
//            "David": ["song1", "song2"],
//            "Emma":  ["song3", "song4"]
//            },
//        songGenres = {}
//    Output: {
//        "David": [],
//        "Emma":  []
//        }

public class FavoriteGenres {

    public static void main(String[] args) {

        Map<String, List<String>> userSongs = new HashMap<>() {{
            put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
            put("Emma", Arrays.asList("song5", "song6", "song7"));
        }};
        Map<String, List<String>> songGenres = new HashMap<>() {{
            put("Rock", Arrays.asList("song1", "song3"));
            put("Dubstep", Arrays.asList("song7"));
            put("Techno", Arrays.asList("song2", "song4"));
            put("Pop", Arrays.asList("song5", "song6"));
            put("Jazz", Arrays.asList("song8", "song9"));
        }};

        FavoriteGenres fg = new FavoriteGenres();
        Map<String, List<String>> userGenres = fg.mapMethod(userSongs, songGenres);
        for (String user : userGenres.keySet()) {
            System.out.println(user + ": " + userGenres.get(user).toString());
        }

    }

    // Map Method
    // - reverse map of song genres to be able to find the genres of specific songs
    // - for each user, get list of songs and for each song, get list of genres and track each genres frequency
    // O(U*S*G) Time Complexity
    // O(S*G + U*G) Space Complexity
    public Map<String, List<String>> mapMethod(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {

        // reverse map song genres
        Map<String, List<String>> songToGenre = reverseMap(songGenres);
        Map<String, List<String>> userGenres = new HashMap<>();

        for (String user : userSongs.keySet()) {
            List<String> songs = userSongs.get(user);

            // map to track each genres count and list to track most frequent genres
            Map<String, Integer> genreCount = new HashMap<>();
            List<String> favoriteGenres = new ArrayList<>();
            int max = 0;

            for (String song : songs) {
                List<String> genres = songToGenre.get(song);
                for (String genre : genres) {

                    // initialize the genre count to 1 or increase by 1
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);

                    // if count is equal to the max, add genre to list of favorites
                    // if count is greater, clear the favorites list and start it with the current max genre
                    int count = genreCount.get(genre);
                    if (count == max) {
                        favoriteGenres.add(genre);
                    } else if (count > max) {
                        favoriteGenres.clear();
                        favoriteGenres.add(genre);
                        max = count;
                    }
                }
            }

            // map user to all his favorite genres
            userGenres.put(user, favoriteGenres);
        }

        return userGenres;
    }

    public Map<String, List<String>> reverseMap(Map<String, List<String>> songGenres) {
        Map<String, List<String>> songToGenre = new HashMap<>();
        for (String genre : songGenres.keySet()) {
            List<String> songs = songGenres.get(genre);
            for (String song : songs) {
                if (!songToGenre.containsKey(song)) {
                    songToGenre.put(song, new ArrayList<>());
                }
                songToGenre.get(song).add(genre);
            }
        }
        return songToGenre;
    }

}
