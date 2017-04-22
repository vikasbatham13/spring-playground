package com.example;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "wordCount.caseSensitive=false",
        "wordCount.words.skip[0]=the",
        "wordCount.words.skip[1]=an",
        "wordCount.words.skip[2]=a",
})
public class ConfigFileUsingPropertySourceTest {
    @Autowired
    private WordCount wordCount;

    @Test
    public void configTest(){

        Assert.assertThat(wordCount.getCaseSensitive(), Matchers.equalTo(false));
        List<String> attributeList = new ArrayList<>();
        attributeList.add("the");
        attributeList.add("an");
        attributeList.add("a");
        Assert.assertThat(wordCount.getWords().getSkip(), Matchers.equalTo(attributeList));

    }
}
