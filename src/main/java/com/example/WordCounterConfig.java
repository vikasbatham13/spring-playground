
package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordCounterConfig {

    @Bean
    public static WordCounter wordCounter(){
        return new WordCounter();
    }
}

