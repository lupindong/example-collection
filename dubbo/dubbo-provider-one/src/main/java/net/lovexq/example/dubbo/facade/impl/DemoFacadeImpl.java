package net.lovexq.example.dubbo.facade.impl;

import com.alibaba.dubbo.config.annotation.Service;
import net.lovexq.example.dubbo.facade.DemoFacade;

/**
 * 演示Facade 实现类
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

    @Override
    public String sayHello(String name) {
        return "sayHello >>> " + count;
    }

}
