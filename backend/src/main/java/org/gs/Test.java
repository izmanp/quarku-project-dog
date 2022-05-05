package org.gs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a = new LinkedList<>();
        ArrayList<String> a2 = new ArrayList<>();
       // a2 = new LinkedList<>();
        a2.add("fd");
        a2.add("0");
        a2.add("1");
        a2.add("123");
        a2.add("543");

        a2.stream().filter(x -> {
            try {
                if (Integer.valueOf(x) % 5 != 0) {
                    return true;
                }
            } catch (Exception ignored) {
            }
            return false;
        }).forEach(System.out::println);
//         IntStream.range(0, a2.size())
//                .filter(n -> n % 5 == 0)
//                .mapToObj(a2::get)
//                .forEach(System.out::print);


    }



}
