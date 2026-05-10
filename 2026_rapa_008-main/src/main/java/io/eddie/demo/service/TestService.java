package io.eddie.demo.service;

import io.eddie.demo.controller.TestController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public int superComplexLogic(TestController.Numbers numbers) {
        int result = numbers.getA() + numbers.getB();
        return result;
    }

}
