package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MathService mathService;

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

}
