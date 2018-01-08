package pl.sda.mock.controller;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.sda.mock.service.HelloService;

public class MyControllerTest {

    private MyController myController;

    @Mock
    private HelloService helloService;

    @Before
    public void setUp() {
        initMocks(this);
        myController = new MyController(helloService);
    }

    @Test
    public void shouldSayHello() {

        String expected = "Hello";

        Mockito.when(helloService.say())
                .thenReturn("Hello");

        String actual = myController.saySomething();

        Assert.assertEquals(expected, actual);
        Mockito.verify(helloService, Mockito.times(1))
                .say();
    }

    @Test
    public void shouldConsumeMessage() {
        String message = "Message";

        Mockito.doNothing()
                .when(helloService)
                .consume(message);

        myController.consumer(message);

        Mockito.verify(helloService, Mockito.times(1))
                .consume(message);
    }
}