package com.spring.cloud.nacos.um.module;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 线刷实体对象
 *
 * @author xuweizhi
 * @since 2020/07/02 22:26
 */
@Data
@ToString
@Component
@RefreshScope
@ConfigurationProperties(prefix = "book")
public class BookVO {

    private String name;

    private Double price;


}
