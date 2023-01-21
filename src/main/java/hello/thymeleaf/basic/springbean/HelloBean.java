package hello.thymeleaf.basic.springbean;

import org.springframework.stereotype.Component;

@Component("helloBean")
public class HelloBean {
    public String hello(String data) {
        return "hello " + data;
    }
}
