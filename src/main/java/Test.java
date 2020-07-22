package main.java;

public class Test {


    public static void main(String[] args) {
        boolean a = false;
        boolean b = true;
        if (a) {
            System.out.println("A");
        } else if (a && b) {
            System.out.println("A && B");
        } else {
            if (!b) {
                System.out.println("NotB");
            } else {
                System.out.println("Else");
            }
        }
    }
}
