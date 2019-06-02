package net.lovexq.example.dubbo.one.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import net.lovexq.example.dubbo.one.facade.DemoFacade;
import net.lovexq.example.dubbo.two.facade.ShopFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示控制器
 *
 * @author LuPindong
 * @time 2019-06-02 10:20
 */
@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @Reference(
            group = "${dubbo.service.provider-one.group}",
            version = "${dubbo.service.provider-one.version}"
            //,url = "${dubbo.service.provider-one.url}"
    )
    DemoFacade demoFacade;

    @Reference(
            group = "${dubbo.service.provider-two.group}",
            version = "${dubbo.service.provider-two.version}"
            //,url = "${dubbo.service.provider-two.url}"
    )
    ShopFacade shopFacade;

    @RequestMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return demoFacade.sayHello(name);
    }

    @RequestMapping("/goShop/{name}")
    public String goShop(@PathVariable("name") String name) {
        return shopFacade.goShop(name);
    }

}