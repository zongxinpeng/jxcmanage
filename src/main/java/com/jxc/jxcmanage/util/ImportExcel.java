package com.jxc.jxcmanage.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.ProductDto;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.json.JSONUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ImportExcel {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImportExcel.class);

	// abc.xls
	public static boolean isXls(String fileName) {
		// (?i)忽略大小写
		if (fileName.matches("^.+\\.(?i)(xls)$")) {
			return true;
		} else if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			return false;
		} else {
			throw new RuntimeException("格式不对");
		}
	}

	public static <T> List<T> readExcel(String fileName, final InputStream inputStream, T obj) throws Exception {
		Field[] results = ReflectUtil.getFields(obj.getClass());
		boolean ret = isXls(fileName);
		List<T> list = new ArrayList<T>();
		Workbook workbook = null;
		StringBuffer sbf = new StringBuffer();
		// 根据后缀创建不同的对象
		if (ret) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			workbook = new XSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(Constant.ZERO);
		// 得到标题行
		Row titleRow = sheet.getRow(Constant.ZERO);
		sbf.setLength(Constant.ZERO);
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = titleRow.getLastCellNum();
		for (int i = Constant.ONE; i < lastRowNum; i++) {
			sbf = new StringBuffer();
			Row row = sheet.getRow(i);
			for (int j = Constant.ONE; j < lastCellNum; j++) {
				Field fieldName = results[j];
				Cell cell = row.getCell(j);// 列值
				if (ObjectUtils.isEmpty(cell) || ObjectUtils.isEmpty(cell.getCellTypeEnum())) {
					continue;
				} else if (cell.getCellTypeEnum() == CellType.BLANK) {
					continue;
				} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					ReflectUtil.setFieldValue(obj, fieldName.getName(), cell.getNumericCellValue());
				} else {
					ReflectUtil.setFieldValue(obj, fieldName.getName(), cell.getStringCellValue());
				}
			}
			list.add(obj);
			obj = (T) obj.getClass().newInstance();
		}
		workbook.close();
		return list;
	}
	public static void main(String[] args) {
		File file = new File("/home/upsmart/桌面/jinxiaocun/UDIA网络申请.xls");
		try {
			List<Object> values = readExcel("UDIA网络申请.xls",new FileInputStream(file), new ProductDto());
			LOGGER.info("...{}....", JSONUtil.toJsonStr(values));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
