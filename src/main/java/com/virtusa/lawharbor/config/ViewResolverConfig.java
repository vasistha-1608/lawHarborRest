package com.virtusa.lawharbor.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@ComponentScan(basePackages = "com.virtusa.lawharbor")
@EnableWebMvc
public class ViewResolverConfig {

}
