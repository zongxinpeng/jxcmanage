package com.jxc.jxcmanage.controller;

import com.jxc.jxcmanage.code.entity.MenuInfo;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.ProductDto;
import com.jxc.jxcmanage.dto.SaleOrderDto;
import com.jxc.jxcmanage.service.MenuService;
import com.jxc.jxcmanage.service.ProductService;
import com.jxc.jxcmanage.util.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;

	/**
	 * 菜单列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/menuList")
	public ResultBean page(@RequestBody Map<String,Object> param) {
		return menuService.list(param);

	}

	/**
	 * 菜单保存
	 * 
	 * @param menuInfo
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody MenuInfo menuInfo) {
		return menuService.save(menuInfo);

	}

	/**
	 * 菜单更新
	 * 
	 * @param menuInfo
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody MenuInfo menuInfo) {
		return menuService.update(menuInfo);
	}

	/**
	 * 产品删除
	 * 
	 * @param menuInfo
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody MenuInfo menuInfo) {
		return menuService.delete(menuInfo);
	}

}
