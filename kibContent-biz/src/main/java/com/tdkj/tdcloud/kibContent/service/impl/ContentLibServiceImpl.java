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
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.api.feign.RemoteDeleteFileService;
import com.tdkj.tdcloud.kibContent.dto.ContentLibDto;
import com.tdkj.tdcloud.kibContent.entity.ContentLib;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import com.tdkj.tdcloud.kibContent.mapper.ContentLibFileMapper;
import com.tdkj.tdcloud.kibContent.mapper.ContentLibMapper;
import com.tdkj.tdcloud.kibContent.service.ContentLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 库
 *
 * @author pigx code generator
 * @date 2023-05-18 11:07:29
 */
@Service
public class ContentLibServiceImpl extends ServiceImpl<ContentLibMapper, ContentLib> implements ContentLibService {

	@Resource
	private ContentLibMapper contentLibMapper;
	@Resource
	private MongoTemplate mongoTemplate;
	@Resource
	private ContentLibFileMapper contentLibFileMapper;

	@Resource
	private RemoteDeleteFileService remoteDeleteFileService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Transactional
	@Override
	public R deleteContentLib(Integer id) {
		ContentLib contentLib = contentLibMapper.selectContentLibById(Long.valueOf(id));
		if (contentLib==null){
			return R.failed("无数据");
		}
		if (contentLib.getLibName()!=null && !"".equals(contentLib.getLibName())){
			contentLibMapper.deleteContentLibById(Long.valueOf(id));
			contentLibFileMapper.deleteContentLibFileByClId(Long.valueOf(id));

			// 删除MongoDB中对应的collection
			if(mongoTemplate.collectionExists(contentLib.getLibName())){
				mongoTemplate.dropCollection(contentLib.getLibName());
			}

		}
		return R.ok("删除成功");
	}

	@Override
	public R getContentLibInfo(Integer id) {
		ContentLib contentLib = contentLibMapper.selectContentLibById(Long.valueOf(id));
		if (contentLib!=null){
			List<ContentLibFile> contentLibFiles = contentLibFileMapper.selectContentLibFileByClId(Long.valueOf(id));
			contentLib.setFileInfoList(contentLibFiles);
		}
		return R.ok(contentLib,"详情查询成功");
	}

	@Transactional
	@Override
	public R saveContentLib(ContentLib contentLib) {
		contentLib.setCreateTime(new Date());
		int i = contentLibMapper.insertContentLib(contentLib);
		if (i ==1){
			if (contentLib.getFileInfoList().size()>0){
				for (ContentLibFile cf : contentLib.getFileInfoList()){
					if (cf.getId()==null){
						cf.setCreateTime(new Date());
						cf.setClId(Long.valueOf(contentLib.getId()));
						contentLibFileMapper.insertContentLibFile(cf);
					}else {
						contentLibFileMapper.updateContentLibFile(cf);
					}
				}
			}
			return R.ok(i,"添加成功");
		}else {
			return R.failed("添加失败");
		}
	}

	@Transactional
	@Override
	public R updateContentLib(ContentLib contentLib) {
		int i = contentLibMapper.updateContentLib(contentLib);
		if (i ==1){
			if (contentLib.getFileInfoList().size()>0){
				for (ContentLibFile cf : contentLib.getFileInfoList()){
					if (cf.getId()==null){
						cf.setCreateTime(new Date());
						cf.setClId(Long.valueOf(contentLib.getId()));
						contentLibFileMapper.insertContentLibFile(cf);
					}else {
						contentLibFileMapper.updateContentLibFile(cf);
					}
				}
			}
			return R.ok(i,"修改成功");
		}else {
			return R.failed("修改失败");
		}
	}

	@Override
	public Page getContentLib(Page page, ContentLibDto contentLibDto) {
		QueryWrapper<ContentLib> wrapper = new QueryWrapper<>();
		wrapper.like(StringUtils.isNotBlank(contentLibDto.getChineseName()), "chinese_name", contentLibDto.getChineseName());
		wrapper.like(StringUtils.isNotBlank(contentLibDto.getLibName()), "lib_name", contentLibDto.getLibName());

		wrapper.orderByDesc("create_time");
		Page page1 = baseMapper.selectPage(page, wrapper);
		List<ContentLib> contentLibList = page1.getRecords();
		if (contentLibList!=null && contentLibList.size()>0){
			Map<String, String> collectionCountsFromRedis = getCollectionCountsFromRedis();

				contentLibList.stream().forEach(contentLib -> {
					List<ContentLibFile> contentLibFileList = contentLibFileMapper.selectContentLibFileByClId(Long.valueOf(contentLib.getId()));
					contentLib.setFileInfoList(contentLibFileList);
						if (collectionCountsFromRedis!=null){
							String value = collectionCountsFromRedis.get(contentLib.getLibName());
							contentLib.setTotal(value);
						}

				});
//			for (ContentLib cl : contentLibList){
//				List<ContentLibFile> contentLibFileList = contentLibFileMapper.selectContentLibFileByClId(Long.valueOf(cl.getId()));
//				cl.setFileInfoList(contentLibFileList);
//				String value = collectionCountsFromRedis.get(cl.getLibName());
//				cl.setTotal(value);
//			}
		}
		return page1;
	}

	@Override
	public R deleteContentLibByFileName(ContentLibFile contentLibFile) {
		int i = contentLibFileMapper.deleteContentLibFileByFileName(contentLibFile.getBucketName(), contentLibFile.getFileName());
		if (i==1){
			//remoteDeleteFileService.deleteFile(contentLibFile);
			return R.ok(i,"删除成功");
		}
//		remoteDeleteFileService.deleteFile(contentLibFile);
		return R.ok("删除成功");
	}

	@Override
	public R getLibNameList() {
		List<String> libNameList = contentLibMapper.selectLibNameList();
		return R.ok(libNameList,"成功");
	}


	public Map<String, String> getCollectionCountsFromRedis() {
		ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();

		// Check if the key "CollectionCount" exists in Redis
		if (stringRedisTemplate.hasKey("CollectionCount")) {
			// If the key exists, deserialize the JSON string to a map
			String jsonString = valueOps.get("CollectionCount");
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				Map<String, String> collectionCounts = objectMapper.readValue(jsonString, Map.class);
				return collectionCounts;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			// If the key doesn't exist, return null or an empty map
			return null;
		}
	}
}
