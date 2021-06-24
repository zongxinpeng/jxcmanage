package com.jxc.jxcmanage.controller;

import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.SupplierDto;
import com.jxc.jxcmanage.service.SupplierService;
import com.jxc.jxcmanage.util.ImportExcel;

@RestController
@RequestMapping("/spplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

	/**
	 * 供应商/厂家分页
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody SupplierDto param) {
		return supplierService.page(param);

	}

	/**
	 * 供应商列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody SupplierDto param) {
		return supplierService.list(param);

	}

	/**
	 * 供应商保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody SupplierDto param) {
		return supplierService.save(param);

	}

	/**
	 * 供应商更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody SupplierDto param) {
		return supplierService.update(param);
	}

	/**
	 * 供应商删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody SupplierDto param) {
		return supplierService.delete(param);
	}

	/**
	 * 供应商导入
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/csvImport")
	public ResultBean csvImport(@RequestParam MultipartFile file) {
		List<SupplierDto> list = null;
		try {
			// 获取文件，对文件进行处理
			String fileName = file.getOriginalFilename();
			// 获取上传文件的输入流
			InputStream inputStream = file.getInputStream();
			// 调用工具类中方法，读取excel文件中数据
			list = ImportExcel.readExcel(fileName, inputStream, new SupplierDto());
			// 解析成对应的对象
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("导入数据失败，请检查文件格式！");
		}
		if (CollectionUtils.isEmpty(list)) {
			return ResultBean.fail("数据为空！");
		}
		return supplierService.csvImport(list);
	}
}
