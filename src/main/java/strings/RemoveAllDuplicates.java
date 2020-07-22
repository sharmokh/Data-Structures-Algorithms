package main.java.strings;

public class RemoveAllDuplicates {

    public static void main(String[] args) {
        RemoveAllDuplicates rad = new RemoveAllDuplicates();
        System.out.println(rad.removeDuplicates("yfttttfbbbbnnnnffbgffffgbbbbgssssgthyyyy", 4));
    }

    public String removeDuplicates(String s, int k) {
        if (s.length() < k) { return s; };
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int j = i + 1;
            while (j < s.length() && c == s.charAt(j) && j-i < k) {
                j++;
            }
            if (j-i == k) {
                return removeDuplicates(s.substring(0, i) + s.substring(j), k);
            }
            i = j;
        }
        return s;
    }

}
