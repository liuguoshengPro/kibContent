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
import com.tdkj.tdcloud.kibContent.entity.ContentApi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 接口api
 *
 * @author pigx code generator
 * @date 2023-05-26 10:41:50
 */
@Mapper
public interface ContentApiMapper extends TdcloudBaseMapper<ContentApi> {

	/**
	 * 查询接口api
	 *
	 * @param id 接口api主键
	 * @return 接口api
	 */
	public ContentApi selectContentApiById(Long id);

	/**
	 * 查询接口api列表
	 *
	 * @param contentApi 接口api
	 * @return 接口api集合
	 */
	public List<ContentApi> selectContentApiList(ContentApi contentApi);

	/**
	 * 新增接口api
	 *
	 * @param contentApi 接口api
	 * @return 结果
	 */
	public int insertContentApi(ContentApi contentApi);

	/**
	 * 修改接口api
	 *
	 * @param contentApi 接口api
	 * @return 结果
	 */
	public int updateContentApi(ContentApi contentApi);

	/**
	 * 删除接口api
	 *
	 * @param id 接口api主键
	 * @return 结果
	 */
	public int deleteContentApiById(Long id);

	/**
	 * 批量删除接口api
	 *
	 * @param ids 需要删除的数据主键集合
	 * @return 结果
	 */
	public int deleteContentApiByIds(String[] ids);
}
