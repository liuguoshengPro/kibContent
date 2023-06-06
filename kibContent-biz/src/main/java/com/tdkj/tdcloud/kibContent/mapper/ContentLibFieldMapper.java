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
import com.tdkj.tdcloud.kibContent.entity.ContentLibField;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字段管理
 *
 * @author pigx code generator
 * @date 2023-05-18 15:55:02
 */
@Mapper
public interface ContentLibFieldMapper extends TdcloudBaseMapper<ContentLibField> {


	/**
	 * 查询字段管理
	 *
	 * @param id 字段管理主键
	 * @return 字段管理
	 */
	public ContentLibField selectContentLibFieldById(Long id);
	public List<ContentLibField> selectContentLibFieldByLibName(String libName);

	/**
	 * 查询字段管理列表
	 *
	 * @param contentLibField 字段管理
	 * @return 字段管理集合
	 */
	public List<ContentLibField> selectContentLibFieldList(ContentLibField contentLibField);

	/**
	 * 新增字段管理
	 *
	 * @param contentLibField 字段管理
	 * @return 结果
	 */
	public int insertContentLibField(ContentLibField contentLibField);

	/**
	 * 修改字段管理
	 *
	 * @param contentLibField 字段管理
	 * @return 结果
	 */
	public int updateContentLibField(ContentLibField contentLibField);

	/**
	 * 删除字段管理
	 *
	 * @param id 字段管理主键
	 * @return 结果
	 */
	public int deleteContentLibFieldById(Long id);

	/**
	 * 批量删除字段管理
	 *
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteContentLibFieldByIds(String[] ids);
}
