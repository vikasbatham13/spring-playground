
package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
@AutoConfigureMockMvc(secure=false)
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void GetPITest() throws Exception{
        this.mockMvc.perform(get("/math/pi"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void CalculateAddTest() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void CalculateSubtractTest() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("-2"));
    }

    @Test
    public void CalculateDivideTest() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));
    }
    @Test
    public void CalculateMultiplyTest() throws Exception{
        this.mockMvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("24"));
    }

    @Test
    public void CalculateWithOutOperationTest() throws Exception{
        this.mockMvc.perform(get("/math/calculate?x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
    @Test
    public void SumAddTest() throws Exception{
        this.mockMvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }

    @Test
    public void VolumeTest() throws Exception{
        this.mockMvc.perform(get ("/math/volume/3/4/6"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3 x 4 x 6 rectangle is 72"));
    }

    @Test
    public void AreaCircleTest_HappyPath() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "5");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 5 is 7.853982e+01"));

    }

    @Test
    public void AreaCircleTest_Invalid() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }

    @Test
    public void AreaRectangleTest_HappyPath() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "5")
                .param("height","4");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 5x4 rectangle is 20"));
    }

    @Test
    public void AreaRectangleTest_Invalid() throws Exception{
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "5")
                .param("height","");
        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}

