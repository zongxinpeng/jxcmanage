package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.jxcmanage.dto.SupplierDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.SysDepartment;
import com.jxc.jxcmanage.code.mapper.SysDepartmentMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.SysDepartmentDto;
import com.jxc.jxcmanage.util.StringUtil;

@Service
public class SysDepartmentService {


	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final SysDepartmentDto param) {
		ResultBean bean = null;
		SysDepartment sysDepartment = new SysDepartment();
		BeanUtils.copyProperties(param, sysDepartment);
		try {
			sysDepartmentMapper.insertSelective(sysDepartment);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "产品数据"));
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
	public ResultBean addOrUpdateInfo(final SysDepartmentDto param){
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
	public synchronized ResultBean update(final SysDepartmentDto param) {
		ResultBean bean = null;
		try {
			SysDepartment old = sysDepartmentMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getDescription())) {
				old.setDescription(param.getDescription());
			}
			if (!ObjectUtils.isEmpty(param.getName())) {
				old.setName(param.getName());
			}
			if (!ObjectUtils.isEmpty(param.getStatus())) {
				old.setStatus(param.getStatus());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			sysDepartmentMapper.updateByPrimaryKeySelective(old);
			SysDepartment now = sysDepartmentMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "产品数据"));
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
	public ResultBean delete(final SysDepartmentDto param) {
		try {
			sysDepartmentMapper.deleteByPrimaryKey(param.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail(String.format(Constant.LOG_FORMAT, "产品数据"));
		}
		return ResultBean.success();
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean page(final SysDepartmentDto param) {
		PageInfo<SysDepartmentDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(sysDepartmentMapper.list(param));
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
	public ResultBean list(final SysDepartmentDto param) {
		List<SysDepartmentDto> list = null;
		try {
			list = sysDepartmentMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}
}
