package com.jxc.jxcmanage.controller;

import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.SysDepartmentDto;
import com.jxc.jxcmanage.service.SysDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class SysDepartmentController {

	@Autowired
	private SysDepartmentService sysDepartmentService;

	/**
	 * 产品分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody SysDepartmentDto param) {
		return sysDepartmentService.page(param);

	}

	/**
	 * 产品列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody SysDepartmentDto param) {
		return sysDepartmentService.list(param);

	}

	/**
	 * 产品保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody SysDepartmentDto param) {
		return sysDepartmentService.save(param);

	}

	/**
	 * 产品更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody SysDepartmentDto param) {
		return sysDepartmentService.update(param);
	}

	/**
	 * 产品删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody SysDepartmentDto param) {
		return sysDepartmentService.delete(param);
	}
}
