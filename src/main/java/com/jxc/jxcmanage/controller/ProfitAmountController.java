package com.jxc.jxcmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.ProfitAmountDto;
import com.jxc.jxcmanage.service.ProfitAmountService;
@RestController
@RequestMapping("/profit")
public class ProfitAmountController {

	@Autowired
	private ProfitAmountService profitAmountService;

	/**
	 * 利润额分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody ProfitAmountDto param) {
		return profitAmountService.page(param);

	}

	/**
	 * 利润额列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody ProfitAmountDto param) {
		return profitAmountService.list(param);

	}

	/**
	 * 利润额保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody ProfitAmountDto param) {
		return profitAmountService.save(param);

	}

	/**
	 * 利润额更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody ProfitAmountDto param) {
		return profitAmountService.update(param);
	}

	/**
	 * 利润额删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody ProfitAmountDto param) {
		return profitAmountService.delete(param);
	}
}
