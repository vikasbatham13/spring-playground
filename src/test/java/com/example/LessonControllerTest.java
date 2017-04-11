package com.example;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LessonRepository entityManagerFactory;


    @Test
    public void testGetLessn() throws Exception {

        this.mockMvc.perform(
                get("/lessons/5")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                //.andExpect(content().string("{\"id\":5,\"title\":\"five\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void testPatchLesson() throws Exception {
        Lesson    lessson1 = new Lesson();
        lessson1.setTitle("One");
        Lesson   lessson2 = new Lesson();
        lessson2.setTitle("two");
        Lesson  lessson3 = new Lesson();
        lessson3.setTitle("three");
        Lesson  lessson4 = new Lesson();
        lessson4.setTitle("four");
        Lesson   lessson5 = new Lesson();
        lessson5.setTitle("five");
        Lesson  lessson6 = new Lesson();
        lessson6.setTitle("Six");
        this.entityManagerFactory.save(Arrays.asList(lessson1, lessson2, lessson3, lessson4, lessson5, lessson6));

        String json = getJSON("/lesson.json");
        MockHttpServletRequestBuilder request = patch("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        this.mockMvc.perform(request).
                andExpect(content().string("{\"id\":5,\"title\":\"test\"}")).
                andExpect(status().isOk());
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));

    }

}

