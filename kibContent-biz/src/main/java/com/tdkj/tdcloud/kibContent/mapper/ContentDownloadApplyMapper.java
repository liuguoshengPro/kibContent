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

package com.tdkj.tdcloud.kibContent.mapper;

import com.tdkj.tdcloud.common.data.datascope.TdcloudBaseMapper;
import com.tdkj.tdcloud.kibContent.dto.ContentDownloadApplyDto;
import com.tdkj.tdcloud.kibContent.entity.ContentDownloadApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下载申请表
 *
 * @author pigx code generator
 * @date 2023-05-22 11:31:39
 */
@Mapper
public interface ContentDownloadApplyMapper extends TdcloudBaseMapper<ContentDownloadApply> {


	/**
	 * 查询下载申请
	 *
	 * @param id 下载申请主键
	 * @return 下载申请
	 */
	public ContentDownloadApply selectContentDownloadApplyById(Long id);

	/**
	 * 查询下载申请列表
	 *
	 * @param contentDownloadApply 下载申请
	 * @return 下载申请集合
	 */
	public List<ContentDownloadApply> selectContentDownloadApplyList(ContentDownloadApply contentDownloadApply);
	public List<ContentDownloadApply> selectContentDownloadNumRank(ContentDownloadApplyDto contentDownloadApplyDto);
	public List<ContentDownloadApply> selectContentDownload();
	int selectContentDownloadNumRankTotal(ContentDownloadApplyDto contentDownloadApplyDto);

	/**
	 * 新增下载申请
	 *
	 * @param contentDownloadApply 下载申请
	 * @return 结果
	 */
	public int insertContentDownloadApply(ContentDownloadApply contentDownloadApply);

	/**
	 * 修改下载申请
	 *
	 * @param contentDownloadApply 下载申请
	 * @return 结果
	 */
	public int updateContentDownloadApply(ContentDownloadApply contentDownloadApply);
	public int updateDownloadApplyAudit(ContentDownloadApply contentDownloadApply);
	public int updateDownloadApplyUrl(@Param("downloadUrl") String downloadUrl,@Param("id")Long id);
	public int updateDownloadApplyUrlExpire(@Param("downloadUrl") String downloadUrl,@Param("id")Long id);
	public int updateDownloadNum(@Param("id")Long id);
	public int updateIsCollect(@Param("id")Long id,@Param("isCollect")Integer isCollect);
	public int updateCollectNum(@Param("id")Long id,@Param("isCollect")Integer isCollect);

	/**
	 * 删除下载申请
	 *
	 * @param id 下载申请主键
	 * @return 结果
	 */
	public int deleteContentDownloadApplyById(Long id);

	/**
	 * 批量删除下载申请
	 *
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteContentDownloadApplyByIds(String[] ids);
}
