package com.meamei;

import com.meamei.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author mm013
 * @create 2020-04-15 16:01:48
 * @description:
 */
@MapperScan({"com.meamei.baseDao.dao"})
@ComponentScan({"com.meamei.baseConfig.config", "com.meamei.utils"})
@ComponentScan(value = {"com.meamei.baseService.service"}, lazyInit = true)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * ContextRefreshedEvent: bean 创建完，服务器启动完毕前</br>
     * ApplicationReadyEvent: 服务器启动完毕
     */
    @EventListener(ContextRefreshedEvent.class)
    public void initListener() {
        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        if (stringRedisTemplate != null) {
            // 初始化redis连接
            stringRedisTemplate.opsForValue().get("initListener");
        }
    }
}
