package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/math")
public class Controller {


    @Autowired
    private MathService service;

    @GetMapping("/pi")
    public String getPi() {
        return Double.toString( Math.PI);
    }

    @GetMapping("/calculate")
    public String calculateNumbers(@RequestParam Map<String,String> querystring) {
        String operation = null;
        int response = 0;
        if (querystring.get("operation") != null)
            operation = (String) querystring.get("operation");
        else operation = "add";
        int var1 = Integer.parseInt(querystring.get("x"));
        int  var2 = Integer.parseInt(querystring.get("y"));
        response = service.calculation(var1,var2,operation);
        return Integer.toString(response);
    }

    @PostMapping("/sum")
    public String sumNumbers(@RequestParam MultiValueMap querystring) {

        String response;
        int sum = 0;
        Set<String> keys = querystring.keySet();
        for (String key : keys) {
            List list = (List) querystring.get(key);
            for(Object temp : list){
                int val2 =Integer.valueOf((String)temp);
                sum = service.calculation(sum,val2,"add");
            }
        }

        return Integer.toString(sum);
    }

    @RequestMapping(value = "/volume/{a}/{b}/{c}", method = {RequestMethod.POST, RequestMethod.GET,RequestMethod.PATCH,RequestMethod.PUT,RequestMethod.DELETE})
    public String getVolume(@PathVariable String a,@PathVariable String b,@PathVariable String c) {
        int result = (Integer.parseInt(a))* (Integer.parseInt(b)) * (Integer.parseInt(c));
        return String.format("The volume of a %s x %s x %s rectangle is %d", a, b,c,result);
    }

}