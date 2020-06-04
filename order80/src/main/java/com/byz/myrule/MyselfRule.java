package com.byz.myrule;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ribbon规则
 */
@Configuration
public class MyselfRule {

    /**
     * 随机规则
     * @return
     */
    @Bean
    public IRule myselfRandomRule() {
        return new RandomRule();
    }

    /**
     * 重试规则
     * new RetryRule()
     *      先按照RoundRibbonRule的策略获取服务，若获取失败则在指定时间内进行重试；默认策略RoundRibbonRule
     * new RetryRule(IRule subRule)
     *      按照指定的策略获取服务，若获取失败则在默认时间内进行重试; 默认时间为500L毫秒
     * new RetryRule(IRule subRule, long maxRetryMillis)
     *      按照指定的策略获取服务，若获取失败则在指定时间内进行重试；
     * @return
     */
    @Bean
    public IRule myselfRetryRule() {
        new RetryRule(new RandomRule());
        new RetryRule(new RandomRule(),300L);
        return new RetryRule();
    }


    /**
     * 选择权重规则：响应速度越快的实例选择权重越大，越容易被选择
     * new WeightedResponseTimeRule()
     * @return
     */
    @Bean
    public IRule myselfWeightedResponseTimeRule() {
        return new WeightedResponseTimeRule();
    }

    /**
     *  最好的规则
     * 优先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量小的服务
     * @return
     */
    @Bean
    public IRule myselfBestAvailableRule() {
        return new BestAvailableRule();
    }

    /**
     * 可用性规则
     * 先过滤掉故障实例，再选择并发量小的服务
     * @return
     */
    @Bean
    public IRule myselfAvailabilityFilteringRule() {
        return new AvailabilityFilteringRule();
    }

    /**
     * 默认规则，复合判断server所在区域的性能和server可用性选择服务器
     * @return
     */
    @Bean
    public IRule myselfZoneAvoidanceRule() {
        return new ZoneAvoidanceRule();
    }
}
