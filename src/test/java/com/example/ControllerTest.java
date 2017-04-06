package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlight() throws Exception {
        this.mockMvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Some other name")));
    }

    @Test
    public void testFlights() throws Exception {
        this.mockMvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.[0]Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$.[0]Tickets[0].Passenger.FirstName", is("Some name")))
                .andExpect(jsonPath("$.[0]Tickets[0].Passenger.LastName", is("Some other name")))
                .andExpect(jsonPath("$.[1]Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.[1]Tickets[0].Price", is(400)))
                .andExpect(jsonPath("$.[1]Tickets[0].Passenger.FirstName", is("Some other name")));
    }
}
