package com.example;


import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {

    public Map<String, Integer> count(String stringToCOuntWord){
        stringToCOuntWord = stringToCOuntWord.replaceAll(",","");
        ArrayList<String> stringList = new ArrayList(Arrays.asList(stringToCOuntWord.split(" ")));
        Map<String, Integer> counts = stringList.parallelStream().
                collect(Collectors.toConcurrentMap(
                        word -> word, word -> 1, Integer::sum));
        return counts;
    }

}
