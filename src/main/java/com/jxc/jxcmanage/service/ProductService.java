package com.jxc.jxcmanage.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jxc.jxcmanage.code.entity.MenuInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxc.jxcmanage.code.entity.Product;
import com.jxc.jxcmanage.code.entity.SaleOrder;
import com.jxc.jxcmanage.code.mapper.ProductMapper;
import com.jxc.jxcmanage.code.mapper.SaleOrderMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.ProductDto;
import com.jxc.jxcmanage.dto.SaleOrderDto;
import com.jxc.jxcmanage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SaleOrderMapper saleOrderMapper;

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 */
	@Transactional
	public ResultBean save(final ProductDto param) {
		ResultBean bean = null;
		Product product = new Product();
		BeanUtils.copyProperties(param, product);
		// 创建人，更新人
		product.setCreatedDate(new Date());
		product.setUpdatedDate(new Date());
		try {
			productMapper.insertSelective(product);
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
	public ResultBean addOrUpdateInfo(final ProductDto param){
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
	public synchronized ResultBean update(final ProductDto param) {
		ResultBean bean = null;
		try {
			Product old = productMapper.selectByPrimaryKey(param.getId());
			if (!ObjectUtils.isEmpty(param.getPrice())) {
				old.setPrice(param.getPrice());
			}
			if (!ObjectUtils.isEmpty(param.getCode())) {
				old.setCode(param.getCode());
			}
			if (!ObjectUtils.isEmpty(param.getName())) {
				old.setName(param.getName());
			}
			if (!ObjectUtils.isEmpty(param.getNote())) {
				old.setNote(param.getNote());
			}
			if (!ObjectUtils.isEmpty(param.getSpecs())) {
				old.setSpecs(param.getSpecs());
			}
			// 添加更新人
			old.setUpdatedDate(new Date());
			productMapper.updateByPrimaryKeySelective(old);
			Product now = productMapper.selectByPrimaryKey(param.getId());
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
	public ResultBean delete(final ProductDto param) {
		try {
			productMapper.deleteByPrimaryKey(param.getId());
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
	public ResultBean page(final ProductDto param) {
		PageInfo<ProductDto> page = null;
		Map<String, Object> resultData = new HashMap<>();
		try {
			PageHelper.startPage(param.getPageNumber(), param.getPageSize());
			page = new PageInfo<>(productMapper.list(param));
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
	public ResultBean list(final ProductDto param) {
		List<ProductDto> list = null;
		try {
			list = productMapper.list(param);
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
	public ResultBean csvImport(final SaleOrderDto param, final List<ProductDto> params) {
		try {
			SaleOrder saleOrder = new SaleOrder();
			// 生成进货单数据
			BeanUtils.copyProperties(param, saleOrder);
			saleOrderMapper.insert(saleOrder);
			// 生成产品明细
			productMapper.insertBatch(params);
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
