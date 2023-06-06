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
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.dto.ContentLibFieldDto;
import com.tdkj.tdcloud.kibContent.entity.ContentLib;
import com.tdkj.tdcloud.kibContent.entity.ContentLibField;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import com.tdkj.tdcloud.kibContent.mapper.ContentLibFieldMapper;
import com.tdkj.tdcloud.kibContent.mapper.ContentLibFileMapper;
import com.tdkj.tdcloud.kibContent.service.ContentLibFieldService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字段管理
 *
 * @author pigx code generator
 * @date 2023-05-18 15:55:02
 */
@Service
public class ContentLibFieldServiceImpl extends ServiceImpl<ContentLibFieldMapper, ContentLibField> implements ContentLibFieldService {

	@Resource
	private ContentLibFieldMapper contentLibFieldMapper;

	@Override
	public Page getContentLibField(Page page,ContentLibFieldDto contentLibFieldDto) {
		QueryWrapper<ContentLibField> wrapper = new QueryWrapper<>();
		wrapper.eq(StringUtils.isNotBlank(contentLibFieldDto.getClId().toString()), "cl_id", contentLibFieldDto.getClId());
		wrapper.eq(StringUtils.isNotBlank("0"), "del_flag", 0);

		wrapper.orderByAsc("sort_order");
		Page page1 = baseMapper.selectPage(page, wrapper);

		return page1;
	}

	@Override
	public R getHomeLibField(String libName) {
		List<ContentLibField> contentLibFieldList = contentLibFieldMapper.selectContentLibFieldByLibName(libName);

		return R.ok(contentLibFieldList,"查询成功");
	}
}
