package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WordCounter {

    @Autowired
    WordCount wordCount;


    public Map<String, Integer> count(String stringToCountWord){
        System.out.println("----------------------------> old String is: -> "+stringToCountWord);
        stringToCountWord = stringToCountWord.replaceAll(",","");
 /*
        if (!wordCount.getCaseSensitive()){
            stringToCountWord = stringToCountWord.toLowerCase();
            for (String skip : wordCount.getWords().getSkip()){
                stringToCountWord = stringToCountWord.replaceAll(skip,"");
            }
        }
        stringToCountWord =stringToCountWord.replaceAll("  "," ").trim();
        System.out.println("----------------------------> new String is : ->  "+stringToCountWord);
       */
        ArrayList<String> stringList = new ArrayList(Arrays.asList(stringToCountWord.split(" ")));
        Map<String, Integer> counts = stringList.parallelStream().
                collect(Collectors.toConcurrentMap(
                        word -> word, word -> 1, Integer::sum));
        return counts;
    }

}
