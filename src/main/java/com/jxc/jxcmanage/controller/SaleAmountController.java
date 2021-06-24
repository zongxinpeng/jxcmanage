package com.jxc.jxcmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.SaleAmountDto;
import com.jxc.jxcmanage.service.SaleAmountService;
@RestController
@RequestMapping("/saleAmount")
public class SaleAmountController {

	@Autowired
	private SaleAmountService saleAmountService;

	/**
	 * 利润额分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody SaleAmountDto param) {
		return saleAmountService.page(param);

	}

	/**
	 * 利润额列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody SaleAmountDto param) {
		return saleAmountService.list(param);

	}

	/**
	 * 利润额保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody SaleAmountDto param) {
		return saleAmountService.save(param);

	}

	/**
	 * 利润额更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody SaleAmountDto param) {
		return saleAmountService.update(param);
	}

	/**
	 * 利润额删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody SaleAmountDto param) {
		return saleAmountService.delete(param);
	}
}
