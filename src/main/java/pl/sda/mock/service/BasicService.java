package pl.sda.mock.service;

public class BasicService implements HelloService {
    @Override
    public String say() {
        return "Basic Controller";
    }

    @Override
    public void consume(String message) {
        System.out.println("Consuming: " + message);
    }
}
