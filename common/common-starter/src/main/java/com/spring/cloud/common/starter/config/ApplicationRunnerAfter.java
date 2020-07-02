package com.spring.cloud.common.starter.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.Resource;

/**
 * SpringBoot 完成后打印 swagger 文档地址
 *
 * @author xuweizhi
 * @since 2020/07/02 12:08
 */
public class ApplicationRunnerAfter implements ApplicationRunner {

    protected final Log log = LogFactory.getLog(this.getClass());

    @Resource
    private IpConfiguration ipConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Swagger github : http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/swagger-ui.html");
        log.info("Swagger github boot-strap: http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/doc.html");
    }
}
