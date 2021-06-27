package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.jxcmanage.dto.StorageDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.Supplier;
import com.jxc.jxcmanage.code.mapper.SupplierMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.SupplierDto;
import com.jxc.jxcmanage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class SupplierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SupplierService.class);

	@Autowired
	private SupplierMapper supplierMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final SupplierDto param) {
		ResultBean bean = null;
		Supplier supplier = new Supplier();
		BeanUtils.copyProperties(param, supplier);
		try {
			supplierMapper.insertSelective(supplier);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "供应商/厂家数据"));
			e.printStackTrace();
		}
		bean = ResultBean.success();
		return bean;
	}

	/**
	 * 更新或者保存
	 * @param param
	 * @return
	 */
	public ResultBean addOrUpdateInfo(final SupplierDto param){
		//更新
		param.setUpdatedBy(Constant.DEFAULT_BY);
		if(param.getId()!=null && param.getId().intValue()>0){
			return update(param);
		} else {//新增
			param.setStatus(1);//默认1
			param.setCreatedBy(Constant.DEFAULT_BY);
			return save(param);
		}
	}

	/**
	 * 更新数据，加锁防止并发数据不一致
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public synchronized ResultBean update(final SupplierDto param) {
		ResultBean bean = null;
		try {
			Supplier old = supplierMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getName())) {
				old.setName(param.getName());
			}
			if (!ObjectUtils.isEmpty(param.getCode())) {
				old.setCode(param.getCode());
			}
			if (!ObjectUtils.isEmpty(param.getLinkAddress())) {
				old.setLinkAddress(param.getLinkAddress());
			}
			if (!ObjectUtils.isEmpty(param.getLinkMobile())) {
				old.setLinkMobile(param.getLinkMobile());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			supplierMapper.updateByPrimaryKeySelective(old);
			Supplier now = supplierMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "供应商数据"));
			e.printStackTrace();
		}
		bean = ResultBean.success();
		return bean;
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean delete(final SupplierDto param) {
		try {
			supplierMapper.deleteByPrimaryKey(param.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail(String.format(Constant.LOG_FORMAT, "供应商数据"));
		}
		return ResultBean.success();
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean page(final SupplierDto param) {
		PageInfo<SupplierDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(supplierMapper.list(param));
			resultData.put("total", page.getTotal());
			resultData.put("rows", page.getList());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(resultData);
	}

	/**
	 * 数据导入
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean list(final SupplierDto param) {
		List<SupplierDto> list = null;
		try {
			list = supplierMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}

	/**
	 * 数据导入
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean csvImport(final List<SupplierDto> params) {
		try {
			// 生成供应商明细
			supplierMapper.insertBatch(params);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("文件写入失败！");
		}
		String message = String.format(Constant.IMPORT_BATCH, params.size());
		return ResultBean.success(message);
	}

	public static void main(String[] args) {
		System.out.println(String.format(Constant.LOG_FORMAT, "产品数据"));
	}
}
