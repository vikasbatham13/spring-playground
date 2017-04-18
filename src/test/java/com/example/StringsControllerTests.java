package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@WebMvcTest(StringsController.class)
public class StringsControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WordCounter wordCounter;

    @Test
    public void testWordCounter() {
        WordCounter wordCounter1 = new WordCounter();
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        countMap.put("the", 2);
        countMap.put("moon", 2);
        countMap.put("to", 2);
        Assert.assertThat(wordCounter1.count("to the moon, to the moon"), org.hamcrest.Matchers.equalTo(countMap));
    }

    @Test
    public void testControllerBean() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/words/count")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("to the moon, to the moon");

        this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.content().string("{\"the\":2,\"moon\":2,\"to\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }



}