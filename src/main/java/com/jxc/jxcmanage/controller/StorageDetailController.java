package com.jxc.jxcmanage.controller;

import com.jxc.jxcmanage.dto.StorageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.StorageDetailDto;
import com.jxc.jxcmanage.service.StorageDetailService;

@RestController
@RequestMapping("/storageDetail")
public class StorageDetailController {
	@Autowired
	private StorageDetailService storageDetailService;

	/**
	 * 产品分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody StorageDetailDto param) {
		return storageDetailService.page(param);

	}

	/**
	 * 产品列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody StorageDetailDto param) {
		return storageDetailService.list(param);

	}

	/**
	 * 产品保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody StorageDetailDto param) {
		return storageDetailService.save(param);

	}

	/**
	 * 保存或更新
	 * @param param
	 * @return
	 */
	@PostMapping("/addOrUpdateInfo")
	public ResultBean addOrUpdateInfo(@RequestBody StorageDetailDto param) {
		return storageDetailService.addOrUpdateInfo(param);

	}


	/**
	 * 产品更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody StorageDetailDto param) {
		return storageDetailService.update(param);
	}

	/**
	 * 产品删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody StorageDetailDto param) {
		return storageDetailService.delete(param);
	}
}
