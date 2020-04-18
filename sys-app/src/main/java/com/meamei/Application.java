package com.meamei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
}
