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
import com.tdkj.tdcloud.kibContent.dto.ContentLibFieldDto;
import com.tdkj.tdcloud.kibContent.entity.ContentLibField;
import com.tdkj.tdcloud.kibContent.service.ContentLibFieldService;
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
 * 字段管理
 *
 * @author pigx code generator
 * @date 2023-05-18 15:55:02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contentlibfield" )
@Tag(description  = "contentlibfield", name =  "字段管理管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ContentLibFieldController {

    private final  ContentLibFieldService contentLibFieldService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param contentLibField 字段管理
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    //@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_view')" )
    public R getContentLibFieldPage(Page page, ContentLibFieldDto contentLibField) {
        return R.ok(contentLibFieldService.getContentLibField(page,contentLibField));
    }

	@Inner(value = false)
	@GetMapping("/getHomeLibField" )
	@Operation(summary = "通过库名查询字段", description = "通过库名查询字段")
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_view')" )
	public R getHomeLibField(String libName) {
		return contentLibFieldService.getHomeLibField(libName);
	}


    /**
     * 通过id查询字段管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(contentLibFieldService.getById(id));
    }

    /**
     * 新增字段管理
     * @param contentLibField 字段管理
     * @return R
     */
    @Operation(summary = "新增字段管理", description = "新增字段管理")
    @SysLog("新增字段管理" )
    @PostMapping
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_add')" )
    public R save(@RequestBody ContentLibField contentLibField) {
    	contentLibField.setCreateTime(new Date());
        return R.ok(contentLibFieldService.save(contentLibField));
    }

    /**
     * 修改字段管理
     * @param contentLibField 字段管理
     * @return R
     */
    @Operation(summary = "修改字段管理", description = "修改字段管理")
    @SysLog("修改字段管理" )
    @PutMapping
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_edit')" )
    public R updateById(@RequestBody ContentLibField contentLibField) {
        return R.ok(contentLibFieldService.updateById(contentLibField));
    }

    /**
     * 通过id删除字段管理
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除字段管理", description = "通过id删除字段管理")
    @SysLog("通过id删除字段管理" )
    @DeleteMapping("/{id}" )
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(contentLibFieldService.removeById(id));
    }


    /**
     * 导出excel 表格
     * @param contentLibField 查询条件
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
	//@PreAuthorize("@pms.hasPermission('kibContent_contentlibfield_export')" )
    public List<ContentLibField> export(ContentLibField contentLibField) {
        return contentLibFieldService.list(Wrappers.query(contentLibField));
    }
}
