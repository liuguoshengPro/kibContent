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

package com.tdkj.tdcloud.kibContent.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.tdkj.tdcloud.kibContent.dto.SearchCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 下载申请表
 *
 * @author pigx code generator
 * @date 2023-05-22 11:31:39
 */
@Data
@TableName("content_download_apply")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "下载申请表")
public class ContentDownloadApply extends Model<ContentDownloadApply> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="id")
    private Integer id;

    /**
     * 申请人
     */
    @Schema(description="申请人")
    private String userName;

    /**
     * 申请人id
     */
    @Schema(description="申请人id")
    private Long userId;

    /**
     * 申请用途
     */
    @Schema(description="申请用途")
    private String applyUse;

    /**
     * 数据量
     */
    @Schema(description="数据量")
    private String dataVolume;

    /**
     * 预计数据大小
     */
    @Schema(description="预计数据大小")
    private Double dataSize;

    /**
     * 检索条件
     */
    @Schema(description="检索条件")
    private String searchCondition;

	@TableField(exist = false)
    private List<SearchCondition> searchConditionList;

    /**
     * 创建时间
     */
    @Schema(description="创建时间")
    private Date createTime;
    private Date auditTime;

    /**
     * 审核状态
     */
    @Schema(description="审核状态")
    private String auditStatus;

    /**
     * 下载地址
     */
    @Schema(description="下载地址")
    private String downloadUrl;
    private String isAgreeDownload;
    private String type;

    private String auditReason;

    private String libName;
    private Long downloadNum;
    private Long collectNum;
    private int isCollect;
    private Integer parentId;


}
