package net.lovexq.example.dubbo.one.facade.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import net.lovexq.example.dubbo.one.facade.DemoFacade;
import net.lovexq.example.dubbo.two.facade.ShopFacade;

/**
 * 演示 Facade实现类
 *
 * @author LuPindong
 * @time 2019-06-02 10:20
 */
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoFacadeImpl implements DemoFacade {

    private static int count = 0;

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}")
    ShopFacade shopFacade;

    @Override
    public String sayHello(String name) {
        String shopName = shopFacade.goShop(name);
        return String.format("[%s], sayHello %s >>> %d", shopName, name, count++);
    }

}
