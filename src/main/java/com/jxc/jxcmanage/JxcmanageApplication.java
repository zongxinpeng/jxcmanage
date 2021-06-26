package com.jxc.jxcmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({ "com.jxc.jxcmanage.code.mapper" })
public class JxcmanageApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(JxcmanageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JxcmanageApplication.class, args);
		LOGGER.info("........................服务启动成功！..........................");
	}

}
