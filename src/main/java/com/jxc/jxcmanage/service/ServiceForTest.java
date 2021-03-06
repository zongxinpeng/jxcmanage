package com.jxc.jxcmanage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.jxc.jxcmanage.code.entity.Account;
import com.jxc.jxcmanage.code.mapper.AccountMapper;
import com.jxc.jxcmanage.code.mapper.ProductMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.CustomerDto;
import com.jxc.jxcmanage.dto.ProductDto;
import com.jxc.jxcmanage.dto.SupplierDto;

import cn.hutool.json.JSONUtil;
@EnableScheduling
@Service
public class ServiceForTest {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SupplierService supplierService;
	private int count = 1;

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceForTest.class);
	//@Scheduled(fixedRate = 2000)
	public void test() {
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
	//@Scheduled(fixedRate = 2000)
	public void testProduct() {
		int size = 5;
		List<ProductDto> dtos = new ArrayList<>();
		ProductDto dto = new ProductDto();
		dto.setStatus(1);
		dto.setCreatedBy("2");
		dto.setUpdatedBy("5");
		dto.setCreatedDate(new Date());
		dto.setUpdatedDate(new Date());
		dto.setName("测试");
		dto.setNote("55");
		dto.setCode("123456");
		dto.setSpecs("ceshi");
		dto.setUnit("525");
		dtos.add(dto);
		List<ProductDto> list = productMapper.list(dto);
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(dto));
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(list));
		productMapper.insertBatch(dtos);
		dto.setPageNumber(count);
		dto.setPageSize(size);
		count = count ++;
		ResultBean resultBean = productService.page(dto);
		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(resultBean.getData()));

	}
	@Scheduled(fixedRate = 2000)
	public void testCustomer() {
		int size = 5;
		List<CustomerDto> params = new ArrayList<>();
		CustomerDto dto = new CustomerDto();
		dto.setStatus(1);
		dto.setCreatedBy("2");
		dto.setUpdatedBy("5");
		dto.setCreatedDate(new Date());
		dto.setUpdatedDate(new Date());
		dto.setName("测试");
		dto.setNote("55");
		params.add(dto);
		customerService.csvImport(params);
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(dto));
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(list));
		dto.setPageNumber(count);
		dto.setPageSize(size);
		count = count ++;
		ResultBean resultBean = customerService.page(dto);
		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(resultBean.getData()));
	}
	@Scheduled(fixedRate = 2000)
	public void testSupplier() {
		int size = 5;
		List<SupplierDto> params = new ArrayList<>();
		SupplierDto dto = new SupplierDto();
		dto.setStatus(1);
		dto.setCreatedBy("2");
		dto.setUpdatedBy("5");
		dto.setCreatedDate(new Date());
		dto.setUpdatedDate(new Date());
		dto.setName("测试");
		dto.setNote("55");
		dto.setCode("123");
		params.add(dto);
		supplierService.csvImport(params);
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(dto));
//		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(list));
		dto.setPageNumber(count);
		dto.setPageSize(size);
		count = count ++;
		ResultBean resultBean = supplierService.page(dto);
		LOGGER.info("......:{}.....", JSONUtil.toJsonStr(resultBean.getData()));
	}
}
