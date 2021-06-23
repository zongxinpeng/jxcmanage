package com.jxc.jxcmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.jxc.jxcmanage.mapper","com.jxc.jxcmanage.code.mapper"})
public class JxcmanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(JxcmanageApplication.class, args);
	}

}
