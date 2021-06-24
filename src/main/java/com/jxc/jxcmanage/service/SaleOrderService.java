package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.SaleOrder;
import com.jxc.jxcmanage.code.mapper.SaleOrderMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.SaleOrderDto;
import com.jxc.jxcmanage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class SaleOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SaleOrderService.class);

	@Autowired
	private SaleOrderMapper saleOrderMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final SaleOrderDto param) {
		ResultBean bean = null;
		SaleOrder saleOrder = new SaleOrder();
		BeanUtils.copyProperties(param, saleOrder);
		try {
			saleOrderMapper.insertSelective(saleOrder);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "销售订单数据"));
			e.printStackTrace();
		}
		bean = ResultBean.success();
		return bean;
	}

	/**
	 * 更新数据，加锁防止并发数据不一致
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public synchronized ResultBean update(final SaleOrderDto param) {
		ResultBean bean = null;
		try {
			SaleOrder old = saleOrderMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getAccountId())) {
				old.setAccountId(param.getAccountId());
			}
			if (!ObjectUtils.isEmpty(param.getBatch())) {
				old.setBatch(param.getBatch());
			}
			if (!ObjectUtils.isEmpty(param.getDeliverer())) {
				old.setDeliverer(param.getDeliverer());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			if (!ObjectUtils.isEmpty(param.getExpressMobile())) {
				old.setExpressMobile(param.getExpressMobile());
			}
			if (!ObjectUtils.isEmpty(param.getReceiver())) {
				old.setReceiver(param.getReceiver());
			}
			if (!ObjectUtils.isEmpty(param.getSaleDate())) {
				old.setSaleDate(param.getSaleDate());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			saleOrderMapper.updateByPrimaryKeySelective(old);
			SaleOrder now = saleOrderMapper.selectByPrimaryKey(param.getId());
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
	public ResultBean delete(final SaleOrderDto param) {
		try {
			saleOrderMapper.deleteByPrimaryKey(param.getId());
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
	public ResultBean page(final SaleOrderDto param) {
		PageInfo<SaleOrderDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(saleOrderMapper.list(param));
			resultData.put("total", page.getTotal());
			resultData.put("rows", page.getList());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(resultData);
	}

	public ResultBean list(SaleOrderDto param) {
		// TODO Auto-generated method stub
		return null;
	}
}
