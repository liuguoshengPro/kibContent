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
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.kibContent.dto.ContentLibDto;
import com.tdkj.tdcloud.kibContent.entity.ContentLib;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;

/**
 * 库
 *
 * @author pigx code generator
 * @date 2023-05-18 11:07:29
 */
public interface ContentLibService extends IService<ContentLib> {

	R deleteContentLib(Integer id);
	R getContentLibInfo(Integer id);

	R saveContentLib(ContentLib contentLib);
	R updateContentLib(ContentLib contentLib);

	Page getContentLib(Page page, ContentLibDto contentLibDto);
	R deleteContentLibByFileName(ContentLibFile contentLibFile);

	R getLibNameList();
}
