package com.example;

import org.springframework.stereotype.Component;

@Component
public class MathService {
    public int calculation(int var1, int var2, String operation){
        int response = 0;

        switch (operation) {
            case "add":
                response = Math.addExact(var1, var2);
                break;
            case "multiply":
                response = Math.multiplyExact(var1, var2);
                break;
            case "subtract":
                response = Math.subtractExact(var1, var2);
                break;
            case "divide":
                response = Math.floorDiv(var1, var2);
                break;
        }
        return response;
    }
}
