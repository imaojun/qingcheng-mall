package xyz.maojun.serviceconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

/**
 * @Description: RibbonLoadBalancerClient test
 * @Created by maojun
 * @Date: 2020/03/26
 */

@SpringBootTest
public class RibbonLoadBalancerTests {

    @Autowired
    private LoadBalancerClient client;

    @Test
    public void testRibbonLoadBalancerClient() {
        for (int i = 0; i < 100; i++) {
            ServiceInstance instance = client.choose("service-provider");
            System.out.println(instance.getHost() + ": " + instance.getPort());
        }


    }
}
