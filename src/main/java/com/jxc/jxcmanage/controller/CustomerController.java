package com.jxc.jxcmanage.controller;

import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.CustomerDto;
import com.jxc.jxcmanage.service.CustomerService;
import com.jxc.jxcmanage.util.ImportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	/**
	 * 产品分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody CustomerDto param) {
		return customerService.page(param);

	}

	/**
	 * 产品列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody CustomerDto param) {
		return customerService.list(param);

	}

	/**
	 * 产品保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody CustomerDto param) {
		return customerService.save(param);

	}

	@PostMapping("/addOrUpdateCustomerInfo")
	public ResultBean addOrUpdateCustomerInfo(@RequestBody CustomerDto param) {
		return customerService.addOrUpdateCustomerInfo(param);

	}

	/**
	 * 产品更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody CustomerDto param) {
		return customerService.update(param);
	}

	/**
	 * 产品删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody CustomerDto param) {
		return customerService.delete(param);
	}

	/**
	 * 产品导入
	 * @param param
	 * @return
	 */
	@PostMapping("/csvImport")
	public ResultBean csvImport( @RequestParam MultipartFile file) {
		List<CustomerDto> list = null;
		try {
			// 获取文件，对文件进行处理
			String fileName = file.getOriginalFilename();
			// 获取上传文件的输入流
			InputStream inputStream = file.getInputStream();
			// 调用工具类中方法，读取excel文件中数据
			list = ImportExcel.readExcel(fileName, inputStream, new CustomerDto());
			// 解析成对应的对象
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("导入数据失败，请检查文件格式！");
		}
		if (CollectionUtils.isEmpty(list)) {
			return ResultBean.fail("数据为空！");
		}
		return customerService.csvImport(list);
	}
}
