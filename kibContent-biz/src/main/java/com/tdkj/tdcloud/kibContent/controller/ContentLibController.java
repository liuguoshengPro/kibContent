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
import com.tdkj.tdcloud.common.security.annotation.Inner;
import com.tdkj.tdcloud.kibContent.dto.ContentLibDto;
import com.tdkj.tdcloud.kibContent.entity.ContentLib;
import com.tdkj.tdcloud.kibContent.entity.ContentLibFile;
import com.tdkj.tdcloud.kibContent.service.ContentLibService;
import org.springframework.security.access.prepost.PreAuthorize;
import com.tdkj.tdcloud.common.excel.annotation.ResponseExcel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库
 *
 * @author pigx code generator
 * @date 2023-05-18 11:07:29
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contentlib" )
@Tag(description  = "contentlib", name =  "库管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ContentLibController {

    private final  ContentLibService contentLibService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param contentLib 库
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_view')" )
    public R getContentLibPage(Page page, ContentLibDto contentLib) {
        return R.ok(contentLibService.getContentLib(page, contentLib));
    }

    @Inner(value = false)
	@GetMapping("/getHomePageContentLib" )
	public R getHomePageContentLib(Page page, ContentLibDto contentLib) {
		return R.ok(contentLibService.getContentLib(page, contentLib));
	}


    /**
     * 通过id查询库
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return contentLibService.getContentLibInfo(id);
    }

    /**
     * 新增库
     * @param contentLib 库
     * @return R
     */
    @Operation(summary = "新增库", description = "新增库")
    @SysLog("新增库" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_add')" )
    public R save(@RequestBody ContentLib contentLib) {
        return contentLibService.saveContentLib(contentLib);
    }

    /**
     * 修改库
     * @param contentLib 库
     * @return R
     */
    @Operation(summary = "修改库", description = "修改库")
    @SysLog("修改库" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_edit')" )
    public R updateById(@RequestBody ContentLib contentLib) {
        return contentLibService.updateContentLib(contentLib);
    }

    /**
     * 通过id删除库
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除库", description = "通过id删除库")
    @SysLog("通过id删除库" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_del')" )
    public R removeById(@PathVariable Integer id) {
        return contentLibService.deleteContentLib(id);
    }

	@Operation(summary = "通过文件名删除库", description = "通过文件名删除库")
	@SysLog("通过文件名删除库" )
	@DeleteMapping("/deleteContentLibByFileName" )
	public R deleteContentLibByFileName(@RequestBody ContentLibFile contentLibFile) {
		return contentLibService.deleteContentLibByFileName(contentLibFile);
	}


    /**
     * 导出excel 表格
     * @param contentLib 查询条件
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('kibContent_contentlib_export')" )
    public List<ContentLib> export(ContentLib contentLib) {
        return contentLibService.list(Wrappers.query(contentLib));
    }
}
