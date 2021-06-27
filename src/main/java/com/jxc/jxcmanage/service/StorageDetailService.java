package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.StorageDetail;
import com.jxc.jxcmanage.code.mapper.StorageDetailMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.StorageDetailDto;
import com.jxc.jxcmanage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class StorageDetailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StorageDetailService.class);

	@Autowired
	private StorageDetailMapper storageDetailMapper;
	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final StorageDetailDto param) {
		ResultBean bean = null;
		StorageDetail storageDetail = new StorageDetail();
		BeanUtils.copyProperties(param, storageDetail);
		try {
			storageDetailMapper.insertSelective(storageDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "库存明细数据"));
		}
		bean = ResultBean.success();
		return bean;
	}

	/**
	 * 更新或者保存
	 * @param param
	 * @return
	 */
	public ResultBean addOrUpdateInfo(final StorageDetailDto param){
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
	public synchronized ResultBean update(final StorageDetailDto param) {
		ResultBean bean = null;
		try {
			StorageDetail old = storageDetailMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getBatch())) {
				old.setBatch(param.getBatch());
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
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			storageDetailMapper.updateByPrimaryKeySelective(old);
			StorageDetail now = storageDetailMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			e.printStackTrace();
			return bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "库存明细数据"));
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
	public ResultBean delete(final StorageDetailDto param) {
		try {
			storageDetailMapper.deleteByPrimaryKey(param.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail(String.format(Constant.LOG_FORMAT, "库存数据"));
		}
		return ResultBean.success();
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean page(final StorageDetailDto param) {
		PageInfo<StorageDetailDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(storageDetailMapper.list(param));
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
	 * @param param
	 * @return
	 */
	public ResultBean list(final StorageDetailDto param) {
		List<StorageDetailDto> list = null;
		try {
			list = storageDetailMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}
}
