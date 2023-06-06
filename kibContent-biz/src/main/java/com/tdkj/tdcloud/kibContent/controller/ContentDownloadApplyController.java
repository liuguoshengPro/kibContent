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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tdkj.tdcloud.common.core.util.R;
import com.tdkj.tdcloud.common.log.annotation.SysLog;
import com.tdkj.tdcloud.kibContent.dto.ContentDownloadApplyDto;
import com.tdkj.tdcloud.kibContent.entity.ContentDownloadApply;
import com.tdkj.tdcloud.kibContent.service.ContentDownloadApplyService;
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
 * 下载申请表
 *
 * @author pigx code generator
 * @date 2023-05-22 11:31:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contentdownloadapply" )
@Tag(description  = "contentdownloadapply", name =  "下载申请表管理")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ContentDownloadApplyController {

    private final  ContentDownloadApplyService contentDownloadApplyService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param contentDownloadApply 下载申请表
     * @return
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @GetMapping("/page" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_view')" )
    public R getContentDownloadApplyPage(Page page, ContentDownloadApplyDto contentDownloadApply) {
        return R.ok(contentDownloadApplyService.getDownloadApplyList(page, contentDownloadApply));
    }

	@Operation(summary = "首页查询申请列表", description = "首页查询申请列表")
	@GetMapping("/getHomeDownloadApplyList" )
	public R getHomeDownloadApplyList(Page page, ContentDownloadApplyDto contentDownloadApply) throws JsonProcessingException {
		return R.ok(contentDownloadApplyService.getHomeDownloadApplyList(page, contentDownloadApply));
	}

    /**
     * 通过id查询下载申请表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_view')" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(contentDownloadApplyService.getById(id));
    }

    /**
     * 新增下载申请表
     * @param contentDownloadApply 下载申请表
     * @return R
     */
    @Operation(summary = "新增下载申请表", description = "新增下载申请表")
    @SysLog("新增下载申请表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_add')" )
    public R save(@RequestBody ContentDownloadApply contentDownloadApply) {
        return R.ok(contentDownloadApplyService.save(contentDownloadApply));
    }

	@Operation(summary = "前台首页填写下载申请表", description = "前台首页填写下载申请表")
	@SysLog("前台首页填写下载申请表" )
	@PostMapping("/saveHomeDownloadApply" )
	public R saveHomeDownloadApply(@RequestBody ContentDownloadApply contentDownloadApply)throws Exception {
		return contentDownloadApplyService.saveHomeDownloadApply(contentDownloadApply);
	}

	@Operation(summary = "审核", description = "审核")
	@SysLog("审核" )
	@PostMapping("/updateDownloadApplyAudit" )
	public R updateDownloadApplyAudit(@RequestBody ContentDownloadApply contentDownloadApply) {
		return contentDownloadApplyService.updateDownloadApplyAudit(contentDownloadApply);
	}

	@Operation(summary = "保存url", description = "保存url")
	@SysLog("保存url" )
	@PostMapping("/updateDownloadApplyUrl" )
	public R updateDownloadApplyUrl(@RequestParam("id") String id,@RequestParam("url")String url) {
		return contentDownloadApplyService.updateDownloadApplyUrl(id,url);
	}


    /**
     * 修改下载申请表
     * @param contentDownloadApply 下载申请表
     * @return R
     */
    @Operation(summary = "修改下载申请表", description = "修改下载申请表")
    @SysLog("修改下载申请表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_edit')" )
    public R updateById(@RequestBody ContentDownloadApply contentDownloadApply) {
        return R.ok(contentDownloadApplyService.updateById(contentDownloadApply));
    }

    /**
     * 通过id删除下载申请表
     * @param id id
     * @return R
     */
    @Operation(summary = "通过id删除下载申请表", description = "通过id删除下载申请表")
    @SysLog("通过id删除下载申请表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_del')" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(contentDownloadApplyService.removeById(id));
    }


    /**
     * 导出excel 表格
     * @param contentDownloadApply 查询条件
     * @return excel 文件流
     */
    @ResponseExcel
    @GetMapping("/export")
    @PreAuthorize("@pms.hasPermission('kibContent_contentdownloadapply_export')" )
    public List<ContentDownloadApply> export(ContentDownloadApply contentDownloadApply) {
        return contentDownloadApplyService.list(Wrappers.query(contentDownloadApply));
    }


	@Operation(summary = "下载量", description = "下载量")
	@SysLog("下载量" )
	@PutMapping("/updateDownloadNum")
	public R updateDownloadNum(Long id) {
		return contentDownloadApplyService.updateDownloadNum(id);
	}

	@Operation(summary = "收藏", description = "收藏")
	@SysLog("收藏" )
	@PutMapping("/updateIsCollect")
	public R updateIsCollect(Long id,Integer isCollect) {
		return contentDownloadApplyService.updateIsCollect(id,isCollect);
	}

	@Operation(summary = "下载排行榜", description = "下载排行榜")
	@SysLog("下载排行榜" )
	@GetMapping("/getDownLoadRank")
	public R getDownLoadRank(ContentDownloadApplyDto contentDownloadApply) {
		return contentDownloadApplyService.getDownLoadRank(contentDownloadApply);
	}
}
