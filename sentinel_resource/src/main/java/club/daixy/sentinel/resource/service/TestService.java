package club.daixy.sentinel.resource.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    /**
     * 实现处理函数，该函数的传参必须与资源点的传参一样，
     * 并且最后加上BlockException异常参数；同时，返回类型也必须一样
     * 
     * @Author: daixiaoyong
     * @Date: 2019/11/19 16:43
     */
    @SentinelResource(value = "doSomeThing", blockHandler = "exceptionHandler")
    public void doSomeThing(String str) {
        log.info(str);
    }

    // 限流与阻塞处理
    public void exceptionHandler(String str, BlockException ex) {
        log.error("blockHandler：" + str, ex);
    }

    //--------------------------------------------------------------

    @SentinelResource(value = "doSomeThing2",fallback = "fallbackHandler")
    public void doSomeThing2(String str) {
        log.info(str);
        throw new RuntimeException("发生异常");
    }

    public void fallbackHandler(String str) {
        log.error("fallbackHandler：" + str);
    }
}
