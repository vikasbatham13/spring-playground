package com.example;

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
public class StringCOntrollerWIthBeanTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WordCounter wordCounterBean;

    @Test
    public void testMockBeanController() throws Exception {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("the", 2);
        countMap.put("moon", 2);
        countMap.put("to", 2);

        // Mockito.when(wordCounter.count("to the moon, to the moon")).thenReturn (org.mockito.Matchers.any());
        BDDMockito.given(this.wordCounterBean.count("to the moon, to the moon")).willReturn(countMap);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/words/count")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("to the moon, to the moon");


        this.mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.content().string("{\"the\":2,\"moon\":2,\"to\":2}"))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

}
