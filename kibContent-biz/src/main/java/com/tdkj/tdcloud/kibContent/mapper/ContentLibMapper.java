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
import com.tdkj.tdcloud.kibContent.entity.ContentLib;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 库
 *
 * @author pigx code generator
 * @date 2023-05-18 11:07:29
 */
@Mapper
public interface ContentLibMapper extends TdcloudBaseMapper<ContentLib> {


	/**
	 * 查询库
	 *
	 * @param id 库主键
	 * @return 库
	 */
	public ContentLib selectContentLibById(Long id);

	/**
	 * 查询库列表
	 *
	 * @param contentLib 库
	 * @return 库集合
	 */
	public List<ContentLib> selectContentLibList(ContentLib contentLib);

	/**
	 * 新增库
	 *
	 * @param contentLib 库
	 * @return 结果
	 */
	public int insertContentLib(ContentLib contentLib);

	/**
	 * 修改库
	 *
	 * @param contentLib 库
	 * @return 结果
	 */
	public int updateContentLib(ContentLib contentLib);

	/**
	 * 删除库
	 *
	 * @param id 库主键
	 * @return 结果
	 */
	public int deleteContentLibById(Long id);

	/**
	 * 批量删除库
	 *
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteContentLibByIds(String[] ids);
}
