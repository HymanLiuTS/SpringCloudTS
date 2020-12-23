package cn.codenest.accountservice.config;

import cn.codenest.accountservice.hystrix.ThreadLocalAeareStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author ：Hyman
 * @date ：Created in 2020/12/23 12:29
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class ThreadLocalConfig {
    //required = false 有则装配，没有不用装配
    @Autowired(required = false)
    private HystrixConcurrencyStrategy hystrixConcurrencyStrategy;

    //初始化时安装以下顺序：
    //Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
    @PostConstruct
    public void init() {
        HystrixEventNotifier eventNotifier =
                HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher =
                HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy =
                HystrixPlugins.getInstance().getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook =
                HystrixPlugins.getInstance().getCommandExecutionHook();
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new ThreadLocalAeareStrategy(hystrixConcurrencyStrategy));
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
    }
}
