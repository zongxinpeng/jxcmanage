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
import com.jxc.jxcmanage.dto.StorageDetailDto;
import com.jxc.jxcmanage.dto.StorageDto;
import com.jxc.jxcmanage.service.StorageService;
import com.jxc.jxcmanage.util.ImportExcel;

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
	public ResultBean page(@RequestBody StorageDto param) {
		return storageService.page(param);

	}

	/**
	 * 产品列表
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/list")
	public ResultBean list(@RequestBody StorageDto param) {
		return storageService.list(param);

	}

	/**
	 * 产品保存
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody StorageDto param) {
		return storageService.save(param);

	}

	/**
	 * 产品更新
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/update")
	public ResultBean update(@RequestBody StorageDto param) {
		return storageService.update(param);
	}

	/**
	 * 产品删除
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody StorageDto param) {
		return storageService.delete(param);
	}
	/**
	 * 产品导入
	 * @param param
	 * @return
	 */
	@PostMapping("/csvImport")
	public ResultBean csvImport(@RequestBody StorageDto param, @RequestParam MultipartFile file) {
		List<StorageDetailDto> list = null;
		try {
			// 获取文件，对文件进行处理
			String fileName = file.getOriginalFilename();
			// 获取上传文件的输入流
			InputStream inputStream = file.getInputStream();
			// 调用工具类中方法，读取excel文件中数据
			list = ImportExcel.readExcel(fileName, inputStream, new StorageDetailDto());
			// 解析成对应的对象
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("导入数据失败，请检查文件格式！");
		}
		if (CollectionUtils.isEmpty(list)) {
			return ResultBean.fail("数据为空！");
		}
		return storageService.csvImport(param, list);
	}
}
