package main.java.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Given a List of Strings replace '&' with "and".
 *
 * For example:
 *
 * Romeo & Juliet
 * Platoon
 * Kate & Leopold
 * Poltergeist III
 * Harold and Kumar Go to White Castle
 * Clerks II
 *
 * becomes:
 *
 * Romeo and Juliet
 * Platoon
 * Kate and Leopold
 * Poltergeist III
 * Harold and Kumar Go to White Castle
 * Clerks II
 *
 */

public class NormalizeList {

    public static void main(String[] args) {

        // Constructor
        NormalizeList nl = new NormalizeList();

        List<String> list = Arrays.asList(
                "Romeo & Juliet",
                "Platoon",
                "Kate & Leopold",
                "Poltergeist III",
                "Harold and Kumar Go to White Castle",
                "Clerks II"
        );

        List<String> normalizeList = nl.normalizeList(list);
        normalizeList.forEach(System.out::println);

        list = Arrays.asList(
                "&&&&",
                "& Romeo",
                "Juliet &",
                "Romeo & Juliet & George",
                "&",
                ""
        );
        nl.normalizeList(list).forEach(System.out::println);

    }

    public List<String> normalizeList(List<String> list) {
        return list.parallelStream()
                .map(s -> s.replaceAll("&", "and"))
                .collect(Collectors.toList());
    }

}
