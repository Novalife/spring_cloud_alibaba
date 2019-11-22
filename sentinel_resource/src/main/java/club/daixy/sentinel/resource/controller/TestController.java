package club.daixy.sentinel.resource.controller;

import club.daixy.sentinel.resource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello() {
        testService.doSomeThing("hello " + new Date());
        return "daixy.club";
    }

    @GetMapping("/hello2")
    public String hello2() {
        testService.doSomeThing2("hello2 " + new Date());
        return "didispace.com";
    }

}