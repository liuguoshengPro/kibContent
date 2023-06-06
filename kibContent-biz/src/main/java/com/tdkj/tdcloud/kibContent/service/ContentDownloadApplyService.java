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

package com.tdkj.tdcloud.kibContent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.dto.ContentDownloadApplyDto;
import com.tdkj.tdcloud.kibContent.entity.ContentDownloadApply;

/**
 * 下载申请表
 *
 * @author pigx code generator
 * @date 2023-05-22 11:31:39
 */
public interface ContentDownloadApplyService extends IService<ContentDownloadApply> {

	R saveHomeDownloadApply(ContentDownloadApply contentDownloadApply) throws Exception;

	R updateDownloadApplyAudit(ContentDownloadApply contentDownloadApply);
	R updateDownloadApplyUrl(String id,String url);

	Page getHomeDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApplyDto) throws JsonProcessingException;
	Page getDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApplyDto);

	R updateDownloadNum(Long id);
	R updateIsCollect(Long id,Integer isCollect);
	R getDownLoadRank(ContentDownloadApplyDto contentDownloadApply);
}
