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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 字段管理
 *
 * @author pigx code generator
 * @date 2023-05-18 15:55:02
 */
@Data
@TableName("content_lib_field")
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字段管理")
public class ContentLibField extends Model<ContentLibField> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="id")
    private Integer id;

    /**
     * 库id
     */
    @Schema(description="库id")
    private Integer clId;

    /**
     * 库名
     */
    @Schema(description="库名")
    private String libName;

    /**
     * itemValue
     */
    @Schema(description="itemValue")
    private String itemValue;

    /**
     * label
     */
    @Schema(description="label")
    private String label;

    /**
     * dictType
     */
    @Schema(description="dictType")
    private String dictType;

    /**
     * description
     */
    @Schema(description="description")
    private String description;

    /**
     * 排序（升序）
     */
    @Schema(description="排序（升序）")
    private Integer sortOrder;

    /**
     * 创建人
     */
    @Schema(description="创建人")
    private String createBy;

    /**
     * 修改人
     */
    @Schema(description="修改人")
    private String updateBy;

    /**
     * 创建时间
     */
    @Schema(description="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description="更新时间")
    private LocalDateTime updateTime;

    /**
     * remarks
     */
    @Schema(description="remarks")
    private String remarks;

    /**
     * delFlag
     */
    @TableLogic
    @Schema(description="delFlag")
    private String delFlag;

}
