package com.dy.base.lb;

import com.dy.base.lb.config.ConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LbApplication {

	private static final Logger log = LoggerFactory.getLogger(LbApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LbApplication.class, args);
		ConfigInfo configInfo = context.getBeanFactory().getBean("configInfo", ConfigInfo.class);
		log.info("\n{}", configInfo);
	}

}
