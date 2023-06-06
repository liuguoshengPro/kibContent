/*
 *    Copyright (c) 2018-2025, tdcloud All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: tdcloud
 */
package com.tdkj.tdcloud.kibContent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.common.security.util.SecurityUtils;
import com.tdkj.tdcloud.kibContent.dto.ContentDownloadApplyDto;
import com.tdkj.tdcloud.kibContent.dto.SearchCondition;
import com.tdkj.tdcloud.kibContent.entity.ContentDownloadApply;
import com.tdkj.tdcloud.kibContent.mapper.ContentDownloadApplyMapper;
import com.tdkj.tdcloud.kibContent.service.ContentDownloadApplyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载申请表
 *
 * @author pigx code generator
 * @date 2023-05-22 11:31:39
 */
@Service
public class ContentDownloadApplyServiceImpl extends ServiceImpl<ContentDownloadApplyMapper, ContentDownloadApply> implements ContentDownloadApplyService {

	@Resource
	private ContentDownloadApplyMapper contentDownloadApplyMapper;


	@Override
	public Page getDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApplyDto){
//		Long userId = SecurityUtils.getUser().getId();
		QueryWrapper<ContentDownloadApply> wrapper = new QueryWrapper<>();
//		wrapper.eq(StringUtils.isNotBlank(userId.toString()), "user_id", userId);
		wrapper.orderByDesc("id");

		Page page1 = baseMapper.selectPage(page, wrapper);
//		List<ContentDownloadApply> contentDownloadApplyList = page1.getRecords();
//		if (contentDownloadApplyList.size()>0){
//			ObjectMapper objectMapper = new ObjectMapper();
//			for (ContentDownloadApply cda : contentDownloadApplyList){
//				if (cda.getSearchCondition()!=null && !"".equals(cda.getSearchCondition())){
//					List<SearchCondition> searchConditionList = objectMapper.readValue(cda.getSearchCondition(), new TypeReference<List<SearchCondition>>(){});
//					cda.setSearchConditionList(searchConditionList);
//				}
//			}
//		}
		return page1;
	}

	@Override
	public R saveHomeDownloadApply(ContentDownloadApply contentDownloadApply) throws Exception{
		String userName = SecurityUtils.getUser().getName();
		Long userId = SecurityUtils.getUser().getId();
		contentDownloadApply.setUserName(userName);
		contentDownloadApply.setUserId(userId);
		contentDownloadApply.setCreateTime(new Date());
		contentDownloadApply.setAuditStatus("0");
		if (contentDownloadApply.getSearchConditionList().size()>0){
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(contentDownloadApply.getSearchConditionList());
			contentDownloadApply.setSearchCondition(jsonString);
		}
		int i = contentDownloadApplyMapper.insertContentDownloadApply(contentDownloadApply);
		if (i==1){
			return R.ok(i,"添加成功");
		}
		return null;
	}

	@Override
	public R updateDownloadApplyAudit(ContentDownloadApply contentDownloadApply) {
		if ("1".equals(contentDownloadApply.getAuditStatus()) && ("".equals(contentDownloadApply.getAuditReason()) || contentDownloadApply.getAuditReason()==null)){
			contentDownloadApply.setAuditReason("同意");
		}
		if ("2".equals(contentDownloadApply.getAuditStatus()) && ("".equals(contentDownloadApply.getAuditReason()) || contentDownloadApply.getAuditReason()==null)){
			contentDownloadApply.setAuditReason("拒绝");
		}
		contentDownloadApply.setAuditTime(new Date());
		int i = contentDownloadApplyMapper.updateDownloadApplyAudit(contentDownloadApply);
		if (i==1){
			return R.ok(i,"审核成功");
		}
		return null;
	}

	@Override
	public R updateDownloadApplyUrl(String id, String url) {
		if (id==null || url==null){
			return R.failed("参数错误");
		}
		int i = contentDownloadApplyMapper.updateDownloadApplyUrl(url, Long.valueOf(id));
		if (i==1){
			return R.ok(i,"修改成功");
		}
		return null;
	}

	@Override
	public Page getHomeDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApplyDto) throws JsonProcessingException {
		Long userId = SecurityUtils.getUser().getId();
		QueryWrapper<ContentDownloadApply> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(userId.toString()), "user_id", userId);
		wrapper.orderByDesc("id");

		Page page1 = baseMapper.selectPage(page, wrapper);
		List<ContentDownloadApply> contentDownloadApplyList = page1.getRecords();
		if (contentDownloadApplyList.size()>0){
			ObjectMapper objectMapper = new ObjectMapper();
			for (ContentDownloadApply cda : contentDownloadApplyList){
				if (cda.getSearchCondition()!=null && !"".equals(cda.getSearchCondition())){
					List<SearchCondition> searchConditionList = objectMapper.readValue(cda.getSearchCondition(), new TypeReference<List<SearchCondition>>(){});
					cda.setSearchConditionList(searchConditionList);
				}
			}
		}
		return page1;
	}

	@Override
	public R updateDownloadNum(Long id) {
		int i = contentDownloadApplyMapper.updateDownloadNum(id);
		if (i==1){
			return R.ok(1,"下载量加1");
		}
		return null;
	}

	@Override
	public R updateIsCollect(Long id,Integer isCollect) {
		if (isCollect==1){
			int i = contentDownloadApplyMapper.updateIsCollect(id,isCollect);

			if (i==1){
				return R.ok(1,"收藏成功");
			}
		}else {
			int i = contentDownloadApplyMapper.updateCollectNum(id,isCollect);
			if (i==1){
				return R.ok(1,"取消收藏");
			}
		}

		return R.failed(1,"收藏失败");
	}

	@Override
	public R getDownLoadRank(ContentDownloadApplyDto contentDownloadApply) {
		if (contentDownloadApply.getPage() == 0) contentDownloadApply.setPage(1);
		contentDownloadApply.setPage((contentDownloadApply.getPage() - 1) * contentDownloadApply.getPageSize());
		Map<String,Object> map = new HashMap<>();
		int total = contentDownloadApplyMapper.selectContentDownloadNumRankTotal(contentDownloadApply);
		if (total>0){
			List<ContentDownloadApply> contentDownloadApplyList = contentDownloadApplyMapper.selectContentDownloadNumRank(contentDownloadApply);

			map.put("total",total);
			map.put("contentDownloadApplyList",contentDownloadApplyList);

		}

		return R.ok(map,"查询排行数据成功");
	}
}
