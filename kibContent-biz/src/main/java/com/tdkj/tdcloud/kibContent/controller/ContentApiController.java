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

package com.tdkj.tdcloud.kibContent.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.common.log.annotation.SysLog;
import com.tdkj.tdcloud.kibContent.entity.ContentApi;
import com.tdkj.tdcloud.kibContent.service.ContentApiService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.tdkj.tdcloud.common.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 接口api
 *
 * @author pigx code generator
 * @date 2023-05-26 10:41:50
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contentapi" )
@Tag(description  = "contentapi", name =  "接口api管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ContentApiController {

    private final  ContentApiService contentApiService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param contentApi 接口api
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_view')" )
    public R getContentApiPage(Page page, ContentApi contentApi) {
        return R.ok(contentApiService.page(page, Wrappers.query(contentApi)));
    }


    /**
     * 通过id查询接口api
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(contentApiService.getById(id));
    }

    /**
     * 新增接口api
     * @param contentApi 接口api
     * @return R
     */
    @Operation(summary = "新增接口api", description = "新增接口api")
    @SysLog("新增接口api" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_add')" )
    public R save(@RequestBody ContentApi contentApi) {
    	contentApi.setCreateTime(new Date());
        return R.ok(contentApiService.save(contentApi));
    }

    /**
     * 修改接口api
     * @param contentApi 接口api
     * @return R
     */
    @Operation(summary = "修改接口api", description = "修改接口api")
    @SysLog("修改接口api" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_edit')" )
    public R updateById(@RequestBody ContentApi contentApi) {
        return R.ok(contentApiService.updateById(contentApi));
    }

    /**
     * 通过id删除接口api
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除接口api", description = "通过id删除接口api")
    @SysLog("通过id删除接口api" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(contentApiService.removeById(id));
    }


    /**
     * 导出excel 表格
     * @param contentApi 查询条件
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('kibContent_contentapi_export')" )
    public List<ContentApi> export(ContentApi contentApi) {
        return contentApiService.list(Wrappers.query(contentApi));
    }
}
