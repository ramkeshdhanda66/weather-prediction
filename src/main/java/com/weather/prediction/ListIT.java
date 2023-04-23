package com.weather.prediction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListIT {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList("gl", "wipro", "infosys",null)
                .stream()
                .filter(str->null != str && str.length()>4)
                .map(s -> s.length())
                .collect(Collectors.toList());
//                .forEach(li-> System.out.println(li));
        System.out.print(list);

    }

}
