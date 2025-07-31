package com.fbs.central_api;

import com.fbs.central_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class UserServiceTest {
    static UserService userService;

    @BeforeAll
    public static void setUp(){
        log.info("Inside setup method");
        userService = new UserService();
    }

    @Test
    public void testNullValues(){
        String expectedResponse = "Null Values present";
        Integer a = null;
        Integer b = null;
        String actualResponse = userService.valueChecker(a, b);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testFunctionIfBothAandBisNot5(){
        Integer a = 4;
        Integer b = 8;
        String expectedResponse = 12 + "";
        String actualResponse = userService.valueChecker(a, b);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testIfAValueIs5(){
        Integer a = 5;
        Integer b = 10;
        String expectedResponse = "Hey How are you ?";
        String actualResponse = userService.valueChecker(a, b);
        assertEquals(expectedResponse, actualResponse);
    }

}
