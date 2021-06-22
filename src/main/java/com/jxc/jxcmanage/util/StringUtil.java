package com.jxc.jxcmanage.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.ProductDto;
import cn.hutool.core.util.ReflectUtil;



public class StringUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

	public static String compareModel(Object oldModel, Object newModel) {
		StringBuffer sbf = new StringBuffer();
		String returnStr = Constant.EMPTY;
		Field[] results = ReflectUtil.getFields(oldModel.getClass());
		for (Field field : results) {
			String value = Constant.EMPTY;
			String newValue = Constant.EMPTY;
			String result = Constant.EMPTY;
			String type = field.getType().getTypeName();
			type = type.substring(type.lastIndexOf(Constant.SPOT) + Constant.ONE);
			String fieldName = field.getName();
			switch (type) {
			case Constant.long_TYPE:
				value = (long) ReflectUtil.getFieldValue(oldModel, field) + Constant.EMPTY;
				newValue = (long) ReflectUtil.getFieldValue(newModel, field) + Constant.EMPTY;
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			case Constant.LONG_TYPE:
				value = (Long) ReflectUtil.getFieldValue(oldModel, field) + Constant.EMPTY;
				newValue = (Long) ReflectUtil.getFieldValue(newModel, field) + Constant.EMPTY;
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			case Constant.INT_TYPE:
				if (!ObjectUtils.isEmpty(ReflectUtil.getFieldValue(oldModel, field))) {
					value = (int) ReflectUtil.getFieldValue(oldModel, field) + Constant.EMPTY;
				}
				if (!ObjectUtils.isEmpty(ReflectUtil.getFieldValue(newModel, field))) {
					newValue = (int) ReflectUtil.getFieldValue(newModel, field) + Constant.EMPTY;
				}
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			case Constant.INTEGER_TYPE:
				if (!ObjectUtils.isEmpty(ReflectUtil.getFieldValue(oldModel, field))) {
					value = (Integer) ReflectUtil.getFieldValue(oldModel, field) + Constant.EMPTY;
				}
				if (!ObjectUtils.isEmpty(ReflectUtil.getFieldValue(newModel, field))) {
					newValue = (Integer) ReflectUtil.getFieldValue(newModel, field) + Constant.EMPTY;
				}
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			case Constant.BIGDECIMAL_TYPE:
				BigDecimal decimal = (BigDecimal) ReflectUtil.getFieldValue(oldModel, field);
				if (!ObjectUtils.isEmpty(decimal)) {
					value = decimal.toPlainString();
				}
				decimal = (BigDecimal) ReflectUtil.getFieldValue(newModel, field);
				if (!ObjectUtils.isEmpty(decimal)) {
					newValue = decimal.toPlainString();
				}
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			case Constant.DATE_TYPE:
				Date date = (Date) ReflectUtil.getFieldValue(oldModel, field);
				if (!ObjectUtils.isEmpty(date)) {
					value = DateUtils.DateToString(date, DateUtils.YYYYMMDDHHMMSS);
				}
				date = (Date) ReflectUtil.getFieldValue(newModel, field);
				if (!ObjectUtils.isEmpty(date)) {
					newValue = DateUtils.DateToString(date, DateUtils.YYYYMMDDHHMMSS);
				}
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			default:
				value = (String) ReflectUtil.getFieldValue(oldModel, field);
				newValue = (String) ReflectUtil.getFieldValue(newModel, field);
				result = combine(fieldName, value, newValue);
				sbf.append(result);
				break;
			}
		}
		if (!StringUtils.isEmpty(sbf.toString())) {
			returnStr = String.format(Constant.PREFIX, sbf.toString());
		} else {
			returnStr = sbf.toString();
		}
		return returnStr;
	}

	private static String combine(String fieldName, String oldValue, String newValue) {
		String value = Constant.EMPTY;
		if (StringUtils.isBlank(oldValue) && StringUtils.isBlank(newValue)) {
			return Constant.EMPTY;
		} else if (StringUtils.isEmpty(oldValue) && !StringUtils.isEmpty(newValue)) {
			value = String.format(Constant.COMBINE, fieldName, Constant.EMPTY, newValue);
		} else if (!StringUtils.isEmpty(oldValue) && StringUtils.isEmpty(newValue)) {
			value = String.format(Constant.COMBINE, fieldName, oldValue, Constant.EMPTY);
		} else {
			if (!oldValue.equals(newValue)) {
				value = String.format(Constant.COMBINE, fieldName, oldValue, newValue);
			}
		}
		return value;
	}

	public static void main(String[] args) {
		ProductDto dto1 = new ProductDto();
		ProductDto dto2 = new ProductDto();
		dto1.setSupplierId(25);
		dto1.setCode("code");
		dto2.setSupplierId(25);
		dto2.setCode("code1");
		System.out.println(compareModel(dto1, dto2));
		// System.out.println("java.lang.Long".substring("java.lang.Long".lastIndexOf(".")
		// + Constant.ONE));
	}
}
