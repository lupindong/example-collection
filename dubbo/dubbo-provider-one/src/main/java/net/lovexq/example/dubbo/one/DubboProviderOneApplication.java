package net.lovexq.example.dubbo.one;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author LuPindong
 * @time 2019-06-02 10:23
 */
@SpringBootApplication
public class DubboProviderOneApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboProviderOneApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}