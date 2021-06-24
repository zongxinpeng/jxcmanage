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
import com.jxc.jxcmanage.code.entity.Storage;
import com.jxc.jxcmanage.code.mapper.StorageDetailMapper;
import com.jxc.jxcmanage.code.mapper.StorageMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.StorageDetailDto;
import com.jxc.jxcmanage.dto.StorageDto;
import com.jxc.jxcmanage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class StorageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

	@Autowired
	private StorageDetailMapper storageDetailMapper;
	@Autowired
	private StorageMapper storageMapper;
	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final StorageDto param) {
		ResultBean bean = null;
		Storage storage = new Storage();
		BeanUtils.copyProperties(param, storage);
		try {
			storageMapper.insertSelective(storage);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "库存数据"));
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
	public synchronized ResultBean update(final StorageDto param) {
		ResultBean bean = null;
		try {
			Storage old = storageMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getDeliverDate())) {
				old.setDeliverDate(param.getDeliverDate());
			}
			if (!ObjectUtils.isEmpty(param.getBatch())) {
				old.setBatch(param.getBatch());
			}
			if (!ObjectUtils.isEmpty(param.getDeliverer())) {
				old.setDeliverer(param.getDeliverer());
			}
			if (!ObjectUtils.isEmpty(param.getExpressMobile())) {
				old.setExpressMobile(param.getExpressMobile());
			}
			if (!ObjectUtils.isEmpty(param.getReceiveDate())) {
				old.setReceiveDate(param.getReceiveDate());
			}
			if (!ObjectUtils.isEmpty(param.getSupplierMobile())) {
				old.setSupplierMobile(param.getSupplierMobile());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			storageMapper.updateByPrimaryKeySelective(old);
			Storage now = storageMapper.selectByPrimaryKey(param.getId());
			StringUtil.compareModel(old, now);
		} catch (Exception e) {
			bean = ResultBean.fail(String.format(Constant.LOG_FORMAT, "库存入库数据"));
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
	public ResultBean delete(final StorageDto param) {
		try {
			storageMapper.deleteByPrimaryKey(param.getId());
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
	public ResultBean page(final StorageDto param) {
		PageInfo<StorageDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(storageMapper.list(param));
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
	public ResultBean list(final StorageDto param) {
		List<StorageDto> list = null;
		try {
			list = storageMapper.list(param);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
		return ResultBean.success(list);
	}

	/**
	 * 数据导入，或者页面添加
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean csvImport( final StorageDto storageDto,final List<StorageDetailDto> params) {
		try {
			Storage storage = new Storage();
			// 生成进货单数据
			BeanUtils.copyProperties(storageDto, storage);
			storageMapper.insert(storage);
			// 生成入库明细
			storageDetailMapper.insertBatch(params);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail("数据写入失败！");
		}
		String message = String.format(Constant.IMPORT_BATCH, params.size());
		return ResultBean.success(message);
	}
}
