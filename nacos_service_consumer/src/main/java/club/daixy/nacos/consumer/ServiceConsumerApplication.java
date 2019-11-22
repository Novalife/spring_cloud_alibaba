/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package club.daixy.nacos.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author daixiaoyong
 * @date 2019/11/18 19:55
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        //        @Autowired
        //        LoadBalancerClient loadBalancerClient;
        //
        //        @GetMapping("/test")
        //        public String test() {
        //            // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        //            ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-service-provider");
        //            String url = serviceInstance.getUri() + "/hello?name=dxy";
        //            RestTemplate restTemplate = new RestTemplate();
        //            String result = restTemplate.getForObject(url, String.class);
        //            return "Invoke : " + url + ", return : " + result;
        //        }

        //-----------------------------------------------------------------

        //        @Autowired
        //        RestTemplate restTemplate;
        //
        //        @GetMapping("/test")
        //        public String test() {
        //            String result = restTemplate.getForObject("http://nacos-service-provider/hello?name=dxy",
        //                    String.class);
        //            return "Return : " + result;
        //        }
        //    }
        //
        //    @Bean
        //    @LoadBalanced
        //    public RestTemplate restTemplate() {
        //        return new RestTemplate();
        //    }

        //-------------------------------------------------------------------

        //        @Autowired
        //        private WebClient.Builder webClientBuilder;
        //
        //        @GetMapping("/test")
        //        public Mono<String> test() {
        //            Mono<String> result = webClientBuilder.build().get()
        //                    .uri("http://nacos-service-provider/hello?name=dxy").retrieve().bodyToMono(String.class);
        //            return result;
        //        }
        //    }
        //
        //    @Bean
        //    @LoadBalanced
        //    public WebClient.Builder loadBalancedWebClientBuilder() {
        //        return WebClient.builder();
        //    }

        @FeignClient("nacos-service-provider")
        interface Client {
            @GetMapping("/hello")
            String hello(@RequestParam(name = "name") String name);
        }

        @Autowired
        Client client;

        @GetMapping("/test")
        public String test() {
            String result = client.hello("dxy");
            return "Return : " + result;
        }
    }
}
