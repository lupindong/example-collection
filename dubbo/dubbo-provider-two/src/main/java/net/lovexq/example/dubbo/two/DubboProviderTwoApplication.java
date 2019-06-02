package net.lovexq.example.dubbo.two;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author LuPindong
 * @time 2019-06-02 10:23
 */
@SpringBootApplication
public class DubboProviderTwoApplication {

    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger", "slf4j");

        new SpringApplicationBuilder(DubboProviderTwoApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
