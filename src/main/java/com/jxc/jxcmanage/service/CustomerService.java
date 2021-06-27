package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.Customer;
import com.jxc.jxcmanage.code.mapper.CustomerMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.CustomerDto;
import com.jxc.jxcmanage.util.StringUtil;

@Service
public class CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final CustomerDto param) {
		ResultBean bean = null;
		Customer customer = new Customer();
		BeanUtils.copyProperties(param, customer);
		try {
			customerMapper.insertSelective(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "客户数据"));
		}
		bean = ResultBean.success();
		return bean;
	}

	/**
	 * 更新或者保存
	 * @param param
	 * @return
	 */
	public ResultBean addOrUpdateCustomerInfo(final CustomerDto param){
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
	public synchronized ResultBean update(final CustomerDto param) {
		ResultBean bean = null;
		try {
			Customer old = customerMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getAddress())) {
				old.setAddress(param.getAddress());
			}
			if (!ObjectUtils.isEmpty(param.getCityCode())) {
				old.setCityCode(param.getCityCode());
			}
			if (!ObjectUtils.isEmpty(param.getEmail())) {
				old.setEmail(param.getEmail());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			if (!ObjectUtils.isEmpty(param.getMobile())) {
				old.setMobile(param.getMobile());
			}
			if (!ObjectUtils.isEmpty(param.getStatus())) {
				old.setStatus(param.getStatus());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			customerMapper.updateByPrimaryKeySelective(old);
			Customer now = customerMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			e.printStackTrace();
			return bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "客户数据"));
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
	public ResultBean delete(final CustomerDto param) {
		try {
			customerMapper.deleteByPrimaryKey(param.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail(String.format(Constant.LOG_FORMAT, "客户数据"));
		}
		return ResultBean.success();
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean page(final CustomerDto param) {
		PageInfo<CustomerDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(customerMapper.list(param));
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
	public ResultBean list(final CustomerDto param) {
		List<CustomerDto> list = null;
		try {
			list = customerMapper.list(param);
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
	public ResultBean csvImport(final List<CustomerDto> params) {
		try {
			// 生成产品明细
			customerMapper.insertBatch(params);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("文件写入失败！");
		}
		String message = String.format(Constant.IMPORT_BATCH, params.size());
		return ResultBean.success(message);
	}
}
