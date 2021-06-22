package com.jxc.jxcmanage.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jxc.jxcmanage.code.entity.Account;
import com.jxc.jxcmanage.code.mapper.AccountMapper;
@EnableScheduling
@Service
public class ServiceForTest {
	@Autowired
	private AccountMapper accountMapper;
	
	@Scheduled(fixedRate = 2000)
	public void test () {
		Account record = new Account();
		record.setDepartment(1);
		record.setStatus(1);
		record.setAccount("index");
		record.setCreatedBy("123");
		record.setUpdatedBy("456");
		record.setCreatedDate(new Date());
		record.setUpdatedDate(new Date());
		accountMapper.insert(record);
	}

}
