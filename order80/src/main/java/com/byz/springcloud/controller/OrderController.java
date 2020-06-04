package com.byz.springcloud.controller;

import com.byz.springcloud.entities.CommonResult;
import com.byz.springcloud.entities.Payment;
import com.byz.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://PAYMENT-SERVER";

    @Resource
    private RestTemplate restTemplate;

    //引入自定义负载均衡规则
    @Resource
    private LoadBalancer loadBalancer;

    //服务发现客户端实例
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getOne(@PathVariable Integer id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/"+id,CommonResult.class);  //读操作
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getForEntity(@PathVariable Integer id) {
        ResponseEntity<CommonResult> payment = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
        //根据状态码确定返回信息
        if (payment.getStatusCode().is2xxSuccessful()) {
            log.info("getForEntity，返回信息,返回体->getBody()：{}；返回头->getHeaders()：{}",payment.getBody(),payment.getHeaders());
            return payment.getBody();
        }
        return new CommonResult<>(999, "查询失败");
    }

    @GetMapping("/consumer/payment/lb/{id}")
    public String getPaymentLb(@PathVariable Integer id) {
        //获取指定服务的服务实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVER");
        if (instances == null || instances.isEmpty()) {
            return null;
        }
        //获取服务器实例
        ServiceInstance instance = loadBalancer.instance(instances);
        log.info("当前访问的服务器实例信息：instance.getHost：{}",instance.getHost());
        log.info("当前访问的服务器实例信息：instance.getMetadata：{}",instance.getMetadata().toString());
        log.info("当前访问的服务器实例信息：instance.getPort：{}",instance.getPort());
        log.info("当前访问的服务器实例信息：instance.getScheme：{}",instance.getScheme());
        log.info("当前访问的服务器实例信息：instance.getUri：{}",instance.getUri());
        //获取服务器访问路径rui
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/" + id,String.class);

    }

}
