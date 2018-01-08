package pl.sda.mock.controller;

import pl.sda.mock.service.*;

public class MyController {

    private HelloService helloService;

    public MyController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String saySomething() {
        return helloService.say();
    }

    public void consumer(String message) {
        helloService.consume(message);
    }

}
