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

import cn.hutool.core.util.ArrayUtil;
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
import com.tdkj.tdcloud.kibContent.entity.EmailSender;
import com.tdkj.tdcloud.kibContent.entity.User;
import com.tdkj.tdcloud.kibContent.mapper.ContentDownloadApplyMapper;
import com.tdkj.tdcloud.kibContent.service.ContentDownloadApplyService;
import com.tdkj.tdcloud.kibContent.service.PlantMailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
	@Resource
	private PlantMailService plantMailService;


	@Override
	public Page getDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApplyDto){
//		Long userId = SecurityUtils.getUser().getId();
		QueryWrapper<ContentDownloadApply> wrapper = new QueryWrapper<>();
//		wrapper.eq(StringUtils.isNotBlank("0", "audit_status", userId);
		wrapper.like(StringUtils.isNotBlank(contentDownloadApplyDto.getUserName()), "user_name", contentDownloadApplyDto.getUserName());
		wrapper.eq(StringUtils.isNotBlank(contentDownloadApplyDto.getLibName()), "lib_name", contentDownloadApplyDto.getLibName());
		wrapper.eq(StringUtils.isNotBlank(contentDownloadApplyDto.getAuditStatus()), "audit_status", contentDownloadApplyDto.getAuditStatus());
		wrapper.eq(StringUtils.isNotBlank(contentDownloadApplyDto.getType()), "type", contentDownloadApplyDto.getType());
//		wrapper.eq(StringUtils.isNotBlank(contentDownloadApplyDto.getAuditStatus()), "audit_status", contentDownloadApplyDto.getAuditStatus());
		if (ArrayUtil.isNotEmpty(contentDownloadApplyDto.getCreateTime())) {
			wrapper.ge("create_time", contentDownloadApplyDto.getCreateTime()[0]).le("create_time",
					contentDownloadApplyDto.getCreateTime()[1]);
		}
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
			List<User> userList = contentDownloadApplyMapper.selectUserByEmailAdmin();
			if (userList.size()>0){
				userList.stream().forEach(user -> {
					EmailSender emailSender = new EmailSender();
					emailSender.setToEmail(user.getUsername());
					emailSender.setEmailType("submitApply");
					plantMailService.sendSimpleMail(emailSender);

				});
			}

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
	public R updateDownloadApplyUrl(String id, String url,String uid) {
		if (id==null || url==null || uid==null){
			return R.failed("参数错误");
		}

		int i = contentDownloadApplyMapper.updateDownloadApplyUrl(url, Long.valueOf(id));
		if (i==1){
			if ("4".equals(url)){
				contentDownloadApplyMapper.updateDownloadApplyAuditStatus(url, Long.valueOf(id));
			}else {
				contentDownloadApplyMapper.updateDownloadApplyAuditStatus("1", Long.valueOf(id));
			}
			EmailSender emailSender = new EmailSender();
			String user = contentDownloadApplyMapper.selectSysUserById(uid);
			emailSender.setToEmail(user);
			emailSender.setEmailType("download");
			plantMailService.sendSimpleMail(emailSender);
			return R.ok(i,"修改成功");
		}
		return null;
	}

	@Override
	public R updateUserIsAgreeDownload(Long id, String isAgreeDownload) {
		if (id==null || isAgreeDownload==null){
			return R.failed("参数错误");
		}
		int i = contentDownloadApplyMapper.updateUserIsAgreeDownload(id,isAgreeDownload);
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
		if (contentDownloadApplyDto.getLibName()!=null && "".equals(contentDownloadApplyDto.getLibName())){
			wrapper.eq(StringUtils.isNotBlank(contentDownloadApplyDto.getLibName()),"lib_name",contentDownloadApplyDto.getLibName());
		}
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
			if(contentDownloadApplyList.size()>0){
//				int downLoadNum = 0;
				contentDownloadApplyList.stream().forEach(contentDownloadApply1 -> {
					int num = contentDownloadApplyMapper.selectContentDownloadByParentId(contentDownloadApply1.getId());
					contentDownloadApply1.setDownloadNum(num + contentDownloadApply1.getDownloadNum());
				});
			}
			Collections.sort(contentDownloadApplyList, Comparator.comparing(ContentDownloadApply::getDownloadNum).reversed());
			map.put("total",total);
			map.put("contentDownloadApplyList",contentDownloadApplyList);

		}

		return R.ok(map,"查询排行数据成功");
	}
}
