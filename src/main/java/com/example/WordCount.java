package com.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("wordCount")
public class WordCount {
    private Boolean caseSensitive;
    private Words words;
    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }
   public Boolean getCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }
       public static class Words{
            public List<String> getSkip() {
                return skip;
            }

            public void setSkip(List<String> skip) {
                this.skip = skip;
            }

            private List<String> skip;
    }

}
