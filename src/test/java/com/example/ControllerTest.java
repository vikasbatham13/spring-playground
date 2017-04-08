package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlight() throws Exception {
        this.mockMvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testFlights() throws Exception {
        this.mockMvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testTotalAmount_JsonString() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"},\"price\": 300}," +
                        "{ \"passenger\": { \"firstName\": \"Name B\", \"lastName\": \"Name C\" }, \"price\": 150} ]}");

        this.mockMvc.perform(request).
                andExpect(status().isOk())
                .andExpect(content().string("{\"result\":450}"));
    }

    @Test
    public void testTotalAmount_file() throws Exception {

        String json = getJSON("/data.json");
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json);
        ;

        this.mockMvc.perform(request).
                andExpect(status().isOk())
                .andExpect(content().string("{\"result\":450}"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);

        return new String(Files.readAllBytes(Paths.get(url.toURI())));

    }

}
