package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.jxcmanage.dto.SaleAmountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.SaleDetail;
import com.jxc.jxcmanage.code.mapper.SaleDetailMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.SaleDetailDto;
import com.jxc.jxcmanage.util.StringUtil;

@Service
public class SaleDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SaleOrderService.class);

	@Autowired
	private SaleDetailMapper saleDetailMapper;
	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final SaleDetailDto param) {
		ResultBean bean = null;
		SaleDetail saleDetail = new SaleDetail();
		BeanUtils.copyProperties(param, saleDetail);
		try {
			saleDetailMapper.insertSelective(saleDetail);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "销售订单数据"));
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
	public ResultBean addOrUpdateInfo(final SaleDetailDto param){
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
	public synchronized ResultBean update(final SaleDetailDto param) {
		ResultBean bean = null;
		try {
			SaleDetail old = saleDetailMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getAmount())) {
				old.setAmount(param.getAmount());
			}
			if (!ObjectUtils.isEmpty(param.getBatch())) {
				old.setBatch(param.getBatch());
			}
			if (!ObjectUtils.isEmpty(param.getPrice())) {
				old.setPrice(param.getPrice());
			}
			if (!ObjectUtils.isEmpty(param.getProductCode())) {
				old.setProductCode(param.getProductCode());
			}
			if (!ObjectUtils.isEmpty(param.getSupplierId())) {
				old.setSupplierId(param.getSupplierId());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			saleDetailMapper.updateByPrimaryKeySelective(old);
			SaleDetail now = saleDetailMapper.selectByPrimaryKey(param.getId());
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
	public ResultBean delete(final SaleDetailDto param) {
		try {
			saleDetailMapper.deleteByPrimaryKey(param.getId());
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
	public ResultBean page(final SaleDetailDto param) {
		PageInfo<SaleDetailDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(saleDetailMapper.list(param));
			resultData.put("total", page.getTotal());
			resultData.put("rows", page.getList());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(resultData);
	}

	public ResultBean list(SaleDetailDto param) {
		List<SaleDetailDto> list = null;
		try {
			list = saleDetailMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}
}
