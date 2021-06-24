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
import com.jxc.jxcmanage.code.entity.ProfitAmount;
import com.jxc.jxcmanage.code.mapper.ProfitAmountMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.ProfitAmountDto;
import com.jxc.jxcmanage.util.StringUtil;

@Service
public class ProfitAmountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProfitAmountMapper profitAmountMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final ProfitAmountDto param) {
		ResultBean bean = null;
		ProfitAmount profitAmount = new ProfitAmount();
		BeanUtils.copyProperties(param, profitAmount);
		try {
			profitAmountMapper.insertSelective(profitAmount);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "利润数据"));
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
	public synchronized ResultBean update(final ProfitAmountDto param) {
		ResultBean bean = null;
		try {
			ProfitAmount old = profitAmountMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getProductId())) {
				old.setProductId(param.getProductId());
			}
			if (!ObjectUtils.isEmpty(param.getStatisticsType())) {
				old.setStatisticsType(param.getStatisticsType());
			}
			if (!ObjectUtils.isEmpty(param.getStatus())) {
				old.setStatus(param.getStatus());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			if (!ObjectUtils.isEmpty(param.getSupplierId())) {
				old.setSupplierId(param.getSupplierId());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			profitAmountMapper.updateByPrimaryKeySelective(old);
			ProfitAmount now = profitAmountMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "利润数据"));
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
	public ResultBean delete(final ProfitAmountDto param) {
		try {
			profitAmountMapper.deleteByPrimaryKey(param.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail(String.format(Constant.LOG_FORMAT, "利润额数据"));
		}
		return ResultBean.success();
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean page(final ProfitAmountDto param) {
		PageInfo<ProfitAmountDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(profitAmountMapper.list(param));
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
	public ResultBean list(final ProfitAmountDto param) {
		List<ProfitAmountDto> list = null;
		try {
			list = profitAmountMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}
}
