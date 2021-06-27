package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.jxcmanage.dto.ProfitAmountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.SaleAmount;
import com.jxc.jxcmanage.code.mapper.SaleAmountMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.SaleAmountDto;
import com.jxc.jxcmanage.util.StringUtil;

@Service
public class SaleAmountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private SaleAmountMapper saleAmountMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final SaleAmountDto param) {
		ResultBean bean = null;
		SaleAmount saleAmount = new SaleAmount();
		BeanUtils.copyProperties(param, saleAmount);
		try {
			saleAmountMapper.insertSelective(saleAmount);
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
	public ResultBean addOrUpdateInfo(final SaleAmountDto param){
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
	public synchronized ResultBean update(final SaleAmountDto param) {
		ResultBean bean = null;
		try {
			SaleAmount old = saleAmountMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getProductId())) {
				old.setProductId(param.getProductId());
			}
			if (!ObjectUtils.isEmpty(param.getStatisticsType())) {
				old.setStatisticsType(param.getStatisticsType());
			}
			if (!ObjectUtils.isEmpty(param.getSupplierId())) {
				old.setSupplierId(param.getSupplierId());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			if (!ObjectUtils.isEmpty(param.getCustomerId())) {
				old.setCustomerId(param.getCustomerId());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			saleAmountMapper.updateByPrimaryKeySelective(old);
			SaleAmount now = saleAmountMapper.selectByPrimaryKey(param.getId());
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
	public ResultBean delete(final SaleAmountDto param) {
		try {
			saleAmountMapper.deleteByPrimaryKey(param.getId());
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
	public ResultBean page(final SaleAmountDto param) {
		PageInfo<SaleAmountDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(saleAmountMapper.list(param));
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
	public ResultBean list(final SaleAmountDto param) {
		List<SaleAmountDto> list = null;
		try {
			list = saleAmountMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}

}
