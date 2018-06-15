/*
 * Copyright (C), 2013-2018, 上汽集团
 * FileName: GoodsService.java
 * Author:   raolesong
 * Date:     2018年1月5日 下午1:44:21
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.apiframe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apiframe.core.ApiMapping;
import com.apiframe.service.entity.GoodsEntity;
import com.apiframe.service.vo.GoodsQuery;
import com.apiframe.service.vo.GoodsVO;


/**
 * 〈功能详细描述〉
 * @author raolesong
 * 2018年1月5日 下午1:44:21
 * 
 * 注意理解 ： GoodsEntity，GoodsVO,GoodsQuery的关系
 * 
 */
@Service
public class GoodsService {
	
	public long saveGoods(GoodsEntity good){
		return 0L;
	}
	
	public void deleteGoodsById(long id){
		
	}
	
	@ApiMapping(value="findGoodsById")
	public GoodsVO findGoodsById(long id){
		GoodsVO goodsVO = new GoodsVO();
		goodsVO.setId(id);
		goodsVO.setName("你好"+id);
		return goodsVO;
	}
	
	@ApiMapping(value="findGoodsList")
	public List<GoodsVO> findGoodsList(GoodsQuery query){
		List<GoodsVO> goodsList = new ArrayList<GoodsVO>();
		goodsList.add(findGoodsById(1L));
		goodsList.add(findGoodsById(77L));
		return goodsList;
	}
	
}
