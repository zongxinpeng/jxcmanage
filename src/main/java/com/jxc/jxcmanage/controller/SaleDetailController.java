package com.jxc.jxcmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.SaleOrderDto;
import com.jxc.jxcmanage.service.SaleDetailService;
import com.jxc.jxcmanage.service.SaleOrderService;

@RestController
@RequestMapping("/saleDetail")
public class SaleDetailController {

	@Autowired
	private SaleDetailService saleDetailService;

	/**
	 * 产品分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody SaleDetailDto param) {
		return saleDetailService.page(param);

	}

	/**
	 * 产品列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody SaleOrderDto param) {
		return saleDetailService.list(param);

	}

	/**
	 * 产品保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody SaleOrderDto param) {
		return saleDetailService.save(param);

	}

	/**
	 * 产品更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody SaleOrderDto param) {
		return saleDetailService.update(param);
	}

	/**
	 * 产品删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody SaleOrderDto param) {
		return saleDetailService.delete(param);
	}
}