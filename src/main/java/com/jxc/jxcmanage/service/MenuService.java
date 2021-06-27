package com.jxc.jxcmanage.service;

import com.jxc.jxcmanage.code.entity.MenuInfo;
import com.jxc.jxcmanage.code.mapper.MenuInfoMapper;
import com.jxc.jxcmanage.common.ResultBean;
import com.jxc.jxcmanage.constants.Constant;
import com.jxc.jxcmanage.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MenuService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

	@Autowired
	private MenuInfoMapper menuInfoMapper;

	/**
	 * 保存
	 * 
	 * @param menuInfo
	 * @return
	 */
	public ResultBean save(MenuInfo menuInfo) {
		menuInfo.setCreatedBy("SYSTEM");
		menuInfo.setUpdatedBy("SYSTEM");
		try {
			return ResultBean.success(menuInfoMapper.insertSelective(menuInfo));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param menuInfo
	 * @return
	 */
	public ResultBean update(MenuInfo menuInfo) {
		menuInfo.setUpdatedBy("SYSTEM");
		try {
			return ResultBean.success(menuInfoMapper.updateByPrimaryKeySelective(menuInfo));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
	}

	/**
	 * 更新或者保存
	 * @param param
	 * @return
	 */
	public ResultBean addOrUpdateInfo(final MenuInfo param){
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
	 * 删除
	 * 
	 * @param menuInfo
	 * @return
	 */
	public ResultBean delete(MenuInfo menuInfo) {
		try {
			return ResultBean.success(menuInfoMapper.deleteByPrimaryKey(menuInfo.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	public ResultBean list(Map<String,Object> param) {
		try {
			return ResultBean.success(menuInfoMapper.queryMenuList(param));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBean.fail();
		}
	}


}
