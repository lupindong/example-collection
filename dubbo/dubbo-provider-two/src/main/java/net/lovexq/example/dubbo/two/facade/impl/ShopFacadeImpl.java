package net.lovexq.example.dubbo.two.facade.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.lovexq.example.dubbo.two.facade.ShopFacade;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Random;

/**
 * 商店 Facade实现类
 *
 * @author LuPindong
 * @time 2019-06-02 10:20
 */
@Service(
        group = "${dubbo.provider.group}",
        version = "${dubbo.provider.version}"
)
@Slf4j
public class ShopFacadeImpl implements ShopFacade {

    private static final Random random = new Random(System.currentTimeMillis());

    private static final List<String> shopList = Lists.newArrayList("淘宝", "京东", "唯品会", "拼多多");

    @Override
    public String goShop(String name) {
        log.info("consumer name is {}", name);
        String shopName = shopList.get(random.nextInt(4));
        return shopName;
    }
}
