package net.lovexq.example.dubbo.one.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Rpc日志过滤器
 *
 * @author LuPindong
 * @time 2019-06-02 13:49
 */
@Slf4j
@Activate
public class RpcLogFilter2 implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        log.info("222.RpcLogFilter2.invoke >>> {}", invoker);

        return invoker.invoke(invocation);
    }
}
