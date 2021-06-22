package com.jxc.jxcmanage.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.dto.ProductDto;
import com.jxc.jxcmanage.service.ProductService;
import com.jxc.jxcmanage.util.ImportExcel;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
	 * 产品分页
	 * @param param
	 * @return
	 */
	@PostMapping("/page")
	public ResultBean page(@RequestBody ProductDto param) {
		return productService.page(param);

	}

	/**
	 * 产品列表
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody ProductDto param) {
		return null;

	}

	/**
	 * 产品保存
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody ProductDto param) {
		return productService.save(param);

	}

	/**
	 * 产品更新
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody ProductDto param) {
		return productService.update(param);
	}

	/**
	 * 产品删除
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody ProductDto param) {
		return productService.delete(param);
	}
	
	/**
	 * 产品导入
	 * @param param
	 * @return
	 */
	@PostMapping("/csvImport")
	public ResultBean csvImport(@RequestBody ProductDto param, @RequestParam MultipartFile file) {
		List<ProductDto> list = new ArrayList<>();
		// 获取文件，对文件进行处理
		try {
			String fileName = file.getOriginalFilename();
			// 获取上传文件的输入流
			InputStream inputStream = file.getInputStream();
			// 调用工具类中方法，读取excel文件中数据
			List<Map<String, Object>> sourceList = ImportExcel.readExcel(fileName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productService.csvImport(list);
	}
}
